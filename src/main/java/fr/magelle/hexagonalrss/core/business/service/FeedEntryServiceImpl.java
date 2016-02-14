package fr.magelle.hexagonalrss.core.business.service;

import fr.magelle.hexagonalrss.core.api.dto.Feed;
import fr.magelle.hexagonalrss.core.api.dto.FeedEntry;
import fr.magelle.hexagonalrss.core.api.service.FeedEntryService;
import fr.magelle.hexagonalrss.core.spi.FeedCatalog;
import fr.magelle.hexagonalrss.core.spi.FeedEntryCatalog;
import fr.magelle.hexagonalrss.core.spi.FeedSynchronize;

import java.util.List;

/**
 * Manage feed entries
 */
public class FeedEntryServiceImpl implements FeedEntryService {

    private FeedCatalog feedCatalog;
    private FeedEntryCatalog feedEntryCatalog;
    private FeedSynchronize feedSynchronize;

    public FeedEntryServiceImpl(FeedCatalog feedCatalog, FeedEntryCatalog feedEntryCatalog, FeedSynchronize feedSynchronize) {
        this.feedCatalog = feedCatalog;
        this.feedEntryCatalog = feedEntryCatalog;
        this.feedSynchronize = feedSynchronize;
    }

    public List<FeedEntry> getAllEntries() {
        return feedEntryCatalog.findAll();
    }

    public List<FeedEntry> getAllEntriesOfFeed(Long feedId) {
        return feedEntryCatalog.findByFeedId(feedId);
    }

    public List<FeedEntry> getUnreadEntries() {
        return feedEntryCatalog.findByIsReadFalse();
    }

    public List<FeedEntry> getUnreadEntriesOfFeed(Long feedId) {
        return feedEntryCatalog.findByFeedIdAndIsReadFalse(feedId);
    }

    public void markEntryAsRead(Long feedEntryId) {
        feedEntryCatalog.markAsReadById(feedEntryId);
    }

    public void markEntriesOfFeedAsRead(Long feedId) {
        feedEntryCatalog.markAsReadByFeedId(feedId);
    }

    public void retrieveNewFeeds() {
        feedCatalog.findAll().forEach(feed -> this.retrieveNewFeeds(feed.getId()));
    }

    public void retrieveNewFeeds(Long feedId) {
        Feed feed = feedCatalog.findById(feedId);
        List<FeedEntry> newFeedEntries = feedSynchronize.getFeedEntriesFromURLAfter(feed.getUrl(), feed.getLastUpdate());
        newFeedEntries.forEach(feedEntry ->
                feedEntry.setFeedId(feedId)
        );
        feedEntryCatalog.save(newFeedEntries);
    }
}
