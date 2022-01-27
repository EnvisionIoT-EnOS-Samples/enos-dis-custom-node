package org.dis.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.camel.Exchange;
import org.apache.camel.support.DefaultProducer;
import org.dis.demo.data.Params;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Optional;

/**
 * The CustomNode producer.
 */
public class CustomNodeProducer extends DefaultProducer {
    private static final Logger LOG = LoggerFactory.getLogger(CustomNodeProducer.class);
    private CustomNodeEndpoint endpoint;

    public CustomNodeProducer(CustomNodeEndpoint endpoint) {
        super(endpoint);
        this.endpoint = endpoint;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        // 前一个组件的msg和metadata
        String msg = exchange.getIn().getBody(String.class);
        Map<String, Object> metadata = exchange.getIn().getHeaders();
        log.info("msg:{},metadata:{}", msg, metadata);

        Params params = Params.builder()
                .input(endpoint.getInput())
                .select(endpoint.getSelect())
                .Switch(endpoint.getSwitch())
                .checkbox(endpoint.getCheckbox())
                .checkbox2(endpoint.getCheckbox2())
                .textarea(endpoint.getTextarea())
                .inputNumber(endpoint.getInputNumber())
                .inputNumber2(endpoint.getInputNumber2())
                .password(endpoint.getPassword())
                .rangePicker(Optional.ofNullable(endpoint.getRangePicker()).map(e -> JSON.parseArray(e, Object.class)).orElse(null))
                .timePicker(endpoint.getTimePicker())
                .routeUri(endpoint.getEndpointUri())
                .build();
        String body = JSON.toJSONString(params, SerializerFeature.WriteMapNullValue);
        log.info("now msg is:{}", body);
        // 传给下一个组件的msg
        exchange.getMessage().setBody(body);
        // 修改metadata
        exchange.getMessage().setHeader("meta1", "hello");
    }

}
