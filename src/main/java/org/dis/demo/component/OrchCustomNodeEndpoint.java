package org.dis.demo.component;

import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.support.DefaultEndpoint;

import java.util.concurrent.ExecutorService;

/**
 * Represents a CustomNode endpoint.
 */
public class OrchCustomNodeEndpoint extends DefaultEndpoint {

    public OrchCustomNodeEndpoint(String uri, OrchCustomNodeComponent component) {
        super(uri, component);
    }

    public Producer createProducer() throws Exception {
        return new OrchCustomNodeProducer(this);
    }

    public Consumer createConsumer(Processor processor) throws Exception {
        return new OrchCustomNodeConsumer(this, processor);
    }

    public boolean isSingleton() {
        return true;
    }

    public ExecutorService createExecutor() {
        // TODO: Delete me when you implementy your custom component
        return getCamelContext().getExecutorServiceManager().newSingleThreadExecutor(this, "CustomNodeConsumer");
    }
}
