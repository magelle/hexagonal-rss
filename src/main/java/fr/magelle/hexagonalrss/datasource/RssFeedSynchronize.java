package fr.magelle.hexagonalrss.datasource;


import fr.magelle.hexagonalrss.core.api.dto.FeedEntry;
import fr.magelle.hexagonalrss.core.spi.FeedSynchronize;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RssFeedSynchronize implements FeedSynchronize {

    private List<FeedEntry> newFeedEntries;

    public RssFeedSynchronize() {
        newFeedEntries = new ArrayList<>();
    }

    public void setNewFeedEntries(Collection<FeedEntry> feedEntries) {
        newFeedEntries.clear();
        newFeedEntries.addAll(feedEntries);
    }

    @Override
    public List<FeedEntry> getFeedEntriesFromURLAfter(String url, LocalDateTime date) {
        return newFeedEntries;
    }
}
