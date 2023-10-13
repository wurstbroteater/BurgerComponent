package main;

/*
import org.apache.camel.impl.DefaultCamelContext;
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {

        final DefaultRegistry registry = new DefaultRegistry();
        BurgerService service = new BurgerService();
        registry.bind("BurgerService", service);

        try (CamelContext context = new DefaultCamelContext(registry)) {
            context.addComponent("burger", new BurgerComponent());
            context.addRoutes(new BurgerRoute());
            context.start();
            final ProducerTemplate burgerProducer = context.createProducerTemplate();
            burgerProducer.sendBody("burger:myBurger", "I produced a Burger");
            Thread.sleep(2000);
            context.stop();

            context.addRoutes(new BurgerRoute());
            context.start();
            final ProducerTemplate burgerProducer = context.createProducerTemplate();
            burgerProducer.sendBody("burger:myBurger", "I produced a Burger");


            final ConsumerTemplate burgerConsumer = context.createConsumerTemplate();
            String message = burgerConsumer.receiveBody("seda:end", String.class);
            logger.info(message);

            Thread.sleep(2000);
            context.stop();


        }

    }
}
*/