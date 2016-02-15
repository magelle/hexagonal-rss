package fr.magelle.hexagonalrss.app.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Application configuration
 */
public class HexagonalRssConfiguration extends Configuration {

    @NotEmpty
    private String instanceName;

    @Valid
    @NotNull
    @JsonProperty("database")
    private DataSourceFactory database = new DataSourceFactory();

    @Valid
    @NotNull
    @JsonProperty("liquibase")
    private LiquibaseConfiguration liquibaseConfiguration;

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public void setDataSourceFactory(DataSourceFactory factory) {
        this.database = factory;
    }

    public DataSourceFactory getDataSourceFactory() {
        return database;
    }

    public LiquibaseConfiguration getLiquibaseConfiguration() {
        return liquibaseConfiguration;
    }

    public void setLiquibaseConfiguration(LiquibaseConfiguration liquibaseConfiguration) {
        this.liquibaseConfiguration = liquibaseConfiguration;
    }
}
