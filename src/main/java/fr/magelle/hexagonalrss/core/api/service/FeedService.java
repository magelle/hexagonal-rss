package fr.magelle.hexagonalrss.core.api.service;


import fr.magelle.hexagonalrss.core.dto.Feed;

import java.util.List;

/**
 * Feed management interface
 */
public interface FeedService {

    Feed add(Feed feed);
    Feed update(Feed feed);
    void delete(Long feedId);
    List<Feed> getAll();
    Feed get(Long id);

}
