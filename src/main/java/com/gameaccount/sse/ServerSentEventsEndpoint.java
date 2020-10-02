package com.gameaccount.sse;

import java.time.Duration;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping ( "/event" )
public class ServerSentEventsEndpoint {

    @Value("${message.ofMinutes}")
    private Long minutes;

    @CrossOrigin
    @GetMapping(path = "/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<LocalTime>> sse() {
        Flux<ServerSentEvent<LocalTime>> data = Flux
                .interval(Duration.ofMinutes(minutes))
                .map(sequence -> ServerSentEvent.<LocalTime>builder().data(LocalTime.now()).build());
        Flux<ServerSentEvent<LocalTime>> keepalive = Flux
                .interval(Duration.ofSeconds(15))
                .map(sequence -> ServerSentEvent.<LocalTime>builder().comment("keepalive").build());
        return Flux.merge(data, keepalive).log();
    }
    
}
