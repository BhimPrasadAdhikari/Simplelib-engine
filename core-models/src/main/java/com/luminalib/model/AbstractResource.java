package com.luminalib.model;

import java.util.UUID;

public abstract class AbstractResource {
    private final String id;
    private final String title;
    private ResourceStatus status;

    public AbstractResource (String title) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.status = ResourceStatus.AVAILABLE;

    }

    public String getId() { return id; }
    public String getTitle() {return title;}
    public ResourceStatus getStatus() {return status;}

    public void setStatus(ResourceStatus status) { this.status = status;}

    public abstract int getLoanPeriodInDays();
}
