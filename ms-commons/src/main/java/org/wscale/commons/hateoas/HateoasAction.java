package org.wscale.commons.hateoas;

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

import java.util.ArrayList;
import java.util.List;

public class HateoasAction {

    private String type;
    private List<Object> links;

    String getType() {
        return this.type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    List<Object> getLinks() {
        return this.links;
    }

    public void setLinks(final List<Object> links) {
        this.links = links;
    }

    public HateoasAction(final String type, final List<Object> links) {
        this.type = type;
        this.links = links;
    }

    public HateoasAction(final String type, final Object link) {
        this.type = type;
        this.links = new ArrayList<>();
        this.links.add(link);
    }
}
