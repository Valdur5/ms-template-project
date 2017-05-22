package de.pandigo.hateoas;

import java.util.ArrayList;
import java.util.List;

public class HateoasAction {

    private ActionType type;
    private List<Object> links;

    public ActionType getType() {
        return type;
    }

    public void setType(ActionType type) {
        this.type = type;
    }

    public List<Object> getLinks() {
        return links;
    }

    public void setLinks(List<Object> links) {
        this.links = links;
    }

    public HateoasAction(ActionType type, List<Object> links) {
        this.type = type;
        this.links = links;
    }

    public HateoasAction(ActionType type, Object link) {
        this.type = type;
        this.links = new ArrayList<>();
        this.links.add(link);
    }
}
