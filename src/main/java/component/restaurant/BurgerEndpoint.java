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
import burger.Ingredients;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * Burger component which does bla bla.
 * <p>
 * TODO: Update one line description above what the component does, and update Category.
 */
@UriEndpoint(firstVersion = "1.0-SNAPSHOT", scheme = "burger", title = "Burger", syntax = "burger:restaurant:name",
        consumerOnly = true, category = {Category.SOCIAL})
public class BurgerEndpoint extends DefaultEndpoint {
    private final Logger LOGGER = LoggerFactory.getLogger(BurgerEndpoint.class);
    @UriPath(label = "common")
    @Metadata(required = true)
    private String name;
    @UriParam(label = "common", defaultValue = "42")
    private int amount = 42;

    public BurgerEndpoint(String uri, BurgerComponent component, String name) {
        super(uri, component);
        this.name = name;
        LOGGER.info("BurgerEndpoint initialized");
    }


    @Override
    public Producer createProducer() throws Exception {
        return null;
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        Consumer consumer = new BurgerConsumer(this, processor);
        configureConsumer(consumer);
        LOGGER.info("BurgerEndpoint created consumer");
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
    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }


    public ExecutorService createExecutor() {
        // TODO: Delete me when you implemented your custom component
        return getCamelContext().getExecutorServiceManager().newSingleThreadExecutor(this, "BurgerConsumer");
    }
}
