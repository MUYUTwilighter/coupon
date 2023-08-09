package com.jd.coupon.component;

import net.bytebuddy.utility.RandomString;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Calendar;

/**
 * @author MUYU_Twilighter
 */
@Component
public class AuthComponent {
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    public String token(String username) {
        String token = RandomString.make(16);
        token = "AU__" + token;
        this.redisTemplate.opsForValue().set(token, username);
        this.extend(token);
        return token;
    }

    public String find(String token) {
        String username = this.redisTemplate.opsForValue().get(token);
        if (username != null) {
            this.extend(token);
        }
        return username;
    }

    private void extend(String key) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 5);
        this.redisTemplate.expireAt(key, calendar.getTime());
    }
}
