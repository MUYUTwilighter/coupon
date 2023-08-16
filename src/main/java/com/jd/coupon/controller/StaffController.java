package com.jd.coupon.controller;

import com.jd.coupon.component.AuthComponent;
import com.jd.coupon.component.StaffRequestComponent;
import com.jd.coupon.entity.Staff;
import com.jd.coupon.exception.BadArgumentException;
import com.jd.coupon.exception.PermissionDenyException;
import com.jd.coupon.exception.ResourceNotFoundException;
import com.jd.coupon.request.StaffRequest;
import com.jd.coupon.service.StaffService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author MUYU_Twilighter<br/>
 * [2023.08.08] [MUYU_Twilighter] All methods are tested and passed
 */
@RestController
@RequestMapping("staff")
@Api("User management and access interface")
public class StaffController {
    @Autowired
    private StaffService service;
    @Autowired
    private StaffRequestComponent staffComponent;
    @Autowired
    private AuthComponent authComponent;

    private static final String ARG_INVALID = "ARG_INVALID";
    private static final String INTERNAL_FAIL = "INTERNAL_FAIL";

    /* PASSED */
    @GetMapping("login")
    @ApiOperation("Log in server with username and password")
    public String login(
        @RequestParam("name") @ApiParam(value = "name", required = true) String name,
        @RequestParam("pwd") @ApiParam(value = "password", required = true) String pwd) {
        Boolean result = service.verify(name, pwd);
        if (result) {
            return authComponent.token(name);
        } else {
            throw BadArgumentException.INSTANCE;
        }
    }

    /* PASSED */
    @PostMapping("register")
    @ApiOperation("Register a user with name and password")
    public Boolean register(
        @RequestParam("name") @ApiParam(value = "username", required = true) String name,
        @RequestParam("pwd") @ApiParam(value = "password", required = true) String pwd) {
        return service.register(name, pwd);
    }

    /* PASSED */
    @PostMapping("destroy/request")
    @ApiOperation(value = "Post a request to destroy a user with username")
    public Boolean destroyRequest(
        @RequestParam("token") @ApiParam(value = "token used for authentication", required = true) String token,
        @RequestParam(value = "name", required = false) @ApiParam("name") String name) {
        Staff initiator = findByToken(token, Staff.AUTH_STAFF);
        Staff target = name == null ? initiator : service.find(name);
        if (target == Staff.EMPTY) {
            throw ResourceNotFoundException.INSTANCE;
        }
        if (initiator != target && !initiator.beyond(Staff.AUTH_ADMIN))  {
            throw PermissionDenyException.INSTANCE;
        }
        StaffRequest request = StaffRequest.initDestroy(initiator.getName(), target);
        staffComponent.put(request);
        return true;
    }

    /* PASSED */
    @DeleteMapping("destroy/approve")
    @ApiOperation(value = "Approve a request to destroy a user")
    public Boolean destroyApprove(
        @RequestParam("token") @ApiParam("token") String token,
        @RequestParam("id") @ApiParam("request id") String id) {
        findByToken(token, Staff.AUTH_ADMIN);
        StaffRequest request = staffComponent.get(id);
        if (request == null) {
            throw ResourceNotFoundException.INSTANCE;
        } else {
            staffComponent.delete(id);
            return service.destroy(request.getTarget().getName());
        }
    }

    /* PASSED */
    @GetMapping("request/list")
    @ApiOperation(value = "Get a list of requests.")
    public List<StaffRequest> requestList(
        @RequestParam("token") @ApiParam(value = "token", required = true) String token,
        @RequestParam(value = "page", defaultValue = "0") @ApiParam("list page") Integer page) {
        findByToken(token, Staff.AUTH_ADMIN);
        return staffComponent.query(page);
    }

    /* PASSED */
    @PutMapping("pwd")
    @ApiOperation(value = "Change password of a user")
    public Boolean changePwd(
        @RequestParam("token") @ApiParam("token") String token,
        @RequestParam("oldPwd") @ApiParam("old password") String oldPwd,
        @RequestParam("newPwd") @ApiParam("new password") String newPwd) {
        String username = authComponent.find(token);
        if (username == null) {
            throw PermissionDenyException.INSTANCE;
        }
        if (service.verify(username, oldPwd)) {
            return service.modify(username, null, null, newPwd);
        } else {
            return false;
        }
    }

    /* PASSED */
    @PostMapping("info/request")
    @ApiOperation(value = "Post a request to change a user's info")
    public Boolean infoRequest(
        @RequestParam("token") @ApiParam("token") String token,
        @RequestParam(value = "auth", required = false) @ApiParam("authentication level") Short auth,
        @RequestParam(value = "business", required = false) @ApiParam("business the user belongs to") String business) {
        Staff operator = findByToken(token);
        operator.setAuth(auth);
        operator.setBusiness(business);
        StaffRequest request = StaffRequest.initInfo(operator);
        staffComponent.put(request);
        return true;
    }

    /* PASSED */
    @PutMapping("info/approve")
    @ApiOperation(value = "Approve a request to change a user's info")
    public Boolean infoApprove(
        @RequestParam("token") @ApiParam("token") String token,
        @RequestParam("id") @ApiParam("request id") String id) {
        findByToken(token, Staff.AUTH_ADMIN);
        StaffRequest request = staffComponent.get(id);
        if (request == null) {
            throw ResourceNotFoundException.INSTANCE;
        } else {
            staffComponent.delete(id);
            Staff updated = request.getTarget();
            String name = updated.getName();
            String business = updated.getBusiness();
            Short auth = updated.getAuth();
            return service.modify(name, business, auth, null);
        }
    }

    /* PASSED */
    @GetMapping("find")
    @ApiOperation(value = "Find a user with specific uid")
    public Staff find(
        @RequestParam("token") @ApiParam("token") String token,
        @RequestParam("username") @ApiParam("username") String name) {
        return service.find(name);
    }

    /* PASSED */
    @GetMapping("search")
    @ApiOperation(value = "Search users matches username")
    public List<Staff> search(
        @RequestParam("token") @ApiParam("token") String token,
        @RequestParam(value = "name", defaultValue = "%") @ApiParam("username") String username,
        @RequestParam(value = "business", defaultValue = "%") @ApiParam("business") String business,
        @RequestParam(value = "page", defaultValue = "0") @ApiParam("page") Integer page) {
        findByToken(token, Staff.AUTH_STAFF);
        return service.search(username, business, null, page);
    }

    private Staff findByToken(String token) {
        String name = authComponent.find(token);
        if (name == null) {
            throw PermissionDenyException.INSTANCE;
        } else {
            Staff staff = service.find(name);
            if (staff == Staff.EMPTY) {
                throw PermissionDenyException.INSTANCE;
            }
            return staff;
        }
    }

    private Staff findByToken(String token, Short auth) {
        String name = authComponent.find(token);
        if (name == null) {
            throw PermissionDenyException.INSTANCE;
        } else {
            Staff staff = service.find(name, auth);
            if (staff == Staff.EMPTY) {
                throw PermissionDenyException.INSTANCE;
            }
            return staff;
        }
    }
}
