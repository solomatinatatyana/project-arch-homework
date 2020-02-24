package ru.otus.homework.projectarchhomework.config.injection.interfaces;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:properties/application.properties")
public interface TestDataConfig extends Config {

    @DefaultValue("https://google.com/")
    @Key("sut.url")
    String getURL();
}
