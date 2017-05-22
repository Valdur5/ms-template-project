package de.pandigo.dto;

import java.util.List;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;
import org.springframework.hateoas.ResourceSupport;

// TODO i am not too sure if that is a good practice.
// TODO Giving out the List for sure not but its a DTO so it wont be processed on this java side.

@ApiObject
public class Mountains extends ResourceSupport {

    @ApiObjectField(description = "A list of all the mountains.")
    private List<Mountain> mountains;

    // todo here we could provide paging information

    public List<Mountain> getMountains() {
        return this.mountains;
    }

    public void addMountain(final Mountain mountain) {
        this.mountains.add(mountain);
    }

    public void setMountain(final int index, final Mountain mountain) {
        this.mountains.set(index, mountain);
    }

    public void deleteMountain(final int index) {
        this.mountains.remove(index);
    }

    private Mountains() {

    }

    public Mountains(final List<Mountain> mountains) {
        this.mountains = mountains;
    }

}
