package com.ztsoft.aegis_analyzer.domain.model;

public class CustomerBehavior {
    private String id;
    private String action;
    private String metadata;

    public CustomerBehavior(String id, String action, String metadata) {
        this.id = id;
        this.action = action;
        this.metadata = metadata;
        Validate();
    }

    public void Validate (){
        if (id == null || id.isEmpty()){
            throw new IllegalStateException("invalid id");
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    @Override
    public String toString() {
        return "CustomerBehavior{" +
                "id='" + id + '\'' +
                ", action='" + action + '\'' +
                ", metadata='" + metadata + '\'' +
                '}';
    }
}
