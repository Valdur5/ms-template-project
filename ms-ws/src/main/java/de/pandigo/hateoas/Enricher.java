package de.pandigo.hateoas;

import java.util.Map;

public interface Enricher<T> {
    T enrich(T obj, Map<String, Object> actions);
    T enrich(T obj);
}
