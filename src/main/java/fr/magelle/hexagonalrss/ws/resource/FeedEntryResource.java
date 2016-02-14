package fr.magelle.hexagonalrss.ws.resource;

import com.codahale.metrics.annotation.Timed;
import fr.magelle.hexagonalrss.ws.adapter.service.FeedEntryServiceAdapter;
import fr.magelle.hexagonalrss.ws.api.FeedEntry;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.security.InvalidParameterException;
import java.util.List;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
public class FeedEntryResource {

    private FeedEntryServiceAdapter feedEntryService;

    public FeedEntryResource(FeedEntryServiceAdapter feedEntryService) {
        this.feedEntryService = feedEntryService;
    }

    @GET
    @Path("/feed-entries")
    @Timed
    public List<FeedEntry> getEntries(@QueryParam("feedId") Long feedId,
                                      @QueryParam("unread") boolean unread) {
        if (feedId != null && unread) return feedEntryService.getUnreadEntriesOfFeed(feedId);
        if (feedId != null) return feedEntryService.getAllEntriesOfFeed(feedId);
        if (unread) return feedEntryService.getUnreadEntries();
        return feedEntryService.getAllEntries();
    }

    @PUT
    @Path("/mark-feed-entries-read")
    @Timed
    public void markAsRead(@QueryParam("feedEntryId") Long feedEntryId,
                           @QueryParam("feedId") Long feedId) {
        if (feedEntryId == null && feedId == null) throw new InvalidParameterException("One parameter should be filled");
        if (feedEntryId != null && feedId != null) throw new InvalidParameterException("Only one parameter should be filled");
        if (feedEntryId != null) feedEntryService.markEntryAsRead(feedEntryId);
        else feedEntryService.markEntryAsRead(feedId);
    }

    @POST
    @Path("/synchronize")
    @Timed
    public void synchronize(@QueryParam("feedId") Long feedId) {
        if (feedId != null) feedEntryService.retrieveNewFeeds(feedId);
        else feedEntryService.retrieveNewFeeds();
    }
}
