package bean;

import burger.Burger;
import org.apache.camel.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReceiverService {

    private final Logger LOGGER = LoggerFactory.getLogger(ReceiverService.class);

    @Handler
    public void receiveFood(Burger burger) {
        LOGGER.info("ReceiverService received " + burger);
    }
}
