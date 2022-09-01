package org.dis.demo.component;

import org.apache.camel.AsyncCallback;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.support.DefaultConsumer;
import org.dis.demo.process.CustomNodeConsumer;
import org.dis.demo.process.CustomNodeEndpoint;

import java.util.function.Consumer;

/**
 * The CustomNode consumer.
 */
public class OrchCustomNodeConsumer extends DefaultConsumer {


    private final CustomNodeEndpoint endpoint;

    public OrchCustomNodeConsumer(OrchCustomNodeEndpoint endpoint, Processor processor) {
        super(endpoint, processor);
        this.endpoint = (CustomNodeEndpoint)endpoint;
    }

    @Override
    protected void doStart() throws Exception {
        super.doStart();
        CustomNodeConsumer customConsumer = new CustomNodeConsumer(endpoint);
        Consumer<Exchange> consumer = (exchange) -> {
        getAsyncProcessor().process(exchange, new AsyncCallback() {
             @Override
             public void done(boolean doneSync) {
                 customConsumer.doAfterDone(doneSync);
             }
          });
        };
        customConsumer.doStart(consumer);

    }

    @Override
    protected void doStop() throws Exception {
        super.doStop();
    }


}
