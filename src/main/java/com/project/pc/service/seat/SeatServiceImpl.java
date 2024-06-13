package com.project.pc.service.seat;

import com.project.pc.domain.Member;
import com.project.pc.domain.Seat;
import com.project.pc.dto.SeatDTO;
import com.project.pc.repository.MemberRepository;
import com.project.pc.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;

    private final MemberRepository memberRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<SeatDTO> seatAll() {
        List<SeatDTO> list = new ArrayList<>();

        seatRepository.findAll().forEach(seat -> {
            SeatDTO seatDTO = modelMapper.map(seat, SeatDTO.class);

            list.add(seatDTO);
        });

        return list;
    }

    @Override
    public boolean updateSeat(Long seat, String id) {
        Seat result = seatRepository.findById(seat).orElseThrow();

        Member member = memberRepository.findById(id).orElseThrow();

        LocalTime time = LocalTime.of(0, 0, 0);

        if (member.getTime().equals(time)) {
            return false;
        } else {
            result.loginUse(id);

            seatRepository.save(result);

            return true;

        }

    }

    @Override
    public void logoutSeat(Long seat) {
        Seat result = seatRepository.findById(seat).orElseThrow();

        result.endOfUse();

        seatRepository.save(result);
    }
}
