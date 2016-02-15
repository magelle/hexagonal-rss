package fr.magelle.hexagonalrss.core.business.service;

import fr.magelle.hexagonalrss.core.dto.Feed;
import fr.magelle.hexagonalrss.core.api.service.FeedService;
import fr.magelle.hexagonalrss.core.spi.FeedRepository;
import fr.magelle.hexagonalrss.core.spi.FeedEntryRepository;
import fr.magelle.hexagonalrss.core.spi.FeedSynchronize;

import java.util.List;

/**
 * Manage Feeds
 */
public class FeedServiceImpl implements FeedService {

    private FeedRepository feedRepository;
    private FeedEntryRepository feedEntryRepository;
    private FeedSynchronize feedSynchronize;

    public FeedServiceImpl(FeedRepository feedRepository) {
        this.feedRepository = feedRepository;
    }

    public Feed add(Feed feed) {
        return feedRepository.save(feed);
    }

    public Feed update(Feed feed) {
        return feedRepository.update(feed);
    }

    public void delete(Long feedId) {
        feedRepository.delete(feedId);
    }

    public List<Feed> getAll() {
        return feedRepository.findAll();
    }

    public Feed get(Long id) {
        return feedRepository.findById(id);
    }
}
