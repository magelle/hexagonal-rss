package fr.magelle.hexagonalrss.datasource.db.dao;

import fr.magelle.hexagonalrss.core.dto.FeedEntry;
import fr.magelle.hexagonalrss.datasource.db.mapper.FeedEntryMapper;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(FeedEntryMapper.class)
public interface FeedEntryDAO {

    @SqlUpdate("insert into feed_entry(feed_id, title, content, url, is_read) " +
               "values(:feed_id, :title, :content, :url, :is_read)")
    @GetGeneratedKeys
    Long insert(@Bind("feed_id") Long feedId,
                @Bind("title") String title,
                @Bind("content") String content,
                @Bind("url") String url,
                @Bind("is_read") boolean isRead);

    @SqlBatch("insert into feed_entry(feed_id, title, content, url, is_read) " +
            "values(:feed_id, :title, :content, :url, :is_read)")
    @GetGeneratedKeys
    List<Long> insert(@Bind("feed_id") List<Long> feedId,
                @Bind("title") List<String> title,
                @Bind("content") List<String> content,
                @Bind("url") List<String> url,
                @Bind("is_read") List<Boolean> isRead);

    @SqlUpdate("deleteById from feed_entry where id = :id")
    void deleteById(@Bind("id") Long id);

    @SqlUpdate("update feed_entry set :is_read = true where id = :id")
    void updateIsRead(@Bind("id") Long id, @Bind("is_read") boolean isRead);

    @SqlUpdate("update feed_entry set :is_read = true where feed_id = :feedId")
    void updateIsReadByFeedId(@Bind("feedId") Long id, @Bind("is_read") boolean isRead);

    @SqlQuery("select id, feed_id, title, content, url, is_read from feed_entry")
    List<FeedEntry> findAll();

    @SqlQuery("select id, feed_id, title, content, url, is_read from feed_entry where id = :id")
    FeedEntry findById(@Bind("id") Long id);

    @SqlQuery("select id, feed_id, title, content, url, is_read from feed_entry where id in :ids")
    List<FeedEntry> findByIdIn(List<Long> ids);

    @SqlQuery("select id, feed_id, title, content, url, is_read from feed_entry where feed_id = :feedId")
    List<FeedEntry> findByFeedId(@Bind("feedId") Long id);

    @SqlQuery("select id, feed_id, title, content, url, is_read from feed_entry where feed_id = :feedId and is_read = :isRead")
    List<FeedEntry> findByFeedIdAndIsRead(@Bind("feedId") Long id, @Bind("isRead") boolean isRead);

    @SqlQuery("select id, feed_id, title, content, url, is_read from feed_entry where is_read = :isRead")
    List<FeedEntry> findByIsRead(@Bind("isRead") boolean isRead);
}
