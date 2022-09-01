package org.dis.demo.process;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.camel.Exchange;
import org.dis.demo.process.data.Params;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class CustomNodeProcessor {

    private static final Logger log = LoggerFactory.getLogger(CustomNodeProcessor.class);

    private CustomNodeEndpoint endpoint;

    public CustomNodeProcessor(CustomNodeEndpoint endpoint) {
        this.endpoint = endpoint;
    }

    public void process(Exchange exchange, Map metadata) throws Exception {
        // 前一个组件的msg和metadata
        String msg = exchange.getIn().getBody(String.class);
        log.info("msg:{},metadata:{}", msg, metadata);

        Params params = Params.builder()
                .input(endpoint.getInput())
                .select(endpoint.getSelect())
                .inputNumber(endpoint.getInputNumber())
                .password(endpoint.getPassword())
                .routeUri(endpoint.getEndpointUri())
                .build();
        String body = JSON.toJSONString(params, SerializerFeature.WriteMapNullValue);
        log.info("now msg is:{}", body);
        // 传给下一个组件的msg
        exchange.getMessage().setBody(body);
        // 修改metadata
        exchange.getMessage().setHeader("meta1", "hello");
    }


    /**
     * do sth after the route is failed
     *
     * @param exchange
     */
    public void doAfterFailer(Exchange exchange, Map metadata) {

    }

    /**
     * do sth after the route is completed
     *
     * @param exchange
     */
    public void doAfterComplete(Exchange exchange, Map metadata) {

    }

}
