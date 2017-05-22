package de.pandigo.dto;

import java.util.List;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;
import org.springframework.hateoas.ResourceSupport;

@ApiObject
public class Mountains extends ResourceSupport {

    @ApiObjectField(description = "A list of all the mountains.")
    private final List<Mountain> mountains;

    public List<Mountain> getMountains() {
        return this.mountains;
    }

    public Mountains(final List<Mountain> mountains) {
        this.mountains = mountains;
    }

}
