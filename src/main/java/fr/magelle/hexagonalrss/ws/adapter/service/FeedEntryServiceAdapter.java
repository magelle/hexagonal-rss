package fr.magelle.hexagonalrss.ws.adapter.service;


import fr.magelle.hexagonalrss.core.api.service.FeedEntryService;
import fr.magelle.hexagonalrss.ws.adapter.mapper.CoreFeedEntryToWsFeedEntryMapper;
import fr.magelle.hexagonalrss.ws.api.FeedEntry;

import java.util.List;

public class FeedEntryServiceAdapter {

    private FeedEntryService feedEntryService;
    private CoreFeedEntryToWsFeedEntryMapper coreFeedEntryToWsFeedEntryMapper;

    public FeedEntryServiceAdapter(FeedEntryService feedEntryService,
                                   CoreFeedEntryToWsFeedEntryMapper coreFeedEntryToWsFeedEntryMapper) {
        this.feedEntryService = feedEntryService;
        this.coreFeedEntryToWsFeedEntryMapper = coreFeedEntryToWsFeedEntryMapper;
    }

    public List<FeedEntry> getAllEntries() {
        return coreFeedEntryToWsFeedEntryMapper.wsFeedEntriesFromCoreFeedEntriesMapper(
                feedEntryService.getAllEntries()
        );
    }

    public List<FeedEntry> getAllEntriesOfFeed(Long feedId) {
        return coreFeedEntryToWsFeedEntryMapper.wsFeedEntriesFromCoreFeedEntriesMapper(
                feedEntryService.getAllEntriesOfFeed(feedId)
        );
    }

    public List<FeedEntry> getUnreadEntries() {
        return coreFeedEntryToWsFeedEntryMapper.wsFeedEntriesFromCoreFeedEntriesMapper(
                feedEntryService.getUnreadEntries()
        );
    }

    public List<FeedEntry> getUnreadEntriesOfFeed(Long feedId) {
        return coreFeedEntryToWsFeedEntryMapper.wsFeedEntriesFromCoreFeedEntriesMapper(
                feedEntryService.getUnreadEntriesOfFeed(feedId)
        );
    }

    public void markEntryAsRead(Long feedEntryId) {
        feedEntryService.markEntryAsRead(feedEntryId);
    }

    public void markEntriesOfFeedAsRead(Long feedId) {
        feedEntryService.markEntriesOfFeedAsRead(feedId);
    }

    public void retrieveNewFeeds() {
        feedEntryService.retrieveNewFeeds();
    }

    public void retrieveNewFeeds(Long feedId) {
        feedEntryService.retrieveNewFeeds(feedId);
    }
}
