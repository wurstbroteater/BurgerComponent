package component;

import org.apache.camel.Endpoint;
import org.apache.camel.support.DefaultComponent;

import java.util.Map;

@org.apache.camel.spi.annotations.Component("burger")
public class BurgerComponent extends DefaultComponent {
    @Override
    protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
        Endpoint endpoint = new BurgerEndpoint(uri, this);
        setProperties(endpoint, parameters);
        return endpoint;
    }
}
