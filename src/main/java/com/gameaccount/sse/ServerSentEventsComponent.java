package com.gameaccount.sse;

import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

import reactor.netty.http.HttpProtocol;
import reactor.netty.http.server.ProxyProtocolSupportType;

@Component
public class ServerSentEventsComponent implements WebServerFactoryCustomizer<NettyReactiveWebServerFactory>
{

    @Override
    public void customize( NettyReactiveWebServerFactory factory )
    {
        factory.addServerCustomizers( serverCustomizer -> serverCustomizer
                .protocol( HttpProtocol.H2C, HttpProtocol.HTTP11 )
                .proxyProtocol( ProxyProtocolSupportType.AUTO ));
    }

}
