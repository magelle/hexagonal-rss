package fr.magelle.hexagonalrss.core.api.service;

import fr.magelle.hexagonalrss.core.dto.Feed;
import fr.magelle.hexagonalrss.core.dto.FeedEntry;
import fr.magelle.hexagonalrss.core.business.service.FeedEntryServiceImpl;
import fr.magelle.hexagonalrss.core.spi.FeedRepository;
import fr.magelle.hexagonalrss.core.spi.FeedEntryRepository;
import fr.magelle.hexagonalrss.core.spi.impl.MapFeedRepository;
import fr.magelle.hexagonalrss.core.spi.impl.MapFeedEntryRepository;
import fr.magelle.hexagonalrss.core.spi.impl.TestFeedSynchronize;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests of FeedEntryService
 */
public class FeedEntryServiceTest {

    private FeedEntryService feedEntryService;
    private FeedEntryRepository feedEntryRepository;
    private FeedRepository feedRepository;
    private TestFeedSynchronize feedSynchronize;

    @Before
    public void setUp() {
        feedEntryRepository = new MapFeedEntryRepository();
        feedRepository = new MapFeedRepository();
        feedSynchronize = new TestFeedSynchronize();
        feedEntryService = new FeedEntryServiceImpl(feedRepository, feedEntryRepository, feedSynchronize);
    }

    @Test
    public void testGetAllEntries() throws Exception {
        // Setup
        FeedEntry feedEntry1 = new FeedEntry(1L, 1L, "title1", "content1", "url1");
        FeedEntry feedEntry2 = new FeedEntry(2L, 1L, "title2", "content2", "url2");
        feedEntryRepository.save(feedEntry1, feedEntry2);

        // Exercise
        List<FeedEntry> allEntries = feedEntryService.getAllEntries();

        // Verify
        assertThat(allEntries).containsExactly(feedEntry1, feedEntry2);
    }

    @Test
    public void testGetAllEntriesOfFeed() throws Exception {
        // Setup
        FeedEntry feedEntry1 = new FeedEntry(1L, 1L, "title1", "content1", "url1");
        FeedEntry feedEntry2 = new FeedEntry(2L, 2L, "title2", "content2", "url2");
        FeedEntry feedEntry3 = new FeedEntry(3L, 2L, "title3", "content3", "url3");
        feedEntryRepository.save(feedEntry1, feedEntry2, feedEntry3);

        // Exercise
        List<FeedEntry> allEntries = feedEntryService.getAllEntriesOfFeed(2L);

        // Verify
        assertThat(allEntries).containsExactly(feedEntry2, feedEntry3);
    }

    @Test
    public void testGetUnreadEntries() throws Exception {
        // Setup
        FeedEntry feedEntry1 = new FeedEntry(1L, 1L, "title1", "content1", "url1");
        FeedEntry feedEntry2 = new FeedEntry(2L, 2L, "title2", "content2", "url2");
        FeedEntry feedEntry3 = new FeedEntry(3L, 2L, "title3", "content3", "url3");
        feedEntry3.setIsRead(true);
        feedEntryRepository.save(feedEntry1, feedEntry2, feedEntry3);

        // Exercise
        List<FeedEntry> allEntries = feedEntryService.getUnreadEntries();

        // Verify
        assertThat(allEntries).containsExactly(feedEntry1, feedEntry2);
    }

    @Test
    public void testGetUnreadEntriesOfFeed() throws Exception {
        // Setup
        FeedEntry feedEntry1 = new FeedEntry(1L, 1L, "title1", "content1", "url1");
        FeedEntry feedEntry2 = new FeedEntry(2L, 2L, "title2", "content2", "url2");
        FeedEntry feedEntry3 = new FeedEntry(3L, 2L, "title3", "content3", "url3");
        feedEntry3.setIsRead(true);
        feedEntryRepository.save(feedEntry1, feedEntry2, feedEntry3);

        // Exercise
        List<FeedEntry> allEntries = feedEntryService.getUnreadEntriesOfFeed(2L);

        // Verify
        assertThat(allEntries).containsExactly(feedEntry2);
    }

    @Test
    public void testMarkEntryAsRead() throws Exception {
        // Setup
        FeedEntry feedEntry1 = new FeedEntry(1L, 1L, "title1", "content1", "url1");
        FeedEntry feedEntry2 = new FeedEntry(2L, 1L, "title2", "content2", "url2");
        feedEntryRepository.save(feedEntry1, feedEntry2);

        // Exercise
        feedEntryService.markEntryAsRead(1L);

        // Verify
        List<FeedEntry> allEntries = feedEntryRepository.findAll();
        feedEntry1 = allEntries.stream().filter(feedEntry -> feedEntry.getId().equals(1L)).findFirst().get();
        assertThat(feedEntry1.isRead()).isTrue();
        feedEntry2 = allEntries.stream().filter(feedEntry -> feedEntry.getId().equals(2L)).findFirst().get();
        assertThat(feedEntry2.isRead()).isFalse();

    }

    @Test
    public void testMarkAllEntriesOfFeedAsRead() throws Exception {
        // Setup
        FeedEntry feedEntry1 = new FeedEntry(1L, 1L, "title1", "content1", "url1");
        FeedEntry feedEntry2 = new FeedEntry(2L, 1L, "title2", "content2", "url2");
        FeedEntry feedEntry3 = new FeedEntry(3L, 2L, "title3", "content3", "url3");
        feedEntryRepository.save(feedEntry1, feedEntry2, feedEntry3);

        // Exercise
        feedEntryService.markEntriesOfFeedAsRead(1L);

        // Verify
        List<FeedEntry> allEntries = feedEntryRepository.findAll();
        feedEntry1 = allEntries.stream().filter(feedEntry -> feedEntry.getId().equals(1L)).findFirst().get();
        assertThat(feedEntry1.isRead()).isTrue();
        feedEntry2 = allEntries.stream().filter(feedEntry -> feedEntry.getId().equals(2L)).findFirst().get();
        assertThat(feedEntry2.isRead()).isTrue();
        feedEntry3 = allEntries.stream().filter(feedEntry -> feedEntry.getId().equals(3L)).findFirst().get();
        assertThat(feedEntry3.isRead()).isFalse();

    }

    @Test
    public void testRetrieveNewFeeds() throws Exception {
        // Setup
        Feed feed = new Feed("name", "url");
        feedRepository.save(feed);
        FeedEntry feedEntry1 = new FeedEntry(1L, 1L, "title1", "content1", "url1");
        FeedEntry feedEntry2 = new FeedEntry(2L, 1L, "title2", "content2", "url2");
        feedSynchronize.setNewFeedEntries(Arrays.asList(feedEntry1, feedEntry2));

        // Exercise
        feedEntryService.retrieveNewFeeds();

        // Verify
        List<FeedEntry> feedEntries = feedEntryRepository.findAll();
        assertThat(feedEntries).containsExactly(feedEntry1, feedEntry2);
    }

    @Test
    public void testRetrieveNewFeeds_feedId_() throws Exception {
        // Setup
        Feed feed = new Feed("name", "url");
        feed = feedRepository.save(feed);
        FeedEntry feedEntry1 = new FeedEntry(1L, 1L, "title1", "content1", "url1");
        FeedEntry feedEntry2 = new FeedEntry(2L, 1L, "title2", "content2", "url2");
        feedSynchronize.setNewFeedEntries(Arrays.asList(feedEntry1, feedEntry2));

        // Exercise
        feedEntryService.retrieveNewFeeds(feed.getId());

        // Verify
        List<FeedEntry> feedEntries = feedEntryRepository.findAll();
        assertThat(feedEntries).containsExactly(feedEntry1, feedEntry2);
    }
}
