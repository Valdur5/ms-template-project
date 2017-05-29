package org.wscale.mountains.dto;

/*
 * Copyright 2017 Valentin Durst (www.wscale.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.springframework.hateoas.ResourceSupport;

/**
 * DTO for a mountain
 */
public class Mountain extends ResourceSupport {

    private long mountainId;
    private String name;
    private int altitude;
    private int firstAscent;
    private String[] firstAscenders;

    public Mountain() {

    }

    public Mountain(final long mountainId, final String name, final int altitude) {
        this.mountainId = mountainId;
        this.name = name;
        this.altitude = altitude;
    }

    public long getMountainId() {
        return this.mountainId;
    }

    public void setMountainId(final long mountainId) {
        this.mountainId = mountainId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getAltitude() {
        return this.altitude;
    }

    public void setAltitude(final int altitude) {
        this.altitude = altitude;
    }

    public int getFirstAscent() {
        return this.firstAscent;
    }

    public void setFirstAscent(final int firstAscent) {
        this.firstAscent = firstAscent;
    }

    public String[] getFirstAscenders() {
        return this.firstAscenders;
    }

    public void setFirstAscenders(final String[] firstAscenders) {
        this.firstAscenders = firstAscenders;
    }
}
