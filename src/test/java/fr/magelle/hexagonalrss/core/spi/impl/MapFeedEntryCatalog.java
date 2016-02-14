package fr.magelle.hexagonalrss.core.spi.impl;


import fr.magelle.hexagonalrss.core.api.dto.FeedEntry;
import fr.magelle.hexagonalrss.core.spi.FeedEntryCatalog;

import java.util.*;
import java.util.stream.Collectors;

public class MapFeedEntryCatalog implements FeedEntryCatalog {

    private Map<Long, FeedEntry> feedEntryMap;
    private long lastId = 0;

    public MapFeedEntryCatalog() {
        feedEntryMap = new HashMap();
    }

    @Override
    public FeedEntry save(FeedEntry feedEntry) {
        feedEntry.setId(++lastId);
        feedEntryMap.put(feedEntry.getId(), feedEntry);
        return feedEntry;
    }

    @Override
    public List<FeedEntry> save(FeedEntry... feedEntries) {
        return save(Arrays.asList(feedEntries));
    }

    @Override
    public List<FeedEntry> save(Collection<FeedEntry> feedEntries) {
        return feedEntries.stream()
                .map(this::save)
                .collect(Collectors.toList());
    }

    @Override
    public FeedEntry markAsReadById(Long feedEntryId) {
        FeedEntry feedEntry = feedEntryMap.get(feedEntryId);
        feedEntry.setIsRead(true);
        return feedEntry;
    }

    @Override
    public List<FeedEntry> markAsReadByFeedId(Long feedId) {
        List<FeedEntry> feedEntries = feedEntryMap.values().stream()
                .filter(feedEntry -> feedEntry.getFeedId().equals(feedId))
                .collect(Collectors.toList());
        feedEntries.forEach(feedEntry1 -> feedEntry1.setIsRead(true));
        return feedEntries;
    }

    @Override
    public void delete(Long feedEntryId) {
        feedEntryMap.remove(feedEntryId);
    }

    @Override
    public List<FeedEntry> findAll() {
        return new ArrayList<>(feedEntryMap.values());
    }

    @Override
    public List<FeedEntry> findByFeedId(Long feedId) {
        return feedEntryMap.values().stream()
                .filter(feedEntry -> feedEntry.getFeedId().equals(feedId))
                .collect(Collectors.toList());
    }

    @Override
    public List<FeedEntry> findByIsReadFalse() {
        return feedEntryMap.values().stream()
                .filter(feedEntry -> !feedEntry.isRead())
                .collect(Collectors.toList());
    }

    @Override
    public List<FeedEntry> findByFeedIdAndIsReadFalse(Long feedId) {
        return feedEntryMap.values().stream()
                .filter(feedEntry -> !feedEntry.isRead())
                .filter(feedEntry -> feedEntry.getFeedId().equals(feedId))
                .collect(Collectors.toList());
    }
}
