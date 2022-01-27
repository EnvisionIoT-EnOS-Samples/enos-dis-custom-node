package org.dis.demo;

import org.apache.camel.Endpoint;
import org.apache.camel.support.DefaultComponent;

import java.util.Map;

/**
 * Represents the component that manages {@link CustomNodeEndpoint}.
 */
public class CustomNodeComponent extends DefaultComponent {
    
    @Override
    protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
        Endpoint endpoint = new CustomNodeEndpoint(uri, this);
        setProperties(endpoint, parameters);
        return endpoint;
    }
}
