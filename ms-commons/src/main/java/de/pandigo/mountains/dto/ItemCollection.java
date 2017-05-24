package de.pandigo.mountains.dto;

import org.springframework.hateoas.ResourceSupport;

import java.util.List;

public class ItemCollection<T> extends ResourceSupport {

    public ItemCollection(List<T> items) {
        this.items = items;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    private List<T> items;
}
