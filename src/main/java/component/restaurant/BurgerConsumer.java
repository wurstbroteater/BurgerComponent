package component.restaurant;

import burger.Burger;
import burger.Ingredients;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.support.DefaultConsumer;
import org.example.EventBusHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

import static burger.Ingredients.*;

public class BurgerConsumer extends DefaultConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(BurgerConsumer.class);

    private final BurgerEndpoint endpoint;
    private final EventBusHelper eventBusHelper;

    private ExecutorService executorService;
    private int counter = 0;

    private final Cook cook;

    public BurgerConsumer(BurgerEndpoint endpoint, Processor processor) {
        super(endpoint, processor);
        this.endpoint = endpoint;
        eventBusHelper = EventBusHelper.getInstance();

        final Map<List<Ingredients>, String> knowledge = Map.of(
                List.of(BEEF, CHEESE, BEEF, CHEESE, ONION, LETTUCE, TOMATO, KETCHUP, MUSTARD), "cheeseburger"
        );
        this.cook = new Cook("Pedro", knowledge);
    }

    @Override
    protected void doStart() throws Exception {
        super.doStart();

        // start a single threaded pool to monitor events
        executorService = endpoint.createExecutor();

        // submit task to the thread pool
        executorService.submit(() -> {
            // subscribe to an event
            eventBusHelper.subscribe(this::onEventListener);
        });
    }

    @Override
    protected void doStop() throws Exception {
        super.doStop();

        // shutdown the thread pool gracefully
        getEndpoint().getCamelContext().getExecutorServiceManager().shutdownGraceful(executorService);
    }

    private void onEventListener(final List<Ingredients> event) {
        final Exchange exchange = createExchange(false);
        LOGGER.info("Received new published ingredients list!");
        final List<Burger> foodDelivery = new ArrayList<>();
        for (int i = 1; i <= endpoint.getAmount(); i++) {
            foodDelivery.add(cook.makeFood(event));
        }
        exchange.getIn().setBody(foodDelivery);
        LOGGER.info("Restaurant %s processed its %d order and created %d %s".formatted(endpoint.getName(), ++counter, endpoint.getAmount(), cook.getRecipes().get(event)));
        try {
            // send message to next processor in the route
            getProcessor().process(exchange);
        } catch (Exception e) {
            exchange.setException(e);
        } finally {
            if (exchange.getException() != null) {
                getExceptionHandler().handleException("Error processing exchange", exchange, exchange.getException());
            }
            releaseExchange(exchange, false);
        }
    }


}
