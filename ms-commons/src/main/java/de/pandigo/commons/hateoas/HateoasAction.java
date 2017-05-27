package de.pandigo.commons.hateoas;

import java.util.ArrayList;
import java.util.List;

public class HateoasAction {

    private String type;
    private List<Object> links;

    String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    List<Object> getLinks() {
        return links;
    }

    public void setLinks(List<Object> links) {
        this.links = links;
    }

    public HateoasAction(String type, List<Object> links) {
        this.type = type;
        this.links = links;
    }

    public HateoasAction(String type, Object link) {
        this.type = type;
        this.links = new ArrayList<>();
        this.links.add(link);
    }
}
