package main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BurgerService {
    private final Logger LOGGER = LoggerFactory.getLogger(BurgerService.class);
    public void baz(String message) {
        LOGGER.info("SERVICE " + message);
    }
}
