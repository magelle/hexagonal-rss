package fr.magelle.hexagonalrss.core.spi;

import fr.magelle.hexagonalrss.core.dto.FeedEntry;

import java.util.Collection;
import java.util.List;

/**
 * Store FeedEntries
 */
public interface FeedEntryRepository {

    FeedEntry save(FeedEntry feed);
    List<FeedEntry> save(FeedEntry ... feed);
    List<FeedEntry> save(Collection<FeedEntry> feed);

    FeedEntry markAsReadById(Long feedId);
    List<FeedEntry> markAsReadByFeedId(Long feedId);


    void delete(Long feedEntryId);
    List<FeedEntry> findAll();
    List<FeedEntry> findByFeedId(Long feedId);
    List<FeedEntry> findByIsReadFalse();
    List<FeedEntry> findByFeedIdAndIsReadFalse(Long feedId);

}
