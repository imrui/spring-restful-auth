package com.xxicon.config;

import com.xxicon.serializer.FastJson2JsonRedisSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    @Value("${redis.cache.expireTime}")
    private long expireTime;

    @Bean
    @SuppressWarnings("rawtypes")
    public RedisSerializer fastJson2JsonRedisSerializer() {
        return new FastJson2JsonRedisSerializer<>(Object.class);
    }

    @Bean
    @SuppressWarnings("rawtypes")
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory, RedisSerializer fastJson2JsonRedisSerializer) {
        StringRedisTemplate template = new StringRedisTemplate(factory);
        template.setValueSerializer(fastJson2JsonRedisSerializer);
        template.setHashValueSerializer(fastJson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

    @Bean
    @SuppressWarnings("rawtypes")
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        cacheManager.setDefaultExpiration(this.expireTime);
        return cacheManager;
    }

    /**
     *  自定义的缓存key的生成策略</br>
     *  若想使用这个key</br>
     *  只需要讲注解上keyGenerator的值设置为customKeyGenerator即可</br>
     * @return 自定义策略生成的key
     */
    @Bean
    public KeyGenerator customKeyGenerator() {
        return (o, method, objects) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(o.getClass().getName());
            sb.append(method.getName());
            for (Object obj : objects) {
                sb.append(obj.toString());
            }
            System.out.println("### redis key: " + sb.toString()); // todo: del
            return sb.toString();
        };
    }

}
