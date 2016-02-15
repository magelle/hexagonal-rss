package fr.magelle.hexagonalrss.datasource.db.mapper;

import fr.magelle.hexagonalrss.core.dto.Feed;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FeedMapper implements ResultSetMapper<Feed> {

    public Feed map(int index, ResultSet r, StatementContext ctx) throws SQLException
    {
        return new Feed(
                r.getLong("id"),
                r.getString("name"),
                r.getString("url"),
                r.getTimestamp("last_update") != null ? r.getTimestamp("last_update").toLocalDateTime() : null
        );
    }

}
