package com.neo.ticketingapp;

import com.mongodb.Block;
import com.mongodb.connection.SslSettings;
import com.mongodb.connection.netty.NettyStreamFactoryFactory;
import io.netty.channel.nio.NioEventLoopGroup;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoClientSettingsBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import com.mongodb.Block;
import com.mongodb.connection.SslSettings;
import com.mongodb.connection.SslSettings.Builder;
import com.mongodb.connection.netty.NettyStreamFactoryFactory;

import io.netty.channel.nio.NioEventLoopGroup;
import javax.annotation.PreDestroy;

@SpringBootApplication
public class TicketingappApplication{

	private NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();


	public static void main(String[] args) {

		SpringApplication.run(TicketingappApplication.class, args);
	}

	@Bean
	public MongoClientSettingsBuilderCustomizer sslCustomizer() {

		Block<SslSettings.Builder> sslSettingsBlock = new Block<SslSettings.Builder>() {
			@Override
			public void apply(Builder t) {
				t.applySettings(SslSettings.builder()
						.enabled(true)
						.invalidHostNameAllowed(true)
						.build());
			}
		};

		return clientSettingsBuilder -> clientSettingsBuilder
				.applyToSslSettings(sslSettingsBlock)
				.streamFactoryFactory(NettyStreamFactoryFactory.builder()
						.eventLoopGroup(eventLoopGroup).build());
	}

	@PreDestroy
	public void shutDownEventLoopGroup() {
		eventLoopGroup.shutdownGracefully();
	}

}
