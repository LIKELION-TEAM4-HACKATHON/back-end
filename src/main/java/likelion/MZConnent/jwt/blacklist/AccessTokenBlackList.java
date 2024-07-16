package likelion.MZConnent.jwt.blacklist;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class AccessTokenBlackList {
    private final RedisTemplate<String, Object> redisTemplate;

    @Value("${jwt.access-token-validity-in-seconds}")
    private Long accessTokenTimeoutInSeconds;

    public void setBlackList(String key, Object o) {
        // Jackson2JsonRedisSerializer를 사용하여 객체 -> JSON(직렬화)
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(o.getClass()));
        // 주어진 key, value를 redis에 저장하고 만료시간 설정
        redisTemplate.opsForValue().set(key, o, accessTokenTimeoutInSeconds, TimeUnit.SECONDS);
    }

    public Object getBlackList(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public boolean isTokenBlackList(String key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }
}
