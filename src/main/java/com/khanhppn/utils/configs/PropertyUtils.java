package com.khanhppn.utils.configs;

import com.khanhppn.constants.FrameworkConstants;
import com.khanhppn.enums.ConfigProperties;
import custom.exceptions.PropertyFileUsageException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PropertyUtils {

    private static final Properties property = new Properties();

    static void loadProperties(String propertyFilePath) {
        try (FileInputStream input = new FileInputStream(propertyFilePath)) {
            property.load(input);
        } catch (IOException e) {
            throw new PropertyFileUsageException("IOException occurred while loading Property file in the specified path");
        }
    }

    public static String getPropertyValue(ConfigProperties key) {
        loadProperties(FrameworkConstants.CONFIG_PROPERTIES_PATH);
        if (Objects.isNull(property.getProperty(key.name().toLowerCase())) || Objects.isNull(key.name().toLowerCase())) {
            throw new PropertyFileUsageException("Property name - " + key + " is not found. Please check the config.properties");
        }
        return property.getProperty(key.name().toLowerCase());
    }
}
