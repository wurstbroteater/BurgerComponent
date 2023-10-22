package bean;

import burger.Burger;
import burger.Ingredients;
import org.apache.camel.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ReceiverService {

    private final Logger LOGGER = LoggerFactory.getLogger(ReceiverService.class);

    @Handler
    public void receiveFood(List<Burger> burgers) {
        LOGGER.info("ReceiverService received " + burgers);
    }

}
