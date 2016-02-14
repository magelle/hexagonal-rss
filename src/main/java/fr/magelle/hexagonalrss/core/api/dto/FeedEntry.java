package fr.magelle.hexagonalrss.core.api.dto;

/**
 * An entry of a feed
 */
public class FeedEntry {

    private Long id;
    private Long feedId;
    private String title;
    private String content;
    private String url;
    private boolean isRead = false;

    public FeedEntry(Long id, Long feedId, String title, String content, String url) {
        this.id = id;
        this.feedId = feedId;
        this.title = title;
        this.content = content;
        this.url = url;
        this.isRead = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFeedId() {
        return feedId;
    }

    public void setFeedId(Long feedId) {
        this.feedId = feedId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
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
