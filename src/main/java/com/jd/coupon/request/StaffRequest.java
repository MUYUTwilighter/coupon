package com.jd.coupon.request;

import com.jd.coupon.entity.Staff;
import springfox.documentation.annotations.ApiIgnore;

import java.io.Serializable;

/**
 * @author MUYU_Twilighter
 */
@ApiIgnore
public class StaffRequest implements Serializable {
    public static final Short DESTROY = 1;
    public static final Short CHG_INFO = 2;

    private String id;
    private Short category;
    private Staff target;
    private String initiator;
    private Short approve;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Short getCategory() {
        return category;
    }

    public void setCategory(Short category) {
        this.category = category;
    }

    public Staff getTarget() {
        return target;
    }

    public void setTarget(Staff target) {
        this.target = target;
    }

    public String getInitiator() {
        return initiator;
    }

    public void setInitiator(String initiator) {
        this.initiator = initiator;
    }

    public Short getApprove() {
        return approve;
    }

    public void setApprove(Short approve) {
        this.approve = approve;
    }

    public static StaffRequest initDestroy(String initiator, Staff target) {
        StaffRequest request = new StaffRequest();
        request.setCategory(StaffRequest.DESTROY);
        request.setInitiator(initiator);
        request.setApprove((short) 0);
        request.setTarget(target);
        return request;
    }

    public static StaffRequest initInfo(Staff initiator) {
        Staff target = new Staff();
        target.setName(initiator.getName());
        target.setBusiness(initiator.getBusiness());
        target.setAuth(initiator.getAuth());

        StaffRequest request = new StaffRequest();
        request.setInitiator(initiator.getName());
        request.setCategory(CHG_INFO);
        request.setTarget(target);
        request.setApprove((short) 0);
        return request;
    }
}
