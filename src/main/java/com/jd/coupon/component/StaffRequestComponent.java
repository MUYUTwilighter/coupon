package com.jd.coupon.component;

import com.jd.coupon.request.StaffRequest;
import net.bytebuddy.utility.RandomString;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author MUYU_Twilighter
 */
@Component
public class StaffRequestComponent {
    private RedisTemplate<String, StaffRequest> redisTemplate;

    @Resource
    public void setRedisTemplate(RedisTemplate<String, StaffRequest> redisTemplate) {
        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
        RedisSerializer<Object> serializer = new GenericJackson2JsonRedisSerializer();

        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(serializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(serializer);
        this.redisTemplate = redisTemplate;
    }

    public void put(StaffRequest request) {
        String token = RandomString.make(16);
        token = "SR__" + token;
        request.setId(token);
        this.redisTemplate.opsForValue().set(token, request);
        this.extend(token);
    }

    public StaffRequest get(String id) {
        return this.redisTemplate.opsForValue().get(id);
    }

    public List<StaffRequest> query(int page) {
        List<StaffRequest> list = new ArrayList<>(10);
        ScanOptions.ScanOptionsBuilder builder = ScanOptions.scanOptions().count(100).match("SR__*");
        try (Cursor<String> cursor = redisTemplate.scan(builder.build())) {
            int i = -1;
            while (cursor.hasNext()) {
                String id = cursor.next();
                ++i;
                if (i < page * 10) {
                    continue;
                }
                if (i < (page + 1) * 10) {
                    StaffRequest request = get(id);
                    list.add(request);
                }
            }
        } catch (Exception ignored) {}
        return list;
    }

    private void extend(String id) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 7);
        this.redisTemplate.expireAt(id, calendar.getTime());
    }

    public void delete(String id) {
        this.redisTemplate.delete(id);
    }
}
