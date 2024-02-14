package com.crown.reservation;

import java.time.LocalDateTime;

import com.crown.Entity.CommonEntity;
import com.crown.facility.Facility;
import com.crown.member.Member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="Reservation")
@Getter
@Setter
public class Reservation extends CommonEntity
{
	//예약번호
	@Id
	@Column(name = "Reservation_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	//회원번호
	private Member member;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="Member_mid")
    private int mid = member.getSeq();

	//시설번호
    private Facility facility;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="Facility_fid")
	private int fid = facility.getFid();

	//예약 시작날짜
    @Column(name="sregTime", nullable = false)
    private LocalDateTime sregTime;

    //예약 종료날짜
    @Column(name="eregTime", nullable = false)
    private LocalDateTime eregTime;

    //예약 인원 수
    @Column(name="cnt", nullable = false)
    private int cnt;

    //예약 생성 method
    public static Reservation createReservation(ReservationDTO reservationDTO)
    {
    	Reservation reservation = new Reservation();
    	reservation.setMid(reservationDTO.getMid());
    	reservation.setFid(reservationDTO.getFid());
    	reservation.setSregTime(reservationDTO.getSregTime());
    	reservation.setEregTime(reservationDTO.getEregTime());
    	reservation.setCnt(reservationDTO.getCnt());
    	return reservation;
    }

    //예약 업데이트
    public void updateReservation(ReservationDTO reservationDTO)
    {
    	this.mid = reservationDTO.getMid();
    	this.fid = reservationDTO.getFid();
    	this.sregTime = reservationDTO.getSregTime();
    	this.eregTime = reservationDTO.getEregTime();
    	this.cnt = reservationDTO.getCnt();
    }

    //예약 삭제


}
