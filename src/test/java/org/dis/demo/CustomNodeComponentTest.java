package org.dis.demo;

import com.alibaba.fastjson.JSON;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

import java.util.*;

public class CustomNodeComponentTest extends CamelTestSupport {


    @Test
    public void testCustomNode() throws Exception {
        MockEndpoint mock = getMockEndpoint("mock:result");
        mock.expectedMinimumMessageCount(5);

        // Trigger events to subscribers
        simulateEventTrigger();

        mock.await();
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            public void configure() {
                from("timer://foo?repeatCount=1")
                        // 模拟前一个节点的输入
                        .process(new Processor() {
                            @Override
                            public void process(Exchange exchange) throws Exception {
                                Map<String, String> msg = new HashMap<>();
                                msg.put("test", "test");
                                // 设置msg和metadata
                                exchange.getIn().setBody(JSON.toJSONString(msg));
                                exchange.getIn().setHeader("meta", 3);
                            }
                        })
                        .to("custom-node://bar?input=input&select=1&inputNumber=1&password=password")
                        .to("mock:result");
            }
        };
    }

    private void simulateEventTrigger() {
        final TimerTask task = new TimerTask() {
            @Override
            public void run() {
                final Date now = new Date();
            }
        };

        new Timer().scheduleAtFixedRate(task, 1000L, 1000L);
    }
}
