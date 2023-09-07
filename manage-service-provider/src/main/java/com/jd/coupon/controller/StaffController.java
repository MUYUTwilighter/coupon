package com.jd.coupon.controller;

import com.jd.coupon.entity.StaffDto;
import com.jd.coupon.entity.StaffRequestDto;
import com.jd.coupon.exception.ResourceNotFoundException;
import com.jd.coupon.service.StaffRequestService;
import com.jd.coupon.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.List;

/**
 * @author MUYU_Twilighter
 */
@RestController
@RequestMapping("manage/staff")
public class StaffController {
    @Autowired
    private StaffService staffService;

    @GetMapping("info/{name}")
    public StaffDto info(@PathVariable(name = "name") String name) {
        StaffDto staff = staffService.info(name);
        if (staff == null) {
            throw ResourceNotFoundException.INSTANCE;
        }
        return staff;
    }

    @PostMapping("register")
    public void register(HttpServletRequest request,
                         @RequestParam String name,
                         @RequestParam String pwd) {
        String initiator = request.getParameter("initiator");
        staffService.register(initiator, name, pwd);
    }

    @PostMapping("login")
    public void login(@RequestParam String name,
                      @RequestParam String pwd) {
        staffService.login(name, pwd);
    }

    @GetMapping("search")
    public List<StaffDto> search(@RequestParam(required = false) String name,
                                 @RequestParam(required = false) String business,
                                 @RequestParam(required = false) Short auth,
                                 @RequestParam(required = false) Integer page) {
        return this.staffService.search(name, business, auth, page);
    }

    @RestController
    @RequestMapping("manage/staff/request")
    public static class StaffRequestPostController {
        @Autowired
        private StaffService staffService;
        @Autowired
        private StaffRequestService staffRequestService;

        @PostMapping("post/{name}/business")
        public void changeBusiness(HttpServletRequest request,
                                   @PathVariable(name = "name") String name,
                                   @RequestParam String newBusiness) {
            String initiator = request.getParameter("initiator");
            staffService.changeBusiness(initiator, name, newBusiness);
        }

        @PostMapping("post/{name}/auth")
        public void changeAuth(HttpServletRequest request,
                               @PathVariable(name = "name") String name,
                               @RequestParam Byte auth) {
            String initiator = request.getParameter("initiator");
            staffService.changeAuth(initiator, name, auth);
        }

        @PostMapping("post/{name}/delete")
        public void delete(HttpServletRequest request, @PathVariable(name = "name") String name) {
            String initiator = request.getParameter("initiator");
            staffService.delete(initiator, name);
        }

        @PostMapping("withdraw/{id}")
        public void withdraw(@PathVariable(name = "id") Long id) {
            staffRequestService.withdraw(id);
        }
    }

    @RestController
    @RequestMapping("manage/staff/approve")
    public static class StaffRequestApproveController {
        @Autowired
        private StaffRequestService staffRequestService;

        @GetMapping("search")
        public List<StaffRequestDto> search(@RequestParam(required = false) Byte category,
                                            @RequestParam(required = false) Date start,
                                            @RequestParam(required = false) Date end,
                                            @RequestParam(required = false) String initiator,
                                            @RequestParam(required = false) Boolean rejected,
                                            @RequestParam(required = false) Byte nextApprove,
                                            @RequestParam(required = false) Integer page) {
            return staffRequestService.search(category, start, end, initiator, rejected, nextApprove, page);
        }

        @GetMapping("pending/{name}")
        public List<StaffRequestDto> searchSelf(@PathVariable(name = "name") String name,
                                                @RequestParam(required = false) Byte category,
                                                @RequestParam(required = false) Date start,
                                                @RequestParam(required = false) Date end,
                                                @RequestParam(required = false) Boolean rejected,
                                                @RequestParam(required = false) Byte nextApprove,
                                                @RequestParam(required = false) Integer page) {
            return staffRequestService.search(category, start, end, name, rejected, nextApprove, page);
        }

        @GetMapping("{id}")
        public StaffRequestDto find(@PathVariable(name = "id") Long id) {
            return staffRequestService.find(id);
        }

        @PostMapping("{id}/process")
        public void approve(HttpServletRequest request, @PathVariable(name = "id") Long id) {
            Byte auth = Byte.valueOf(request.getParameter("auth"));
            staffRequestService.approve(id, auth);
        }
    }
}
