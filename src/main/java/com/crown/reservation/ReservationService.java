package com.crown.reservation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crown.facility.FacilityRepository;
import com.crown.member.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@Service
@Transactional				// 클래스 내부의 메소드가 하나의 트랜잭션으로 처리하도록 함.
@RequiredArgsConstructor
public class ReservationService
{
	private final MemberRepository memberRepository;
	private final FacilityRepository facilityRepository;
	private final ReservationRepository reservationRepository;

	//예약 등록
	public int createReservation(ReservationDTO reservationDTO) throws Exception
	{
		Reservation reservation = new Reservation();
		reservation.createReservation(reservationDTO);

		return reservation.getId();
	}

	//예약 수정
	public int updateReservation(ReservationDTO reservationDTO) throws Exception
	{
		Reservation reservation = reservationRepository.findByReservationIdDetail(reservationDTO.getId());
		reservation.updateReservation(reservationDTO);
		return reservation.getId();
	}


	//예약 삭제
	public int deleteReservation(ReservationDTO reservationDTO) throws Exception
	{
		Reservation reservation = reservationRepository.findByReservationIdDetail(reservationDTO.getId());
		reservationRepository.deleteById(reservationDTO.getId());
		return reservation.getId();
	}
}
