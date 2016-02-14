package fr.magelle.hexagonalrss.app;

import fr.magelle.hexagonalrss.app.config.HexagonalRssConfiguration;
import fr.magelle.hexagonalrss.app.config.metrics.InstanceNameHealthCheck;
import fr.magelle.hexagonalrss.core.api.service.FeedService;
import fr.magelle.hexagonalrss.core.business.service.FeedServiceImpl;
import fr.magelle.hexagonalrss.core.spi.FeedCatalog;
import fr.magelle.hexagonalrss.datasource.InMemoryFeedCatalog;
import fr.magelle.hexagonalrss.ws.adapter.CoreFeedToWsFeedMapper;
import fr.magelle.hexagonalrss.ws.adapter.FeedServiceAdapter;
import fr.magelle.hexagonalrss.ws.resource.FeedResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 *
 */
public class HexagonalRssApplication extends Application<HexagonalRssConfiguration> {

    public static void main(String[] args) throws Exception {
        new HexagonalRssApplication().run(args);
    }

    @Override
    public String getName() {
        return "HexagonalRss";
    }

    @Override
    public void initialize(Bootstrap<HexagonalRssConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(HexagonalRssConfiguration configuration, Environment environment) throws Exception {
        final FeedCatalog feedCatalog = new InMemoryFeedCatalog();
        final FeedService feedService = new FeedServiceImpl(feedCatalog);
        final CoreFeedToWsFeedMapper coreFeedToWsFeedMapper =  new CoreFeedToWsFeedMapper();
        final FeedServiceAdapter feedServiceAdapter = new FeedServiceAdapter(feedService, coreFeedToWsFeedMapper);

        final FeedResource feedResource = new FeedResource(feedServiceAdapter);
        environment.jersey().register(feedResource);

        final InstanceNameHealthCheck healthCheck = new InstanceNameHealthCheck(configuration.getInstanceName());
        environment.healthChecks().register("instanceName", healthCheck);
    }
}


