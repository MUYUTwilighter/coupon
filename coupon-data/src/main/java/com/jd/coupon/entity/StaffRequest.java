package com.jd.coupon.entity;

import com.jd.coupon.util.RequestUtil;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

/**
 * @author MUYU_Twilighter
 */
@Data
@Entity
@Table(name = "staff_request")
public class StaffRequest implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;
    @Column(name = "category")
    private Byte category;
    @Column(name = "initiate")
    private Date initiate = Date.valueOf(LocalDate.now());
    @ManyToOne
    @JoinColumn(name = "initiator", referencedColumnName = "name")
    private Staff initiator;
    @Column(name = "rejected")
    private Boolean rejected;
    @Column(name = "approval")
    private Long approval;
    @Column(name = "staff_name", length = 32)
    private String staffName;
    @Column(name = "staff_business", length = 32)
    private String staffBusiness;
    @Column(name = "staff_auth")
    private Byte staffAuth;
    @Column(name = "staff_pwd", length = 32)
    private String staffPwd;

    public String getInitiator() {
        return initiator.getName();
    }

    public void setInitiator(String initiator) {
        this.initiator.setName(initiator);
    }

    public Staff extractStaff() {
        Staff staff = new Staff();
        staff.setName(staffName);
        staff.setBusiness(staffBusiness);
        staff.setAuth(staffAuth);
        staff.setPwd(staffPwd);
        return staff;
    }

    public static StaffRequest initRegister(@NotNull String initiator,
                                     @NotNull String staffName,
                                     @NotNull String staffPwd) {
        StaffRequest request = new StaffRequest();
        request.setCategory(RequestUtil.CATE_STAFF_REGISTER);
        request.setInitiator(initiator);
        request.setApproval(RequestUtil.APPR_ONCE);
        request.setStaffName(staffName);
        request.setStaffPwd(staffPwd);
        return request;
    }

    public static StaffRequest initDelete(@NotNull String initiator, @NotNull String staffName) {
        StaffRequest request = new StaffRequest();
        request.setCategory(RequestUtil.CATE_STAFF_DELETE);
        request.setInitiator(initiator);
        request.setApproval(RequestUtil.APPR_ONCE);
        request.setStaffName(staffName);
        return request;
    }

    public static StaffRequest initChangeBus(@NotNull String initiator, @NotNull String staffName, @NotNull String staffBusiness) {
        StaffRequest request = new StaffRequest();
        request.setCategory(RequestUtil.CATE_STAFF_CHANGE_BUS);
        request.setInitiator(initiator);
        request.setApproval(RequestUtil.APPR_ONCE);
        request.setStaffName(staffName);
        request.setStaffBusiness(staffBusiness);
        return request;
    }

    public static StaffRequest initChangeAuth(@NotNull String initiator, @NotNull String staffName, @NotNull Byte staffAuth) {
        StaffRequest request = new StaffRequest();
        request.setCategory(RequestUtil.CATE_STAFF_CHANGE_AUTH);
        request.setInitiator(initiator);
        request.setApproval(RequestUtil.APPR_ONCE);
        request.setStaffName(staffName);
        request.setStaffAuth(staffAuth);
        return request;
    }

    public Byte nextApproval() {
        return (byte) (this.getApproval() & 0xFF);
    }

    public void rollApproval() {
        Long approval = this.getApproval();
        approval >>>= 8;
        setApproval(approval);
    }

    public Boolean hasApproved() {
        return this.getApproval() == 0;
    }
}
