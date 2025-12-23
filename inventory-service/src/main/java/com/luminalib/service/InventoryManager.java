package com.luminalib.service;

import com.luminalib.model.AbstractResource;
import com.luminalib.model.ResourceStatus;
import com.luminalib.model.Searchable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InventoryManager<T extends AbstractResource> {

    // ArrayList is not thread safe.
    // One thread adding & another one searching causes app crash.
    private final List<T> resources = Collections.synchronizedList(new ArrayList<>());

    public void addResource(T resource) {
        resources.add(resource);
        System.out.println("Added: " + resource.getTitle());
    }

    public Optional<T> findById(String id) {
        return resources.stream()
                .filter(r -> r.getId().equals((id)))
                .findFirst();
    }

    public List<T> getAllResources() {
        return new ArrayList<>(resources);
    }

    // Returns all resources that match a specific status.
    public List<T> findByStatus(ResourceStatus status) {
        return resources.stream()
                .filter(r->r.getStatus() == status)
                .collect(Collectors.toList());
    }

    // Attempts to reserve a resource.
    // Thread-safe reservation.
    public boolean reserveResource(String id) {
        // Functional Chaining. It prevents NullPointerExceptions entirely.

        synchronized (resources) {
            return findById(id)
                    .filter(r -> r.getStatus() == ResourceStatus.AVAILABLE)
                    .map(r -> {

                        r.setStatus(ResourceStatus.RESERVED);
                        return true;
                    })
                    .orElse(false);
        }
    }

    public List<T> searchByKeyword(String keyword) {
        return resources.stream()
                // pattern matching for instanceof.
                .filter(r -> r instanceof Searchable s && s.containsKeyword(keyword))
                .collect(Collectors.toList());
    }
}


