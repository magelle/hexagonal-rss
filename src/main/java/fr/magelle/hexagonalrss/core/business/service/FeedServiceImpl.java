package fr.magelle.hexagonalrss.core.business.service;

import fr.magelle.hexagonalrss.core.api.dto.Feed;
import fr.magelle.hexagonalrss.core.api.service.FeedService;
import fr.magelle.hexagonalrss.core.spi.FeedCatalog;
import fr.magelle.hexagonalrss.core.spi.FeedEntryCatalog;
import fr.magelle.hexagonalrss.core.spi.FeedSynchronize;

import java.util.List;

/**
 * Manage Feeds
 */
public class FeedServiceImpl implements FeedService {

    private FeedCatalog feedCatalog;
    private FeedEntryCatalog feedEntryCatalog;
    private FeedSynchronize feedSynchronize;

    public FeedServiceImpl(FeedCatalog feedCatalog) {
        this.feedCatalog = feedCatalog;
    }

    public Feed add(Feed feed) {
        return feedCatalog.save(feed);
    }

    public Feed update(Feed feed) {
        return feedCatalog.update(feed);
    }

    public void delete(Long feedId) {
        feedCatalog.delete(feedId);
    }

    public List<Feed> getAll() {
        return feedCatalog.findAll();
    }

    public Feed get(Long id) {
        return feedCatalog.findById(id);
    }
}
