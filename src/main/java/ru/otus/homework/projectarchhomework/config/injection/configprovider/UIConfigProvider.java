package ru.otus.homework.projectarchhomework.config.injection.configprovider;

import com.google.inject.Provider;
import org.aeonbits.owner.ConfigFactory;
import ru.otus.homework.projectarchhomework.config.injection.interfaces.UIConfig;

public class UIConfigProvider implements Provider<UIConfig> {
    @Override
    public UIConfig get() {
        return new LocalConfig(ConfigFactory.create(UIConfig.class,System.getProperties()));
    }

    public static class LocalConfig implements UIConfig{
        UIConfig config;

        public LocalConfig(UIConfig config) {
            this.config = config;
        }

        @Override
        public String getBrowser() {
            return config.getBrowser();
        }
    }
}
