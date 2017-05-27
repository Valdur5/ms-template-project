package de.pandigo.mountains.merger;

import org.wscale.commons.merger.AbstractMerger;
import org.wscale.mountains.domain.MountainEntity;
import org.springframework.stereotype.Component;


/**
 * For the HTTP Patch operation we need to merge two mountain classes because we only want to use the set fields for
 * an Patch update and reuse the not set fields from the already existing object. That is why this merger class exists.
 *
 * The following values count as not set:
 *
 * 0 / null and an empty String or Array.
 *
 * All fields of the received mountain get priority when set,
 * only the mountainID will always be the one from the loaded mountain.
 */
@Component
public class MountainMerger extends AbstractMerger {

    public MountainEntity mergeMountains(MountainEntity loadedMountain, MountainEntity receivedMountain) {

        MountainEntity returnMountainEntry = null;

        if (receivedMountain == null) {
            returnMountainEntry = loadedMountain;
        } else if (loadedMountain == null) {
            returnMountainEntry = receivedMountain;
        } else {
            if (isSet(receivedMountain.getName())) {
                loadedMountain.setName(receivedMountain.getName());
            }
            if (isSet(receivedMountain.getAltitude())) {
                loadedMountain.setAltitude(receivedMountain.getAltitude());
            }
            if (isSet(receivedMountain.getFirstAscent())) {
                loadedMountain.setFirstAscent(receivedMountain.getFirstAscent());
            }
            if (isSet(receivedMountain.getFirstAscenders())) {
                loadedMountain.setFirstAscenders(receivedMountain.getFirstAscenders());
            }
            if (isSet(receivedMountain.getDateAdded())) {
                loadedMountain.setDateAdded(receivedMountain.getDateAdded());
            }
            returnMountainEntry = loadedMountain;
        }

        return returnMountainEntry;
    }

}
