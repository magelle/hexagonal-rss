package fr.magelle.hexagonalrss.core.dto;

import java.time.LocalDateTime;

/**
 * A feed
 */
public class Feed {

    private Long id;
    private String name;
    private String url;
    private LocalDateTime lastUpdate;

    public Feed(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public Feed(Long id, String name, String url) {
        this(name, url);
        this.id = id;
    }

    public Feed(Long id, String name, String url, LocalDateTime lastUpdate) {
        this(id, name, url);
        this.lastUpdate = lastUpdate;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
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
