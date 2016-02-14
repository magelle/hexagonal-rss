package fr.magelle.hexagonalrss.core.api.service;

import fr.magelle.hexagonalrss.core.api.dto.Feed;
import fr.magelle.hexagonalrss.core.business.service.FeedServiceImpl;
import fr.magelle.hexagonalrss.core.spi.FeedCatalog;
import fr.magelle.hexagonalrss.core.spi.impl.MapFeedCatalog;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests of FeedEntryService
 */
public class FeedServiceTest {

    public FeedService feedService;

    @Before
    public void setUp() {
        FeedCatalog feedCatalog = new MapFeedCatalog();
        feedService = new FeedServiceImpl(feedCatalog);
    }

    @Test
    public void testAddOneFeed() {
        // Setup
        Feed feed = new Feed("My feed", "http://myfeed.com/feed");

        // Exercise
        feedService.add(feed);

        // Verify
        List<Feed> feeds = feedService.getAll();
        assertThat(feeds).containsExactly(feed);
    }

    @Test
    public void testAddTwoFeed() {
        // Setup
        Feed feed1 = new Feed("My first feed", "http://myfirstfeed.com/feed");
        Feed feed2 = new Feed("My second feed", "http://mysecondfeed.com/feed");

        // Exercise
        feedService.add(feed1);
        feedService.add(feed2);

        // Verify
        List<Feed> feeds = feedService.getAll();
        assertThat(feeds).containsExactly(feed1, feed2);
    }

    @Test
    public void testUpdateAFeed() {
        // Setup
        Feed feed = new Feed("My first feed", "http://myfirstfeed.com/feed");

        // Exercise
        feed = feedService.add(feed);
        feed.setName("My second feed");
        feed.setUrl("http://mysecondfeed.com/feed");
        feedService.update(feed);

        // Verify
        List<Feed> feeds = feedService.getAll();
        assertThat(feeds).containsExactly(feed);
    }

    @Test
    public void testDeleteAFeedWhenOnlyOneFeedExists() {
        // Setup
        Feed feed = new Feed("My first feed", "http://myfirstfeed.com/feed");

        // Exercise
        feed = feedService.add(feed);
        feedService.delete(feed.getId());

        // Verify
        List<Feed> feeds = feedService.getAll();
        assertThat(feeds).isEmpty();
    }

    @Test
    public void testDeleteAFeedWhenTwoFeedsExists() {
        // Setup
        Feed feed1 = new Feed("My first feed", "http://myfirstfeed.com/feed");
        Feed feed2 = new Feed("My second feed", "http://mysecondfeed.com/feed");

        // Exercise
        feed1 = feedService.add(feed1);
        feed2 = feedService.add(feed2);
        feedService.delete(feed1.getId());

        // Verify
        List<Feed> feeds = feedService.getAll();
        assertThat(feeds).containsExactly(feed2);
    }

}
