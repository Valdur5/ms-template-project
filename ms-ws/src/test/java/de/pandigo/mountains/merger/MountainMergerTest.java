package de.pandigo.mountains.merger;

import org.wscale.mountains.domain.MountainEntity;
import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class MountainMergerTest {

    private MountainMerger mountainMerger = new MountainMerger();

    private static final long LOADED_MOUNTAINID = 1L;
    private static final long RECEIVED_MOUNTAINID = 2L;
    private static final int LOADED_ALTITUDE = 1111;
    private static final int RECEIVED_ALTITUDE = 2222;
    private static final int LOADED_FIRSTASCENT = 1999;
    private static final int RECEIVED_FIRSTASCENT = 2000;
    private static final String LOADED_NAME = "LoadedMountain";
    private static final String RECEIVED_NAME = "ReceivedMountain";
    private static final LocalDate LOADED_DATE = LocalDate.ofYearDay(2001,1);
    private static final LocalDate RECEIVED_DATE = LocalDate.now();
    private static final String[] LOADED_FIRSTASCENDERS = {"Some Guy", "Another Guy"};
    private static final String[] RECEIVED_FIRSTASCENDERS = {"The Third Guy"};

    @Test
    public void mergeMountains_bothMountainsNull_returnNull() throws Exception {
        // Arrange, Act & Assert
        assertNull(mountainMerger.mergeMountains(null, null));
    }

    @Test
    public void mergeMountains_loadedMountainIsNull_returnReceivedMountain() throws Exception {
        // Arrange
        MountainEntity receivedMountain = new MountainEntity(RECEIVED_NAME,
                RECEIVED_ALTITUDE, RECEIVED_FIRSTASCENT, RECEIVED_FIRSTASCENDERS, RECEIVED_DATE);
        // Act & Assert
        assertThat(mountainMerger.mergeMountains(null, receivedMountain), is(receivedMountain));
    }

    @Test
    public void mergeMountains_receivedMountainIsNull_returnLoadedMountain() throws Exception {
        // Arrange
        MountainEntity loadedMountain = new MountainEntity(LOADED_NAME,
                LOADED_ALTITUDE,LOADED_FIRSTASCENT, LOADED_FIRSTASCENDERS, LOADED_DATE);
        // Act & Assert
        assertThat(mountainMerger.mergeMountains(loadedMountain, null), is(loadedMountain));
    }

    @Test
    public void mergeMountains_receivedMountainIsEmpty_returnAllValuesOfLoadedMountain() throws Exception {
        // Arrange
        LocalDate now = LocalDate.now();
        MountainEntity loadedMountain = new MountainEntity(LOADED_NAME,
                LOADED_ALTITUDE,LOADED_FIRSTASCENT, LOADED_FIRSTASCENDERS, LOADED_DATE);
        MountainEntity receivedMountain = new MountainEntity("",0
                ,0, new String[]{}, null);
        // Act
        MountainEntity mergedMountain = mountainMerger.mergeMountains(loadedMountain, receivedMountain);
        // Assert
        assertNotNull(mergedMountain);
        assertThat(mergedMountain.getName(), is(LOADED_NAME));
        assertThat(mergedMountain.getAltitude(), is(LOADED_ALTITUDE));
        assertThat(mergedMountain.getFirstAscent(), is(LOADED_FIRSTASCENT));
        assertThat(mergedMountain.getFirstAscenders(), is(LOADED_FIRSTASCENDERS));
        assertThat(mergedMountain.getDateAdded(), is(LOADED_DATE));
    }

    @Test
    public void mergeMountains_receivedMountainIsCompletelySet_returnAllValuesOfReceivedMountain() throws Exception {
        // Arrange
        LocalDate someDayAgo = LocalDate.ofYearDay(2001,1);
        MountainEntity loadedMountain = new MountainEntity(LOADED_NAME,
                LOADED_ALTITUDE,LOADED_FIRSTASCENT, LOADED_FIRSTASCENDERS, LOADED_DATE);
        MountainEntity receivedMountain = new MountainEntity(RECEIVED_NAME,
                RECEIVED_ALTITUDE, RECEIVED_FIRSTASCENT, RECEIVED_FIRSTASCENDERS, RECEIVED_DATE);
        // Act
        MountainEntity mergedMountain = mountainMerger.mergeMountains(loadedMountain, receivedMountain);
        // Assert
        assertNotNull(mergedMountain);
        assertThat(mergedMountain.getName(), is(RECEIVED_NAME));
        assertThat(mergedMountain.getAltitude(), is(RECEIVED_ALTITUDE));
        assertThat(mergedMountain.getFirstAscent(), is(RECEIVED_FIRSTASCENT));
        assertThat(mergedMountain.getFirstAscenders(), is(RECEIVED_FIRSTASCENDERS));
        assertThat(mergedMountain.getDateAdded(), is(RECEIVED_DATE));
    }

    @Test
    public void mergeMountains_receivedMountainIsPartiallySet_returnAMixOfValues_part1() throws Exception {
        // Arrange
        MountainEntity loadedMountain = new MountainEntity(LOADED_NAME,
                LOADED_ALTITUDE,LOADED_FIRSTASCENT, LOADED_FIRSTASCENDERS, LOADED_DATE);
        MountainEntity receivedMountain = new MountainEntity("",RECEIVED_ALTITUDE
                ,0, RECEIVED_FIRSTASCENDERS, null);
        // Act
        MountainEntity mergedMountain = mountainMerger.mergeMountains(loadedMountain, receivedMountain);
        // Assert
        assertNotNull(mergedMountain);
        assertThat(mergedMountain.getName(), is(LOADED_NAME));
        assertThat(mergedMountain.getAltitude(), is(RECEIVED_ALTITUDE));
        assertThat(mergedMountain.getFirstAscent(), is(LOADED_FIRSTASCENT));
        assertThat(mergedMountain.getFirstAscenders(), is(RECEIVED_FIRSTASCENDERS));
        assertThat(mergedMountain.getDateAdded(), is(LOADED_DATE));
    }

    @Test
    public void mergeMountains_receivedMountainIsPartiallySet_returnAMixOfValues_part2() throws Exception {
        // Arrange
        MountainEntity loadedMountain = new MountainEntity(LOADED_NAME,
                LOADED_ALTITUDE,LOADED_FIRSTASCENT, LOADED_FIRSTASCENDERS, LOADED_DATE);
        MountainEntity receivedMountain = new MountainEntity(RECEIVED_NAME,0
                ,RECEIVED_FIRSTASCENT, null, RECEIVED_DATE);
        // Act
        MountainEntity mergedMountain = mountainMerger.mergeMountains(loadedMountain, receivedMountain);
        // Assert
        assertNotNull(mergedMountain);
        assertThat(mergedMountain.getName(), is(RECEIVED_NAME));
        assertThat(mergedMountain.getAltitude(), is(LOADED_ALTITUDE));
        assertThat(mergedMountain.getFirstAscent(), is(RECEIVED_FIRSTASCENT));
        assertThat(mergedMountain.getFirstAscenders(), is(LOADED_FIRSTASCENDERS));
        assertThat(mergedMountain.getDateAdded(), is(RECEIVED_DATE));
    }

    @Test
    public void mergeMountains_loadedMountainIdIsDifferentToReceivedMountainId_loadedIdGetsPriority() {
        // Arrange
        MountainEntity loadedMountain = new MountainEntity(LOADED_NAME,
                LOADED_ALTITUDE,LOADED_FIRSTASCENT, LOADED_FIRSTASCENDERS, LOADED_DATE);
        MountainEntity receivedMountain = new MountainEntity(RECEIVED_NAME,
                RECEIVED_ALTITUDE, RECEIVED_FIRSTASCENT, RECEIVED_FIRSTASCENDERS, RECEIVED_DATE);
        loadedMountain.setMountainId(LOADED_MOUNTAINID);
        receivedMountain.setMountainId(RECEIVED_MOUNTAINID);
        // Act
        MountainEntity mergedMountain = mountainMerger.mergeMountains(loadedMountain, receivedMountain);
        // Assert
        assertNotNull(mergedMountain);
        assertThat(mergedMountain.getMountainId(), is(LOADED_MOUNTAINID));
    }

}