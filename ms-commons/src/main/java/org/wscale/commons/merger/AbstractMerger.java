package org.wscale.commons.merger;

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

import java.time.LocalDate;

/**
 * Common methods for merge operations. Some
 */
public abstract class AbstractMerger {

    protected boolean isSet(final LocalDate value) {
        boolean empty = true;
        if (value == null) {
            empty = false;
        }
        return empty;
    }

    protected boolean isSet(final String[] value) {
        boolean empty = true;
        if (value == null) {
            empty = false;
        } else {
            // Defensive programming we make sure the array is not null before we execute those request.
            if (value.length == 0) {
                empty = false;
            }
        }
        return empty;
    }

    protected boolean isSet(final String value) {
        boolean empty = true;
        if ("".equals(value)) {
            empty = false;
        }
        if (value == null) {
            empty = false;
        }
        return empty;
    }

    protected boolean isSet(final int value) {
        boolean empty = true;
        if (value == 0) {
            empty = false;
        }
        return empty;
    }

}
