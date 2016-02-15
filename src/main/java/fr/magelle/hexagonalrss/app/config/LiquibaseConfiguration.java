package fr.magelle.hexagonalrss.app.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class LiquibaseConfiguration {

    @Valid
    @NotNull
    @JsonProperty("context")
    private String context;

    @Valid
    @NotEmpty
    @JsonProperty("changelog")
    private String changelogPath;

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getChangelogPath() {
        return changelogPath;
    }

    public void setChangelogPath(String changelogPath) {
        this.changelogPath = changelogPath;
    }
}
