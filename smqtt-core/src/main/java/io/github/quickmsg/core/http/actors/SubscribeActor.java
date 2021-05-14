package io.github.quickmsg.core.http.actors;

import com.alibaba.fastjson.JSON;
import io.github.quickmsg.common.http.HttpActor;
import io.github.quickmsg.common.http.annotation.Router;
import io.github.quickmsg.common.http.enums.HttpType;
import io.github.quickmsg.core.DefaultTransport;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;
import reactor.netty.http.server.HttpServerRequest;
import reactor.netty.http.server.HttpServerResponse;

/**
 * @author luxurong
 */
@Router(value = "/smqtt/subscribe", type = HttpType.POST)
@Slf4j
public class SubscribeActor  implements HttpActor {

    @Override
    public Publisher<Void> doRequest(HttpServerRequest request, HttpServerResponse response) {
        response.addHeader("Content-Type", "application/json");
        return request
                .receive()
                .then(response
                        .sendString(Mono.just(JSON.toJSONString(DefaultTransport.receiveContext.getTopicRegistry().getAllTopics())))
                        .then());
    }
}
