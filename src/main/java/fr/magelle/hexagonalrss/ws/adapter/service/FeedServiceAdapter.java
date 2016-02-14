package fr.magelle.hexagonalrss.ws.adapter.service;

import fr.magelle.hexagonalrss.core.api.service.FeedService;
import fr.magelle.hexagonalrss.ws.adapter.mapper.CoreFeedToWsFeedMapper;
import fr.magelle.hexagonalrss.ws.api.Feed;

import java.util.List;

public class FeedServiceAdapter {

    private FeedService feedService;
    private CoreFeedToWsFeedMapper coreFeedToWsFeedMapper;

    public FeedServiceAdapter(FeedService feedService, CoreFeedToWsFeedMapper coreFeedToWsFeedMapper) {
        this.feedService = feedService;
        this.coreFeedToWsFeedMapper = coreFeedToWsFeedMapper;
    }

    public Feed get(Long id) {
        return coreFeedToWsFeedMapper.wsFeedFromCoreFeed(
                feedService.get(id)
        );
    }

    public List<Feed> getAll() {
        return coreFeedToWsFeedMapper.wsFeedsFromCodeFeeds(
                feedService.getAll()
        );
    }

    public Feed add(Feed feed) {
        return coreFeedToWsFeedMapper.wsFeedFromCoreFeed(
                feedService.add(
                        coreFeedToWsFeedMapper.coreFeedFromWsFeed(feed)
                )
        );
    }

    public Feed update(Feed feed) {
        return coreFeedToWsFeedMapper.wsFeedFromCoreFeed(
                feedService.update(
                        coreFeedToWsFeedMapper.coreFeedFromWsFeed(feed)
                )
        );
    }

    public void delete(Long feedId) {
        feedService.delete(feedId);
    }
}
