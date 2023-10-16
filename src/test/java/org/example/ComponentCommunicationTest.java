package org.example;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class ComponentCommunicationTest extends CamelTestSupport {

    private final EventBusHelper eventBusHelper = EventBusHelper.getInstance();

    @Test
    public void testBurgerComponent() throws Exception {
        MockEndpoint mock = getMockEndpoint("mock:result");
        mock.expectedMinimumMessageCount(5);

        // Trigger events to subscribers
        simulateEventTrigger();

        mock.await();
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            public void configure() {
                //from restaurant to location
                from("burger://restaurant:burgerme?order=4")
                        /*
                        .process(new Processor() {
                            private final Logger INNER_LOGGER = LoggerFactory.getLogger(Processor.class);
                            @Override
                            public void process(Exchange exchange) throws Exception {
                                INNER_LOGGER.info("I process the Burgers");
                                String message = exchange.getIn().getBody(String.class);
                                message += " with processed meat";

                                exchange.getOut().setBody(message);
                            }
                        })
                        */
                        .to("location:home")
                        //.to("log:out")
                        .to("mock:result");
            }
        };
    }

    private void simulateEventTrigger() {
        final TimerTask task = new TimerTask() {
            @Override
            public void run() {
                final Date now = new Date();
                // publish events to the event bus
                eventBusHelper.publish(now);
            }
        };

        new Timer().scheduleAtFixedRate(task, 1000L, 1000L);
    }
}
