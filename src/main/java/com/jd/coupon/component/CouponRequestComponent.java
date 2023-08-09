package com.jd.coupon.component;

import com.jd.coupon.request.CouponRequest;
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
public class CouponRequestComponent {
    private RedisTemplate<String, CouponRequest> redisTemplate;

    @Resource
    public void setRedisTemplate(RedisTemplate<String, CouponRequest> redisTemplate) {
        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
        RedisSerializer<Object> serializer = new GenericJackson2JsonRedisSerializer();

        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(serializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(serializer);
        this.redisTemplate = redisTemplate;
    }

    public void put(CouponRequest request) {
        String token = RandomString.make(16);
        token = "CR__" + token;
        request.setId(token);
        this.redisTemplate.opsForValue().set(token, request);
        this.extend(token);
    }

    public CouponRequest find(String token) {
        return this.redisTemplate.opsForValue().get(token);
    }

    public List<CouponRequest> query(int page) {
        List<CouponRequest> list = new ArrayList<>(10);
        ScanOptions.ScanOptionsBuilder builder = ScanOptions.scanOptions().count(100);
        try (Cursor<String> cursor = redisTemplate.scan(builder.build())) {
            int i = -1;
            while (cursor.hasNext()) {
                String key = cursor.next();
                ++i;
                if (i < page * 10) {
                    continue;
                }
                if (i < (page + 1) * 10) {
                    CouponRequest request = find(key);
                    list.add(request);
                }
            }
        } catch (Exception ignored) {}
        return list;
    }

    public void delete(String id) {
        this.redisTemplate.delete(id);
    }

    public boolean update(String id, Short approve) {
        CouponRequest request = this.find(id);
        if (request == null) {
            return false;
        }
        this.delete(id);
        request.setApprove(approve);
        this.put(request);
        return true;
    }

    private void extend(String key) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 7);
        this.redisTemplate.expireAt(key, calendar.getTime());
    }
}
