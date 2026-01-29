package com.ztsoft.whatsapp_dispatcher.domain.model;
import com.ztsoft.whatsapp_dispatcher.domain.records.PhoneNumber;

public class WhatsappMessage {

    private String id;
    private PhoneNumber destination;
    private String content;

    public WhatsappMessage(String id, PhoneNumber destination, String content) {
        this.id = id;
        this.destination = destination;
        this.content = content;

        if (destination == null || destination.countryCode().isEmpty() || destination.number().isEmpty()) {
            throw new IllegalArgumentException("Telefone obrigatório");
        }
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PhoneNumber getDestination() {
        return destination;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "WhatsappMessage{" +
                "id='" + id + '\'' +
                ", destination='" + destination + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
