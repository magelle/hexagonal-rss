package fr.magelle.hexagonalrss.datasource.db.dao;

import fr.magelle.hexagonalrss.core.dto.Feed;
import fr.magelle.hexagonalrss.datasource.db.mapper.FeedMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.time.LocalDateTime;
import java.util.List;

@RegisterMapper(FeedMapper.class)
public interface FeedDAO {

    @SqlUpdate("insert into feed(name, url, last_update) values(:name, :url, :lastUpdate)")
    @GetGeneratedKeys
    Long insert(@Bind("name") String name,
                @Bind("url") String url,
                @Bind("lastUpdate") LocalDateTime lastUpdate);

    @SqlUpdate("update feed set name = :name, url = :url, last_update = :lastUpdate where id = :id")
    void update(@Bind("id") Long id,
                @Bind("name") String name,
                @Bind("url") String url,
                @Bind("lastUpdate") LocalDateTime lastUpdate);

    @SqlUpdate("deleteById from feed where id = :id")
    void deleteById(@Bind("id") Long id);

    @SqlQuery("select id, name, url, last_update from feed where id = :id")
    Feed findById(@Bind("id") Long id);

    @SqlQuery("select id, name, url, last_update from feed")
    List<Feed> findAll();
}
