package de.pandigo.dto;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;
import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

// TODO i am not too sure if that is a good practice.
// TODO Giving out the List for sure not but its a DTO so it wont be processed on this java side.

@ApiObject
public class Mountains extends ResourceSupport {

    @ApiObjectField(description = "A list of all the mountains.")
    private List<Mountain> mountains;

    // todo here we could provide paging information

    public List<Mountain> getMountains() {
        return mountains;
    }

    public void addMountain(Mountain mountain) {
        this.mountains.add(mountain);
    }

    public void setMountain(int index, Mountain mountain) {
        this.mountains.set(index, mountain);
    }

    public void deleteMountain(int index) {
        this.mountains.remove(index);
    }

    public Mountains() {
        this.mountains = new ArrayList<>();
    }

}
