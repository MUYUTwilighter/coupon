package com.jd.coupon.entity;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * @author MUYU_Twilighter
 */
@Data
public class StaffDto {
    private String name = "default";
    private Byte auth;
    private String business;

    public Boolean hasAuth(Byte auth) {
        Byte authThis = this.getAuth();
        if (authThis == null) {
            return false;
        } else {
            return auth == null || authThis >= auth;
        }
    }

    public static StaffDto of(Staff staff) {
        StaffDto staffDto = new StaffDto();
        staffDto.setName(staff.getName());
        staffDto.setAuth(staff.getAuth());
        staffDto.setBusiness(staff.getBusiness());
        return staffDto;
    }

    public static List<StaffDto> of(Iterable<Staff> staffs) {
        List<StaffDto> staffDtos = new LinkedList<>();
        for (Staff staff : staffs) {
            StaffDto staffDto = StaffDto.of(staff);
            staffDtos.add(staffDto);
        }
        return staffDtos;
    }
}
