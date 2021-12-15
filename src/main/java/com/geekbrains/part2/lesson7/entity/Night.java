package com.geekbrains.part2.lesson7.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Night {
    private String iconPhrase;

    public Night() {
    }

    @JsonGetter("IconPhrase")
    public String getIconPhrase() {
        return iconPhrase;
    }

    public void setIconPhrase(String IconPhrase) {
        this.iconPhrase = IconPhrase;
    }

    @Override
    public String toString() {
        return ". Ночью: " + iconPhrase;
    }
}