package component.location;

import burger.Burger;
import org.apache.camel.Exchange;
import org.apache.camel.support.DefaultProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LocationProducer extends DefaultProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(LocationProducer.class);
    private LocationEndpoint endpoint;

    public LocationProducer(LocationEndpoint endpoint) {
        super(endpoint);
        this.endpoint = endpoint;
    }

    public void process(Exchange exchange) throws Exception {
        final Burger receivedFood = (Burger) exchange.getIn().getBody();
        LOGGER.info("LOCATION Received message  " + receivedFood);
    }

}
