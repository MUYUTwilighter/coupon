package com.jd.coupon.component;

import com.jd.coupon.entity.StaffDto;
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
    private RedisTemplate<String, StaffDto> redisTemplate;

    public String putAndToken(StaffDto staff) {
        String token = RandomString.make(12);
        token = "AU__" + token;
        this.redisTemplate.opsForValue().set(token, staff);
        this.extend(token);
        return token;
    }

    public StaffDto find(String token) {
        StaffDto staff = this.redisTemplate.opsForValue().get(token);
        if (staff != null) {
            this.extend(token);
        }
        return staff;
    }

    private void extend(String key) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 5);
        this.redisTemplate.expireAt(key, calendar.getTime());
    }
}
