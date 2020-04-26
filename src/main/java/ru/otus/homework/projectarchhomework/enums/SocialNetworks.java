package ru.otus.homework.projectarchhomework.enums;

public enum SocialNetworks {
    FACEBOOK("#3b5998"),
    VK("#4178a9"),
    TWITTER("#55acee"),
    GITHUB("#161514"),
    LIVE_ID("#4279d3"),
    GOOGLE("#dc4e41");
    private String color;

    SocialNetworks(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
