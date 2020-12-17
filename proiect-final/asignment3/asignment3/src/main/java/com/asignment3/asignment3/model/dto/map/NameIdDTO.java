package com.asignment3.asignment3.model.dto.map;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class NameIdDTO {
    private Long id;
    private String name;

    public NameIdDTO() {}

    public NameIdDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "NameIdDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
