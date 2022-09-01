package org.dis.demo.component;

import org.apache.camel.Endpoint;
import org.apache.camel.support.DefaultComponent;
import org.dis.demo.process.CustomNodeEndpoint;

import java.util.Map;

/**
 * Represents the component that manages {@link CustomNodeEndpoint}.
 */
public class OrchCustomNodeComponent extends DefaultComponent {
    
    protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
        Endpoint endpoint = new CustomNodeEndpoint(uri, this);
        setProperties(endpoint, parameters);
        return endpoint;
    }
}
