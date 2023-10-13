package component.restaurant;

import org.apache.camel.Exchange;
import org.apache.camel.support.DefaultProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BurgerProducer extends DefaultProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(BurgerProducer.class);
    private BurgerEndpoint endpoint;

    public BurgerProducer(BurgerEndpoint endpoint) {
        super(endpoint);
        this.endpoint = endpoint;
    }

    public void process(Exchange exchange) throws Exception {
        LOGGER.error("BURGER Received message but should only produce messages: " + exchange.getIn().getBody());
    }

}
