package fr.magelle.hexagonalrss.core.spi.impl;

import fr.magelle.hexagonalrss.core.dto.Feed;
import fr.magelle.hexagonalrss.core.spi.FeedRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MapFeedRepository implements FeedRepository {

    private Map<Long, Feed> feeds;
    private long lastId = 0;

    public MapFeedRepository() {
        feeds = new HashMap<>();
    }

    public Feed save(Feed feed) {
        feed.setId(++lastId);
        feeds.put(feed.getId(), feed);
        return feed;
    }

    public Feed update(Feed feed) {
        return feeds.put(feed.getId(), feed);
    }

    public void delete(Long feedId) {
        feeds.remove(feedId);
    }

    public List<Feed> findAll() {
        return new ArrayList<>(feeds.values());
    }

    @Override
    public Feed findById(Long id) {
        return feeds.values().stream()
                .filter(feed -> feed.getId().equals(id))
                .findFirst().get();
    }
}
