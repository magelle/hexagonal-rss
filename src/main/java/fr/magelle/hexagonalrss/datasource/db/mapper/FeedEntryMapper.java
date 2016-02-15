package fr.magelle.hexagonalrss.datasource.db.mapper;

import fr.magelle.hexagonalrss.core.dto.FeedEntry;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FeedEntryMapper implements ResultSetMapper<FeedEntry> {

    @Override
    public FeedEntry map(int i, ResultSet r, StatementContext statementContext) throws SQLException {
        return new FeedEntry(
            r.getLong("id"),
            r.getLong("feed_id"),
            r.getString("title"),
            r.getString("content"),
            r.getString("url"),
            r.getBoolean("is_read")
        );
    }
}
