package com.crown.reservation;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationDTO
{
	//예약번호
	private int id;

	//회원번호
	private int mid;

	//시설번호
	private int fid;

	//예약 시작날짜
    private LocalDateTime sregTime;

    //예약 종료날짜
    private LocalDateTime eregTime;

    //예약 인원 수
    private int cnt;


}
