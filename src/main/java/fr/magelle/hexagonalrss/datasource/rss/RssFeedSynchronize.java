package fr.magelle.hexagonalrss.datasource.rss;


import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import fr.magelle.hexagonalrss.core.dto.FeedEntry;
import fr.magelle.hexagonalrss.core.spi.FeedSynchronize;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class RssFeedSynchronize implements FeedSynchronize {

    @Override
    public List<FeedEntry> getFeedEntriesFromURLAfter(String url, LocalDateTime date) {
        try {
            URL feedUrl = new URL(url);

            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(feedUrl));

            return feed.getEntries().stream()
                    .map(this::syndEntryToCoreFeedEntry)
                    .collect(Collectors.toList());
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private FeedEntry syndEntryToCoreFeedEntry(SyndEntry syndEntry) {
        return new FeedEntry(
                null,
                null,
                syndEntry.getTitle(),
                syndEntry.getContents().stream()
                        .map(SyndContent::getValue)
                        .reduce((s, s2) -> s + s2).get(),
                syndEntry.getLink()
        );
    }
}
