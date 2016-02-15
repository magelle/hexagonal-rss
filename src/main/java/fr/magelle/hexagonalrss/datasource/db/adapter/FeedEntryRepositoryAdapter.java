package fr.magelle.hexagonalrss.datasource.db.adapter;


import fr.magelle.hexagonalrss.core.dto.FeedEntry;
import fr.magelle.hexagonalrss.core.spi.FeedEntryRepository;
import fr.magelle.hexagonalrss.datasource.db.dao.FeedEntryDAO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class FeedEntryRepositoryAdapter implements FeedEntryRepository {

    private FeedEntryDAO feedEntryDAO;

    public FeedEntryRepositoryAdapter(FeedEntryDAO feedEntryDAO) {
        this.feedEntryDAO = feedEntryDAO;
    }

    @Override
    public FeedEntry save(FeedEntry entry) {
        Long id = feedEntryDAO.insert(
                entry.getFeedId(),
                entry.getTitle(),
                entry.getContent(),
                entry.getUrl(),
                entry.isRead()
        );
        return feedEntryDAO.findById(id);
    }

    @Override
    public List<FeedEntry> save(FeedEntry... entries) {
        return this.save(Arrays.asList(entries));
    }

    @Override
    public List<FeedEntry> save(Collection<FeedEntry> entries) {
        List<Long> feedIds = new ArrayList<>(entries.size());
        List<String> titles = new ArrayList<>(entries.size());
        List<String> contents = new ArrayList<>(entries.size());
        List<String> urls = new ArrayList<>(entries.size());
        List<Boolean> isReads = new ArrayList<>(entries.size());
        entries.forEach(entry -> {
            feedIds.add(entry.getId());
            titles.add(entry.getTitle());
            contents.add(entry.getContent());
            urls.add(entry.getUrl());
            isReads.add(entry.isRead());
        });
        List<Long> ids = feedEntryDAO.insert(feedIds, titles, contents, urls, isReads);
        return feedEntryDAO.findByIdIn(ids);
    }

    @Override
    public FeedEntry markAsReadById(Long entryId) {
        feedEntryDAO.updateIsRead(entryId, true);
        return feedEntryDAO.findById(entryId);
    }

    @Override
    public List<FeedEntry> markAsReadByFeedId(Long feedId) {
        feedEntryDAO.updateIsReadByFeedId(feedId, true);
        return feedEntryDAO.findByFeedId(feedId);
    }

    @Override
    public void delete(Long feedEntryId) {
        feedEntryDAO.deleteById(feedEntryId);
    }

    @Override
    public List<FeedEntry> findAll() {
        return feedEntryDAO.findAll();
    }

    @Override
    public List<FeedEntry> findByFeedId(Long feedId) {
        return feedEntryDAO.findByFeedId(feedId);
    }

    @Override
    public List<FeedEntry> findByIsReadFalse() {
        return feedEntryDAO.findByIsRead(false);
    }

    @Override
    public List<FeedEntry> findByFeedIdAndIsReadFalse(Long feedId) {
        return feedEntryDAO.findByFeedIdAndIsRead(feedId, false);
    }
}
