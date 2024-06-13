package com.project.pc.service.seat;

import com.project.pc.dto.SeatDTO;

import java.util.List;

public interface SeatService {

    List<SeatDTO> seatAll();

    boolean updateSeat(Long seat, String id);

    void logoutSeat(Long seat);
}
