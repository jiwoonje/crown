package com.crown.reservation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;



public interface ReservationRepository extends JpaRepository<Reservation, Integer>
{

	//예약번호로 전체 조회
	List<Reservation> findByReservationId(int id);

	//예약번호로 특정 1개 조회
	Reservation findByReservationIdDetail(int id);

	//멤버번호로 조회
	Reservation findByMemberIdDetail(int mid);

	//시설번호로 조회
	Reservation findByFacilityIdDetail(int fid);

	//예약번호 & 맴버번호로 조회
	Reservation findByReservationIdAndMemberId(int id, int mid);

	//예약번호 & 시설번호로 조회
	Reservation findByReservationIdAndFacilityId(int id, int fid);
}
