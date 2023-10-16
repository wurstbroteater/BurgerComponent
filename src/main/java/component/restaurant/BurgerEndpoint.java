package component.restaurant;

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
@UriEndpoint(firstVersion = "1.0-SNAPSHOT", scheme = "burger", title = "Burger", syntax = "burger:restaurant:name",
        consumerOnly = true, category = {Category.DATABASE})
public class BurgerEndpoint extends DefaultEndpoint {
    @UriPath
    @Metadata(required = true)
    private String name;
    @UriParam(defaultValue = "42")
    private int order;

    private final Logger LOGGER = LoggerFactory.getLogger(BurgerEndpoint.class);

    public BurgerEndpoint() {
    }

    public BurgerEndpoint(String uri, BurgerComponent component) {
        super(uri, component);
        LOGGER.info("BurgerEndpoint created");
    }

    @Override
    public Producer createProducer() throws Exception {
        return null;
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        Consumer consumer = new BurgerConsumer(this, processor);
        configureConsumer(consumer);
        return consumer;
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
    public void setOrder(int order) {
        this.order = order;
    }

    public int getOrder() {
        return order;
    }

    public ExecutorService createExecutor() {
        // TODO: Delete me when you implemented your custom component
        return getCamelContext().getExecutorServiceManager().newSingleThreadExecutor(this, "BurgerConsumer");
    }
}
