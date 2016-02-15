package fr.magelle.hexagonalrss.datasource.db.adapter;


import fr.magelle.hexagonalrss.core.dto.Feed;
import fr.magelle.hexagonalrss.core.spi.FeedRepository;
import fr.magelle.hexagonalrss.datasource.db.dao.FeedDAO;

import java.util.List;

public class FeedRepositoryAdapter implements FeedRepository {

    private FeedDAO feedDAO;

    public FeedRepositoryAdapter(FeedDAO feedDAO) {
        this.feedDAO = feedDAO;
    }

    @Override
    public Feed save(Feed feed) {
        Long id = feedDAO.insert(feed.getName(), feed.getUrl(), feed.getLastUpdate());
        return this.findById(id);
    }

    @Override
    public Feed update(Feed feed) {
        feedDAO.update(feed.getId(), feed.getName(), feed.getUrl(), feed.getLastUpdate());
        return this.findById(feed.getId());
    }

    @Override
    public void delete(Long feedId) {
        feedDAO.deleteById(feedId);
    }

    @Override
    public List<Feed> findAll() {
        return feedDAO.findAll();
    }

    @Override
    public Feed findById(Long id) {
        return feedDAO.findById(id);
    }
}
