package org.example;

import burger.Ingredients;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;

import java.util.*;

import static burger.Ingredients.*;

public class ComponentCommunicationTest extends CamelTestSupport {

    private final EventBusHelper eventBusHelper = EventBusHelper.getInstance();

    @Test
    public void wublerandoTest() throws Exception {
        MockEndpoint mock = getMockEndpoint("mock:result");
        mock.expectedMinimumMessageCount(2);

        // Trigger events to subscribers
        simulateEventTrigger();

        mock.await();
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            public void configure() {
                System.out.println();
                //from restaurant to location
                from("burger://restaurant:burgerme?amount=4")
                        .to("location:home")
                        .to("log:out")
                        .to("mock:result");
            }
        };
    }

    private void simulateEventTrigger() {
        final TimerTask task = new TimerTask() {
            @Override
            public void run() {
                final List<Ingredients> ingredients = new ArrayList<>(List.of(BEEF, CHEESE, BEEF, CHEESE, ONION, LETTUCE, TOMATO, KETCHUP, MUSTARD));
                // publish events to the event bus
                eventBusHelper.publish(ingredients);
            }
        };

        new Timer().scheduleAtFixedRate(task, 1000L, 1000L);
    }
}
