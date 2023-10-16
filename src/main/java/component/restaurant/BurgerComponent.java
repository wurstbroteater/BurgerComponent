package component.restaurant;

import org.apache.camel.Endpoint;
import org.apache.camel.support.DefaultComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@org.apache.camel.spi.annotations.Component("burger")
public class BurgerComponent extends DefaultComponent {
    private static final Logger LOGGER = LoggerFactory.getLogger(BurgerComponent.class);

    @Override
    protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
        LOGGER.info("uri " + uri + " remaining " + remaining);
        Endpoint endpoint = new BurgerEndpoint(uri, this);

        setProperties(endpoint, parameters);
        return endpoint;
    }
}
