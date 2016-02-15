package fr.magelle.hexagonalrss.core.spi;

import fr.magelle.hexagonalrss.core.dto.Feed;

import java.util.List;

/**
 * Store Feeds
 */
public interface FeedRepository {

    Feed save(Feed feed);
    Feed update(Feed feed);
    void delete(Long feedId);
    List<Feed> findAll();
    Feed findById(Long id);

}
