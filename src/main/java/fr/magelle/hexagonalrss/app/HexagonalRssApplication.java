package fr.magelle.hexagonalrss.app;

import fr.magelle.hexagonalrss.app.config.HexagonalRssConfiguration;
import fr.magelle.hexagonalrss.app.config.metrics.InstanceNameHealthCheck;
import fr.magelle.hexagonalrss.core.api.service.FeedEntryService;
import fr.magelle.hexagonalrss.core.api.service.FeedService;
import fr.magelle.hexagonalrss.core.business.service.FeedEntryServiceImpl;
import fr.magelle.hexagonalrss.core.business.service.FeedServiceImpl;
import fr.magelle.hexagonalrss.core.spi.FeedEntryRepository;
import fr.magelle.hexagonalrss.core.spi.FeedRepository;
import fr.magelle.hexagonalrss.core.spi.FeedSynchronize;
import fr.magelle.hexagonalrss.datasource.db.adapter.FeedEntryRepositoryAdapter;
import fr.magelle.hexagonalrss.datasource.db.adapter.FeedRepositoryAdapter;
import fr.magelle.hexagonalrss.datasource.db.dao.FeedDAO;
import fr.magelle.hexagonalrss.datasource.db.dao.FeedEntryDAO;
import fr.magelle.hexagonalrss.datasource.rss.RssFeedSynchronize;
import fr.magelle.hexagonalrss.ws.adapter.mapper.CoreFeedEntryToWsFeedEntryMapper;
import fr.magelle.hexagonalrss.ws.adapter.mapper.CoreFeedToWsFeedMapper;
import fr.magelle.hexagonalrss.ws.adapter.service.FeedEntryServiceAdapter;
import fr.magelle.hexagonalrss.ws.adapter.service.FeedServiceAdapter;
import fr.magelle.hexagonalrss.ws.resource.FeedEntryResource;
import fr.magelle.hexagonalrss.ws.resource.FeedResource;
import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

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

        // Database
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "h2");
        final FeedDAO feedDAO = jdbi.onDemand(FeedDAO.class);
        final FeedEntryDAO feedEntryDAO = jdbi.onDemand(FeedEntryDAO.class);

        // DataSources
        final FeedRepository feedRepository = new FeedRepositoryAdapter(feedDAO);
        final FeedEntryRepository feedEntryRepository = new FeedEntryRepositoryAdapter(feedEntryDAO);
        final FeedSynchronize feedSynchronize = new RssFeedSynchronize();

        // Core services
        final FeedService feedService = new FeedServiceImpl(feedRepository);
        final FeedEntryService feedEntryService = new FeedEntryServiceImpl(feedRepository, feedEntryRepository, feedSynchronize);

        // Object Mappers
        final CoreFeedToWsFeedMapper coreFeedToWsFeedMapper =  new CoreFeedToWsFeedMapper();
        final CoreFeedEntryToWsFeedEntryMapper coreFeedEntryToWsFeedEntryMapper = new CoreFeedEntryToWsFeedEntryMapper();

        // Service Adapters
        final FeedServiceAdapter feedServiceAdapter = new FeedServiceAdapter(feedService, coreFeedToWsFeedMapper);
        final FeedEntryServiceAdapter feedEntryServiceAdapter = new FeedEntryServiceAdapter(feedEntryService, coreFeedEntryToWsFeedEntryMapper);

        // WS Resources
        final FeedResource feedResource = new FeedResource(feedServiceAdapter);
        environment.jersey().register(feedResource);

        final FeedEntryResource feedEntryResource = new FeedEntryResource(feedEntryServiceAdapter);
        environment.jersey().register(feedEntryResource);

        final InstanceNameHealthCheck healthCheck = new InstanceNameHealthCheck(configuration.getInstanceName());
        environment.healthChecks().register("instanceName", healthCheck);
    }
}


