package org.dadada.evele.config;

import org.dadada.evele.pubsub.EveleRedisSubscriber;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class RedisConfig {
    /**
     * 단일 Topic 사용을 위한 Bean 설정
     */
    @Bean
    public ChannelTopic channelTopic() {
        return new ChannelTopic("chatroom");
    }
    /**
     * redis에 발행(publish)된 메시지 처리를 위한 리스너 설정
     */
    @Bean
    public RedisMessageListenerContainer redisMessageListener(RedisConnectionFactory connectionFactory,
                                                              MessageListenerAdapter listenerAdapter,
                                                              ChannelTopic channelTopic) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, channelTopic);
        return container;
    }
    /**
     * 실제 메시지를 처리하는 subscriber 설정 추가
     */
    @Bean
    public MessageListenerAdapter listenerAdapter(EveleRedisSubscriber subscriber) {
        return new MessageListenerAdapter(subscriber, "sendMessage");
    }
}