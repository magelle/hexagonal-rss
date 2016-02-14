package fr.magelle.hexagonalrss.ws.resource;

import com.codahale.metrics.annotation.Timed;
import fr.magelle.hexagonalrss.ws.adapter.FeedServiceAdapter;
import fr.magelle.hexagonalrss.ws.api.Feed;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
public class FeedResource {

    private FeedServiceAdapter feedService;

    public FeedResource(FeedServiceAdapter feedService) {
        this.feedService = feedService;
    }

    @GET
    @Path("/feed")
    @Timed
    public Feed getFeed(@QueryParam("id") Long id) {
        return feedService.get(id);
    }

    @GET
    @Path("/feeds")
    @Timed
    public List<Feed> getFeeds() {
        return feedService.getAll();
    }

    @POST
    @Path("/feed/add")
    @Timed
    public Feed addFeed(Feed feed) {
        return feedService.add(feed);
    }

    @PUT
    @Path("/feed/update")
    @Timed
    public Feed updateFeed(@QueryParam("id") Long id, Feed feed) {
        return feedService.update(feed);
    }

    @DELETE
    @Path("/feed/delete")
    @Timed
    public void deleteFeed(@QueryParam("id") Long id) {
        feedService.delete(id);
    }
}
