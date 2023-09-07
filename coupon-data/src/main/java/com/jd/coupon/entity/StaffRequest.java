package com.jd.coupon.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

/**
 * @author MUYU_Twilighter
 */
@Entity
@Table(name = "staff_request")
public class StaffRequest implements StaffRequestDto {
    public static Byte CATE_REGISTER = 0x00;
    public static Byte CATE_DELETE = 0x01;
    public static Byte CATE_CHANGE_BUS = 0x02;
    public static Byte CATE_CHANGE_AUTH = 0x03;

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
    @JoinTable(name = "staff")
    private String initiator;
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

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Byte getCategory() {
        return category;
    }

    @Override
    public void setCategory(Byte category) {
        this.category = category;
    }

    @Override
    public Date getInitiate() {
        return initiate;
    }

    @Override
    public void setInitiate(Date initiate) {
        this.initiate = initiate;
    }

    @Override
    public String getInitiator() {
        return initiator;
    }

    @Override
    public void setInitiator(String initiator) {
        this.initiator = initiator;
    }

    @Override
    public Boolean getRejected() {
        return rejected;
    }

    @Override
    public void setRejected(Boolean rejected) {
        this.rejected = rejected;
    }

    @Override
    public Long getApproval() {
        return approval;
    }

    @Override
    public void setApproval(Long approval) {
        this.approval = approval;
    }

    @Override
    public String getStaffName() {
        return staffName;
    }

    @Override
    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    @Override
    public String getStaffBusiness() {
        return staffBusiness;
    }

    @Override
    public void setStaffBusiness(String staffBusiness) {
        this.staffBusiness = staffBusiness;
    }

    @Override
    public Byte getStaffAuth() {
        return staffAuth;
    }

    @Override
    public void setStaffAuth(Byte staffAuth) {
        this.staffAuth = staffAuth;
    }

    public String getStaffPwd() {
        return staffPwd;
    }

    public void setStaffPwd(String staffPwd) {
        this.staffPwd = staffPwd;
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
        request.setCategory(CATE_REGISTER);
        request.setInitiator(initiator);
        request.setApproval(APPR_ONCE);
        request.setStaffName(staffName);
        request.setStaffPwd(staffPwd);
        return request;
    }

    public static StaffRequest initDelete(@NotNull String initiator, @NotNull String staffName) {
        StaffRequest request = new StaffRequest();
        request.setCategory(CATE_DELETE);
        request.setInitiator(initiator);
        request.setApproval(APPR_ONCE);
        request.setStaffName(staffName);
        return request;
    }

    public static StaffRequest initChangeBus(@NotNull String initiator, @NotNull String staffName, @NotNull String staffBusiness) {
        StaffRequest request = new StaffRequest();
        request.setCategory(CATE_CHANGE_BUS);
        request.setInitiator(initiator);
        request.setApproval(APPR_ONCE);
        request.setStaffName(staffName);
        request.setStaffBusiness(staffBusiness);
        return request;
    }

    public static StaffRequest initChangeAuth(@NotNull String initiator, @NotNull String staffName, @NotNull Byte staffAuth) {
        StaffRequest request = new StaffRequest();
        request.setCategory(CATE_CHANGE_AUTH);
        request.setInitiator(initiator);
        request.setApproval(APPR_ONCE);
        request.setStaffName(staffName);
        request.setStaffAuth(staffAuth);
        return request;
    }
}
