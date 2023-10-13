package component.location;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.support.DefaultConsumer;
import org.example.EventBusHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;

public class LocationConsumer extends DefaultConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(LocationConsumer.class);
    private final LocationEndpoint endpoint;
    private final EventBusHelper eventBusHelper;

    private ExecutorService executorService;
    private int counter = 0;

    public LocationConsumer(LocationEndpoint endpoint, Processor processor) {
        super(endpoint, processor);
        this.endpoint = endpoint;
        eventBusHelper = EventBusHelper.getInstance();
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

    private void onEventListener(final Object event) {
        final Exchange exchange = createExchange(false);

        LOGGER.error("Location was instructed to create message but this should not happen because location only receives messages!");
        //exchange.getIn().setBody("Created " + (++counter) + " Location " + event);

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
