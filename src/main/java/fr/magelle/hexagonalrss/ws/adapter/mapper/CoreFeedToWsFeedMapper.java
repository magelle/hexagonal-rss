package fr.magelle.hexagonalrss.ws.adapter.mapper;


import fr.magelle.hexagonalrss.ws.api.Feed;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CoreFeedToWsFeedMapper {

    public Feed wsFeedFromCoreFeed(fr.magelle.hexagonalrss.core.dto.Feed coreFeed) {
        return new Feed(
                coreFeed.getId(),
                coreFeed.getName(),
                coreFeed.getUrl(),
                coreFeed.getLastUpdate()
        );
    }

    public List<Feed> wsFeedsFromCodeFeeds(Collection<fr.magelle.hexagonalrss.core.dto.Feed> coreFeeds) {
        return coreFeeds.stream()
                .map(this::wsFeedFromCoreFeed)
                .collect(Collectors.toList());
    }

    public fr.magelle.hexagonalrss.core.dto.Feed coreFeedFromWsFeed(Feed wsFeed) {
        return new fr.magelle.hexagonalrss.core.dto.Feed(
                wsFeed.getId(),
                wsFeed.getName(),
                wsFeed.getUrl()
        );
    }

}
