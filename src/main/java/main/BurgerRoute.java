package main;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.support.SimpleRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BurgerRoute extends RouteBuilder {
    private final Logger LOGGER = LoggerFactory.getLogger(BurgerRoute.class);


    @Override
    public void configure() throws Exception {
        LOGGER.info("Here will be many different Burgers!");
        from("burger:myBurgerService#baz")
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
                .to("log:output");
    }
}
