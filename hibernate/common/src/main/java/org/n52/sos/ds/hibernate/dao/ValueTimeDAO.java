/**
 * Copyright (C) 2012-2014 52°North Initiative for Geospatial Open Source
 * Software GmbH
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 as published
 * by the Free Software Foundation.
 *
 * If the program is linked with libraries which are licensed under one of
 * the following licenses, the combination of the program with the linked
 * library is not considered a "derivative work" of the program:
 *
 *     - Apache License, version 2.0
 *     - Apache Software License, version 1.0
 *     - GNU Lesser General Public License, version 3
 *     - Mozilla Public License, versions 1.0, 1.1 and 2.0
 *     - Common Development and Distribution License (CDDL), version 1.0
 *
 * Therefore the distribution of the program linked with libraries licensed
 * under the aforementioned licenses, is permitted by the copyright holders
 * if the distribution is compliant with both the GNU General Public
 * License version 2 and the aforementioned licenses.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details.
 */
package org.n52.sos.ds.hibernate.dao;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.n52.sos.ds.hibernate.entities.FeatureOfInterest;
import org.n52.sos.ds.hibernate.entities.ObservableProperty;
import org.n52.sos.ds.hibernate.entities.Observation;
import org.n52.sos.ds.hibernate.entities.Offering;
import org.n52.sos.ds.hibernate.entities.Procedure;
import org.n52.sos.ds.hibernate.entities.values.ObservationValueTime;
import org.n52.sos.ds.hibernate.util.HibernateHelper;
import org.n52.sos.ogc.ows.OwsExceptionReport;
import org.n52.sos.ogc.sos.SosConstants.SosIndeterminateTime;
import org.n52.sos.request.GetObservationRequest;
import org.n52.sos.util.CollectionHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValueTimeDAO extends AbstractValueDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(ValueTimeDAO.class);

    public ObservationValueTime getMinValueFor(GetObservationRequest request, long procedure, long observableProperty,
            long featureOfInterest, Criterion temporalFilterCriterion, Session session) throws OwsExceptionReport {
        return (ObservationValueTime) getValueCriteriaFor(request, procedure, observableProperty, featureOfInterest,
                temporalFilterCriterion, SosIndeterminateTime.first, session).uniqueResult();
    }

    public ObservationValueTime getMaxValueFor(GetObservationRequest request, long procedure, long observableProperty,
            long featureOfInterest, Criterion temporalFilterCriterion, Session session) throws HibernateException,
            OwsExceptionReport {
        return (ObservationValueTime) getValueCriteriaFor(request, procedure, observableProperty, featureOfInterest,
                temporalFilterCriterion, SosIndeterminateTime.latest, session).uniqueResult();
    }

    public ObservationValueTime getMinValueFor(GetObservationRequest request, long procedure, long observableProperty,
            long featureOfInterest, Session session) throws HibernateException, OwsExceptionReport {
        return (ObservationValueTime) getValueCriteriaFor(request, procedure, observableProperty, featureOfInterest, null,
                SosIndeterminateTime.first, session).uniqueResult();
    }

    public ObservationValueTime getMaxValueFor(GetObservationRequest request, long procedure, long observableProperty,
            long featureOfInterest, Session session) throws HibernateException, OwsExceptionReport {
        return (ObservationValueTime) getValueCriteriaFor(request, procedure, observableProperty, featureOfInterest, null,
                SosIndeterminateTime.latest, session).uniqueResult();
    }

    private Criteria getValueCriteriaFor(GetObservationRequest request, long procedure, long observableProperty,
            long featureOfInterest, Criterion temporalFilterCriterion, SosIndeterminateTime sosIndeterminateTime,
            Session session) throws OwsExceptionReport {
        final Criteria c =
                getDefaultObservationCriteria(ObservationValueTime.class, session).createAlias(Observation.PROCEDURE, "p")
                        .createAlias(Observation.FEATURE_OF_INTEREST, "f")
                        .createAlias(Observation.OBSERVABLE_PROPERTY, "o");

        checkAndAddSpatialFilteringProfileCriterion(c, request, session);

        c.add(Restrictions.eq("p." + Procedure.ID, observableProperty));
        c.add(Restrictions.eq("o." + ObservableProperty.ID, observableProperty));
        c.add(Restrictions.eq("f." + FeatureOfInterest.ID, featureOfInterest));

        if (CollectionHelper.isNotEmpty(request.getOfferings())) {
            c.createCriteria(Observation.OFFERINGS).add(Restrictions.in(Offering.IDENTIFIER, request.getOfferings()));
        }

        String logArgs = "request, series, offerings";
        if (temporalFilterCriterion != null) {
            logArgs += ", filterCriterion";
            c.add(temporalFilterCriterion);
        }
        if (sosIndeterminateTime != null) {
            logArgs += ", sosIndeterminateTime";
            addIndeterminateTimeRestriction(c, sosIndeterminateTime);
        }
        LOGGER.debug("QUERY getObservationFor({}): {}", logArgs, HibernateHelper.getSqlString(c));
        return c;
    }

    public Criteria getDefaultObservationCriteria(Class<?> clazz, Session session) {
        return session.createCriteria(clazz).add(Restrictions.eq(ObservationValueTime.DELETED, false))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
    }
}