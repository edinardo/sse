package com.gameaccount.sse;

import java.time.Duration;
import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping ( "/event" )
public class ServerSentEventsEndpoint {

    private Logger log = LoggerFactory.getLogger(ServerSentEventsEndpoint.class);
    @Value("${message.ofMinutes}")
    private Long minutes;

    @CrossOrigin
    @GetMapping(path = "/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<LocalTime> sse() {
        return Flux
                .interval(Duration.ofMinutes(minutes))
                .log()
                .map(sequence -> LocalTime.now());
    }

}
