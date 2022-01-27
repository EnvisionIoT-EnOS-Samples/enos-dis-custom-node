package org.dis.demo;

import lombok.Data;
import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.spi.Metadata;
import org.apache.camel.spi.UriEndpoint;
import org.apache.camel.spi.UriParam;
import org.apache.camel.spi.UriPath;
import org.apache.camel.support.DefaultEndpoint;

import java.util.concurrent.ExecutorService;

/**
 * Represents a CustomNode endpoint.
 */
@Data
@UriEndpoint(firstVersion = "1.0-SNAPSHOT", scheme = "custom-node", title = "CustomNode", syntax="custom-node:pathValue",
             consumerClass = CustomNodeConsumer.class, label = "custom")
public class CustomNodeEndpoint extends DefaultEndpoint {
    @UriPath(description = "pathValue")
    @Metadata(required = true)
    private String pathValue;

    @UriParam(description = "input")
    private String input;
    @UriParam(description = "select")
    private Double select;
    @UriParam(description = "Switch")
    private Boolean Switch;
    @UriParam(description = "checkbox")
    private Boolean checkbox;
    @UriParam(description = "checkbox2")
    private Boolean checkbox2;
    @UriParam(description = "textarea")
    private String textarea;
    @UriParam(description = "inputNumber")
    private Double inputNumber;
    @UriParam(description = "inputNumber2")
    private Double inputNumber2;
    @UriParam(description = "password")
    private String password;
    @UriParam(description = "rangePicker")
    private String rangePicker;
    @UriParam(description = "timePicker")
    private String timePicker;

    public CustomNodeEndpoint() {
    }

    public CustomNodeEndpoint(String uri, CustomNodeComponent component) {
        super(uri, component);
    }

    @Override
    public Producer createProducer() throws Exception {
        return new CustomNodeProducer(this);
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        return new CustomNodeConsumer(this, processor);
    }

    public boolean isSingleton() {
        return true;
    }

    public ExecutorService createExecutor() {
        // TODO: Delete me when you implementy your custom component
        return getCamelContext().getExecutorServiceManager().newSingleThreadExecutor(this, "CustomNodeConsumer");
    }
}
