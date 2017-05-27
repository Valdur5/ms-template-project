package org.wscale.commons.merger;

import java.time.LocalDate;

/**
 * Common methods for merge operations. Some
 */
public abstract class AbstractMerger {

    protected boolean isSet(LocalDate value) {
        boolean empty = true;
        if (value == null) {
            empty = false;
        }
        return empty;
    }

    protected boolean isSet(String[] value) {
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

    protected boolean isSet(String value) {
        boolean empty = true;
        if ("".equals(value)) {
            empty = false;
        }
        if (value == null) {
            empty = false;
        }
        return empty;
    }

    protected boolean isSet(int value) {
        boolean empty = true;
        if (value == 0) {
            empty = false;
        }
        return empty;
    }

}
