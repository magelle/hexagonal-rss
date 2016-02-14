package fr.magelle.hexagonalrss.ws.adapter.mapper;


import fr.magelle.hexagonalrss.ws.api.FeedEntry;

import java.util.List;
import java.util.stream.Collectors;

public class CoreFeedEntryToWsFeedEntryMapper {

    public FeedEntry wsFeedEntryFromCoreFeedEntryMapper(fr.magelle.hexagonalrss.core.api.dto.FeedEntry coreFeedEntry) {
        return new FeedEntry(
            coreFeedEntry.getId(),
            coreFeedEntry.getFeedId(),
            coreFeedEntry.getTitle(),
            coreFeedEntry.getContent(),
            coreFeedEntry.getUrl(),
            coreFeedEntry.isRead()
        );
    }

    public List<FeedEntry> wsFeedEntriesFromCoreFeedEntriesMapper(List<fr.magelle.hexagonalrss.core.api.dto.FeedEntry> coreFeedEntries) {
        return coreFeedEntries.stream()
                .map(this::wsFeedEntryFromCoreFeedEntryMapper)
                .collect(Collectors.toList());
    }

}
