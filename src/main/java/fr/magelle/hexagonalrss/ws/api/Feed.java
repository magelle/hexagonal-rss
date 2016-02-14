package fr.magelle.hexagonalrss.ws.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

public class Feed {

    private Long id;

    @Length(max = 50)
    private String name;
    @Length(max = 200)
    private String url;

    private LocalDateTime lastUpdate;

    public Feed() {
    }

    public Feed(Long id, String name, String url, LocalDateTime lastUpdate) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.lastUpdate = lastUpdate;
    }

    @JsonProperty
    public Long getId() {
        return id;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    @Override
    public String toString() {
        return "Feed{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}
