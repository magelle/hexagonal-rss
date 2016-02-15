package fr.magelle.hexagonalrss.core.api.service;

import fr.magelle.hexagonalrss.core.dto.FeedEntry;

import java.util.List;

/**
 * feed entries management interface
 */
public interface FeedEntryService {

    List<FeedEntry> getAllEntries();
    List<FeedEntry> getAllEntriesOfFeed(Long feedId);
    List<FeedEntry> getUnreadEntries();
    List<FeedEntry> getUnreadEntriesOfFeed(Long feedId);

    void markEntryAsRead(Long feedEntryId);
    void markEntriesOfFeedAsRead(Long feedId);

    void retrieveNewFeeds();
    void retrieveNewFeeds(Long FeedId);
}
