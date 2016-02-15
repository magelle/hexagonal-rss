package fr.magelle.hexagonalrss.core.spi;

import fr.magelle.hexagonalrss.core.dto.FeedEntry;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Retrieve lasted feed entries
 */
public interface FeedSynchronize {

    List<FeedEntry> getFeedEntriesFromURLAfter(String url, LocalDateTime date);

}
