package org.dis.demo.component;

import org.apache.camel.Exchange;
import org.apache.camel.support.DefaultProducer;
import org.apache.camel.support.SynchronizationAdapter;
import org.dis.demo.process.CustomNodeEndpoint;
import org.dis.demo.process.CustomNodeProcessor;

/**
 * The CustomNode producer.
 */
public class OrchCustomNodeProducer extends DefaultProducer {

    private CustomNodeEndpoint endpoint;

    private CustomNodeProcessor customProcessor;

    public OrchCustomNodeProducer(OrchCustomNodeEndpoint endpoint) {
        super(endpoint);
        this.endpoint = (CustomNodeEndpoint) endpoint;
        customProcessor = new CustomNodeProcessor(this.endpoint);
    }

    public void process(Exchange exchange) throws Exception {
        customProcessor.process(exchange, exchange.getIn().getHeaders());
        exchange.addOnCompletion(new SynchronizationAdapter() {
              @Override
              public void onFailure(Exchange exchange) {
                  customProcessor.doAfterFailer(exchange, exchange.getIn().getHeaders());
              }

              @Override
              public void onComplete(Exchange exchange) {
                 customProcessor.doAfterComplete(exchange, exchange.getIn().getHeaders());
              }
        });
    }

}
