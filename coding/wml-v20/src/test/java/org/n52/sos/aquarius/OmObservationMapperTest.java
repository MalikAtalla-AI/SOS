/**
 * Copyright (C) 2012-2015 Aquatic Informatics
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
package org.n52.sos.aquarius;

public class OmObservationMapperTest {

    // TODO AI: proper mocking, update, compile
    /*
    @Test
    public void toInterpolationTypeHref_withObservationWithProcedureIdentifier_returnsExpectedHref() {

        String identifier = "Discharge_DayMean";
        String expectedHref = "http://www.opengis.net/def/waterml/2.0/interpolationType/AveragePrec";

        // InterpolationTypeMapper interpolationTypeMapper = Mockito.mock(InterpolationTypeMapper.class);
        // Mockito.when(interpolationTypeMapper.toHref(Mockito.anyString()))
        //        .thenReturn(expectedHref);

        OmObservationMapper omObservationMapper = new OmObservationMapper(new InterpolationTypeMapper(), new ObservedPropertyMapper());
        String href = omObservationMapper.toInterpolationTypeHref(makeOmObservation(identifier));

        Assert.assertEquals(expectedHref, href);
    }

    @Test
    public void toInterpolationTypeHref_withObservationWithInvalidProcedureIdentifier_returnsDefaultHref() {

        String identifier = "InstantTotal";
        String expectedHref = "http://www.opengis.net/def/waterml/2.0/interpolationType/Continuous";

        // InterpolationTypeMapper interpolationTypeMapper = Mockito.mock(InterpolationTypeMapper.class);
        // Mockito.when(interpolationTypeMapper.toHref(Mockito.anyString()))
        //        .thenReturn(expectedHref);

        OmObservationMapper omObservationMapper = new OmObservationMapper(new InterpolationTypeMapper(), new ObservedPropertyMapper());
        String href = omObservationMapper.toInterpolationTypeHref(makeOmObservation(identifier));

        Assert.assertEquals(expectedHref, href);
    }

    @Test
    public void toInterpolationTypeHref_withProcedureIdentifierWithMultipleUnderscores_returnsDefaultHref() {

        String identifier = "abc_123_InstantTotal";
        String expectedHref = "http://www.opengis.net/def/waterml/2.0/interpolationType/InstantTotal";

        // InterpolationTypeMapper interpolationTypeMapper = Mockito.mock(InterpolationTypeMapper.class);
        // Mockito.when(interpolationTypeMapper.toHref(Mockito.anyString()))
        //        .thenReturn(expectedHref);

        OmObservationMapper omObservationMapper = new OmObservationMapper(new InterpolationTypeMapper(), new ObservedPropertyMapper());
        String href = omObservationMapper.toInterpolationTypeHref(makeOmObservation(identifier));

        Assert.assertEquals(expectedHref, href);
    }

    @Test
    public void toInterpolationTypeTitle_withObservationWithProcedureIdentifier_returnsExpectedHref() {
        String identifier = "abc123_InstantTotal";
        String expectedTitle = "InstantTotal";

        // InterpolationTypeMapper interpolationTypeMapper = Mockito.mock(InterpolationTypeMapper.class);
        // Mockito.when(interpolationTypeMapper.toHref(Mockito.anyString()))
        //        .thenReturn(expectedHref);

        OmObservationMapper omObservationMapper = new OmObservationMapper(new InterpolationTypeMapper(), new ObservedPropertyMapper());
        String title = omObservationMapper.toInterpolationTypeTitle(makeOmObservation(identifier));

        Assert.assertEquals(expectedTitle, title);
    }

    private OmObservation makeOmObservation(String identifier) {
        OmObservableProperty omObservableProperty = new OmObservableProperty(identifier);
        OmObservationConstellation omObservationConstellation = new OmObservationConstellation();
        omObservationConstellation.setObservableProperty(omObservableProperty);
        OmObservation observation = new OmObservation();
        observation.setObservationConstellation(omObservationConstellation);
        return observation;
    }
*/
}