//import org.junit.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import java.util.List;
//import static org.junit.Assert.assertEquals;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class CouponControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    public void testRecommendCoupons() throws Exception {
//        // 使用MockMvc发送POST请求调用selectAll接口
//        MvcResult selectAllResult = mockMvc.perform(MockMvcRequestBuilders.post("/selectAll"))
//                .andReturn();
//        String selectAllResponse = selectAllResult.getResponse().getContentAsString();
//
//        // 解析selectAll接口的响应结果
//        ObjectMapper objectMapper = new ObjectMapper();
//        List<Coupon> couponList = objectMapper.readValue(selectAllResponse, new TypeReference<List<Coupon>>() {});
//
//        // 调用recommendCoupons接口并传递selectAll的结果
//        MvcResult recommendResult = mockMvc.perform(MockMvcRequestBuilders.post("/recommend")
//                        .contentType("application/json")
//                        .content(objectMapper.writeValueAsString(couponList)))
//                .andReturn();
//        String recommendResponse = recommendResult.getResponse().getContentAsString();
//
//        // 解析recommendCoupons接口的响应结果
//        List<Coupon> recommendedCoupons = objectMapper.readValue(recommendResponse, new TypeReference<List<Coupon>>() {});
//
//        // 验证返回的推荐优惠券列表是否符合预期
//        assertEquals(couponList.size(), recommendedCoupons.size());
//        // 进一步验证其他字段的值
//        // ...
//
//    }
//}