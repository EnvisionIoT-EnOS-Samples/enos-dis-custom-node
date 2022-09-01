package org.dis.demo.process;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;

public class CustomNodeConsumer {

    private static final Logger log = LoggerFactory.getLogger(CustomNodeConsumer.class);

    private CustomNodeEndpoint endpoint;

    public CustomNodeConsumer(CustomNodeEndpoint endpoint) {
        this.endpoint = endpoint;
    }

    public void doStart(Consumer<Exchange> consumer) {
        //1.create exchange
        Exchange exchange = endpoint.createExchange();
        //2.invoke route
        consumer.accept(exchange);
    }

    /**
     * do sth after route is done
     */
    public void doAfterDone(boolean doneSync) {

    }

}
