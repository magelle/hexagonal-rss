package fr.magelle.hexagonalrss.app.config;

import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Application configuration
 */
public class HexagonalRssConfiguration extends Configuration {

    @NotEmpty
    private String instanceName;

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }
}
