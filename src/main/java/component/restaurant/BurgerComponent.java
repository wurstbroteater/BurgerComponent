package component.restaurant;

import burger.Ingredients;
import org.apache.camel.Endpoint;
import org.apache.camel.support.DefaultComponent;
import org.apache.camel.util.StringHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

@org.apache.camel.spi.annotations.Component("burger")
public class BurgerComponent extends DefaultComponent {
    private static final Logger LOGGER = LoggerFactory.getLogger(BurgerComponent.class);

    @Override
    protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
        LOGGER.info("uri " + uri + " remaining " + remaining);
        Endpoint endpoint = new BurgerEndpoint(uri, this, StringHelper.after(remaining, ":"));
        setProperties(endpoint, parameters);
        return endpoint;
    }
}
