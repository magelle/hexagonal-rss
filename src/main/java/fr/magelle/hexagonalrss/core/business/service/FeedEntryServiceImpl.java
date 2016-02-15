package fr.magelle.hexagonalrss.core.business.service;

import fr.magelle.hexagonalrss.core.dto.Feed;
import fr.magelle.hexagonalrss.core.dto.FeedEntry;
import fr.magelle.hexagonalrss.core.api.service.FeedEntryService;
import fr.magelle.hexagonalrss.core.spi.FeedRepository;
import fr.magelle.hexagonalrss.core.spi.FeedEntryRepository;
import fr.magelle.hexagonalrss.core.spi.FeedSynchronize;

import java.util.List;

/**
 * Manage feed entries
 */
public class FeedEntryServiceImpl implements FeedEntryService {

    private FeedRepository feedRepository;
    private FeedEntryRepository feedEntryRepository;
    private FeedSynchronize feedSynchronize;

    public FeedEntryServiceImpl(FeedRepository feedRepository, FeedEntryRepository feedEntryRepository, FeedSynchronize feedSynchronize) {
        this.feedRepository = feedRepository;
        this.feedEntryRepository = feedEntryRepository;
        this.feedSynchronize = feedSynchronize;
    }

    public List<FeedEntry> getAllEntries() {
        return feedEntryRepository.findAll();
    }

    public List<FeedEntry> getAllEntriesOfFeed(Long feedId) {
        return feedEntryRepository.findByFeedId(feedId);
    }

    public List<FeedEntry> getUnreadEntries() {
        return feedEntryRepository.findByIsReadFalse();
    }

    public List<FeedEntry> getUnreadEntriesOfFeed(Long feedId) {
        return feedEntryRepository.findByFeedIdAndIsReadFalse(feedId);
    }

    public void markEntryAsRead(Long feedEntryId) {
        feedEntryRepository.markAsReadById(feedEntryId);
    }

    public void markEntriesOfFeedAsRead(Long feedId) {
        feedEntryRepository.markAsReadByFeedId(feedId);
    }

    public void retrieveNewFeeds() {
        feedRepository.findAll().forEach(feed -> this.retrieveNewFeeds(feed.getId()));
    }

    public void retrieveNewFeeds(Long feedId) {
        Feed feed = feedRepository.findById(feedId);
        List<FeedEntry> newFeedEntries = feedSynchronize.getFeedEntriesFromURLAfter(feed.getUrl(), feed.getLastUpdate());
        newFeedEntries.forEach(feedEntry ->
                feedEntry.setFeedId(feedId)
        );
        feedEntryRepository.save(newFeedEntries);
    }
}
