/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2016-2021 Gerrit Grunwald.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package eu.hansolo.tilesfx.chart;

import eu.hansolo.tilesfx.events.ChartDataEventListener;
import eu.hansolo.toolboxfx.GradientLookup;
import eu.hansolo.toolboxfx.geom.Location;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.HashMap;


public class ChartDataBuilder<B extends ChartDataBuilder<B>> {
    private HashMap<String, Property> properties = new HashMap<>();


    // ******************** Constructors **************************************
    protected ChartDataBuilder() {}


    // ******************** Methods *******************************************
    public static final ChartDataBuilder create() {
        return new ChartDataBuilder();
    }

    public final B name(final String NAME) {
        properties.put("name", new SimpleStringProperty(NAME));
        return (B)this;
    }

    public final B value(final double VALUE) {
        properties.put("value", new SimpleDoubleProperty(VALUE));
        return (B)this;
    }

    public final B timestamp(final Instant TIMESTAMP) {
        properties.put("timestamp", new SimpleObjectProperty<>(TIMESTAMP));
        return (B)this;
    }
    public final B timestamp(final ZonedDateTime TIMESTAMP) {
        properties.put("timestamp", new SimpleObjectProperty<>(TIMESTAMP.toInstant()));
        return (B)this;
    }

    public final B duration(final java.time.Duration DURATION) {
        properties.put("duration", new SimpleObjectProperty<>(DURATION));
        return (B)this;
    }

    public final B location(final Location LOCATION) {
        properties.put("location", new SimpleObjectProperty<>(LOCATION));
        return (B)this;
    }

    public final B fillColor(final Color COLOR) {
        properties.put("fillColor", new SimpleObjectProperty(COLOR));
        return (B)this;
    }

    public final B strokeColor(final Color COLOR) {
        properties.put("strokeColor", new SimpleObjectProperty<>(COLOR));
        return (B)this;
    }

    public final B textColor(final Color COLOR) {
        properties.put("textColor", new SimpleObjectProperty<>(COLOR));
        return (B)this;
    }

    public final B animated(final boolean ANIMATED) {
        properties.put("animated", new SimpleBooleanProperty(ANIMATED));
        return (B)this;
    }

    public final B formatString(final String FORMAT_STRING) {
        properties.put("formatString", new SimpleStringProperty(FORMAT_STRING));
        return (B)this;
    }

    public final B minValue(final double MIN_VALUE) {
        properties.put("minValue", new SimpleDoubleProperty(MIN_VALUE));
        return (B)this;
    }

    public final B maxValue(final double MAX_VALUE) {
        properties.put("maxValue", new SimpleDoubleProperty(MAX_VALUE));
        return (B)this;
    }

    public final B gradientLookup(final GradientLookup GRADIENT_LOOKUP) {
        properties.put("gradientLookup", new SimpleObjectProperty(GRADIENT_LOOKUP));
        return (B)this;
    }

    public final B useChartDataColor(final boolean USE) {
        properties.put("useChartDataColor", new SimpleBooleanProperty(USE));
        return (B)this;
    }

    public final B onChartDataEvent(final ChartDataEventListener HANDLER) {
        properties.put("onChartDataEvent", new SimpleObjectProperty<>(HANDLER));
        return (B)this;
    }

    public final B image(final Image IMAGE) {
        properties.put("image", new SimpleObjectProperty<>(IMAGE));
        return (B)this;
    }


    public final ChartData build() {
        final ChartData DATA = new ChartData();
        properties.forEach((key, property) -> {
            switch (key) {
                case "name"              -> DATA.setName(((StringProperty) property).get());
                case "value"             -> DATA.setValue(((DoubleProperty) property).get());
                case "timestamp"         -> DATA.setTimestamp(((ObjectProperty<Instant>) property).get());
                case "duration"          -> DATA.setDuration(((ObjectProperty<java.time.Duration>) property).get());
                case "location"          -> DATA.setLocation(((ObjectProperty<Location>) property).get());
                case "fillColor"         -> DATA.setFillColor(((ObjectProperty<Color>) property).get());
                case "strokeColor"       -> DATA.setStrokeColor(((ObjectProperty<Color>) property).get());
                case "textColor"         -> DATA.setTextColor(((ObjectProperty<Color>) property).get());
                case "animated"          -> DATA.setAnimated(((BooleanProperty) property).get());
                case "formatString"      -> DATA.setFormatString(((StringProperty) property).get());
                case "minValue"          -> DATA.setMinValue(((DoubleProperty) property).get());
                case "maxValue"          -> DATA.setMaxValue(((DoubleProperty) property).get());
                case "gradientLookup"    -> DATA.setGradientLookup(((ObjectProperty<GradientLookup>) property).get());
                case "useChartDataColor" -> DATA.setUseChartDataColors(((BooleanProperty) property).get());
                case "onChartDataEvent"  -> DATA.setOnChartDataEvent(((ObjectProperty<ChartDataEventListener>) property).get());
                case "image"             -> DATA.setImage(((ObjectProperty<Image>) property).get());
            }
        });
        return DATA;
    }
}
