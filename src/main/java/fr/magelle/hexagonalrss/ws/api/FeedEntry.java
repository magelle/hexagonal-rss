package fr.magelle.hexagonalrss.ws.api;


import com.fasterxml.jackson.annotation.JsonProperty;

public class FeedEntry {

    private Long id;
    private Long feedId;
    private String title;
    private String content;
    private String url;
    private boolean isRead = false;

    public FeedEntry() {
    }

    public FeedEntry(Long id, Long feedId, String title, String content, String url, boolean isRead) {
        this();
        this.id = id;
        this.feedId = feedId;
        this.title = title;
        this.content = content;
        this.url = url;
        this.isRead = isRead;
    }

    @JsonProperty
    public Long getId() {
        return id;
    }

    @JsonProperty
    public Long getFeedId() {
        return feedId;
    }

    @JsonProperty
    public String getTitle() {
        return title;
    }

    @JsonProperty
    public String getContent() {
        return content;
    }

    @JsonProperty
    public String getUrl() {
        return url;
    }

    @JsonProperty
    public boolean isRead() {
        return isRead;
    }

    @Override
    public String toString() {
        return "FeedEntry{" +
                "id=" + id +
                ", feedId=" + feedId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", url='" + url + '\'' +
                ", isRead=" + isRead +
                '}';
    }
}
