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

    private final EventBusHelper eventBusHelper = EventBusHelper.getInstance();

    @Test
    public void testCustomNode() throws Exception {
        MockEndpoint mock = getMockEndpoint("mock:result");
        mock.expectedMinimumMessageCount(1);

        // Trigger events to subscribers
        simulateEventTrigger();

        mock.await();
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            public void configure() {
                from("custom-node://foo")
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
                        .to("custom-node://bar?input=input&select=1&Switch=true&checkbox=true&checkbox2=true&textarea=textarea&inputNumber=1&inputNumber2=1&password=password&rangePicker=[\"2021-02-11 14:42\", \"2021-02-19 14:42\"]&timePicker=timePicker")
                        .to("mock:result");
            }
        };
    }

    private void simulateEventTrigger() {
        final TimerTask task = new TimerTask() {
            @Override
            public void run() {
                final Date now = new Date();
                // publish events to the event bus
                eventBusHelper.publish(now);
            }
        };

        new Timer().scheduleAtFixedRate(task, 1000L, 1000L);
    }
}
