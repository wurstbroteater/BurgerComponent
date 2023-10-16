package component.location;

import org.apache.camel.Endpoint;
import org.apache.camel.support.DefaultComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@org.apache.camel.spi.annotations.Component("location")
public class LocationComponent extends DefaultComponent {
    private static final Logger LOGGER = LoggerFactory.getLogger(LocationComponent.class);

    @Override
    protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
        //LOGGER.info("uri " + uri + " remaining " + remaining);
        Endpoint endpoint = endpoint = new LocationEndpoint(uri, this);

        setProperties(endpoint, parameters);
        return endpoint;
    }
}
