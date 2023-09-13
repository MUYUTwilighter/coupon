package com.jd.coupon.entity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

/**
 * @author MUYU_Twilighter
 */
@Data
public class StaffRequestDto implements Serializable {
    private Long id;
    private Byte category;
    private Date initiate = Date.valueOf(LocalDate.now());
    private Staff initiator;
    private Boolean rejected;
    private Long approval;
    private String staffName;
    private String staffBusiness;
    private Byte staffAuth;

    public String getInitiator() {
        return this.initiator.getName();
    }

    public void setInitiator(String initiator) {
        this.initiator.setName(initiator);
    }

    public static StaffRequestDto of(StaffRequest request) {
        StaffRequestDto requestDto = new StaffRequestDto();
        requestDto.setId(request.getId());
        requestDto.setCategory(request.getCategory());
        requestDto.setInitiate(request.getInitiate());
        requestDto.setInitiator(request.getInitiator());
        requestDto.setRejected(request.getRejected());
        requestDto.setApproval(request.getApproval());
        requestDto.setStaffName(request.getStaffName());
        requestDto.setStaffAuth(requestDto.getStaffAuth());
        return requestDto;
    }

    public static List<StaffRequestDto> of(Iterable<StaffRequest> requests) {
        List<StaffRequestDto> requestDtos = new LinkedList<>();
        for (StaffRequest request : requests) {
            StaffRequestDto requestDto = StaffRequestDto.of(request);
            requestDtos.add(requestDto);
        }
        return requestDtos;
    }
}
