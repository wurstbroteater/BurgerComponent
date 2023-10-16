package component.location;

import component.restaurant.BurgerComponent;
import org.apache.camel.Category;
import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.spi.Metadata;
import org.apache.camel.spi.UriEndpoint;
import org.apache.camel.spi.UriParam;
import org.apache.camel.spi.UriPath;
import org.apache.camel.support.DefaultEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;

/**
 * Burger component which does bla bla.
 * <p>
 * TODO: Update one line description above what the component does, and update Category.
 */
@UriEndpoint(firstVersion = "1.0-SNAPSHOT", scheme = "location", title = "Location", syntax = "location:name",
        producerOnly = true, category = {Category.DATABASE})
public class LocationEndpoint extends DefaultEndpoint {
    @UriPath
    @Metadata(required = true)
    private String name;
    @UriParam(defaultValue = "10")
    private int option = 10;

    private final Logger LOGGER = LoggerFactory.getLogger(LocationEndpoint.class);

    public LocationEndpoint() {
    }

    public LocationEndpoint(String uri, LocationComponent component) {
        super(uri, component);
        LOGGER.info("LocationEndpoint created");
    }

    @Override
    public Producer createProducer() throws Exception {
        return new LocationProducer(this);
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        return null;
    }

    /**
     * Some description of this option, and what it does
     */
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * Some description of this option, and what it does
     */
    public void setOption(int option) {
        this.option = option;
    }

    public int getOption() {
        return option;
    }

    public ExecutorService createExecutor() {
        // TODO: Delete me when you implemented your custom component
        return getCamelContext().getExecutorServiceManager().newSingleThreadExecutor(this, "LocationConsumer");
    }
}
