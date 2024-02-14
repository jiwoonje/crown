package com.crown.room;

import com.crown.exception.OutOfRcntException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="Room")
@Getter
@Setter
@ToString
public class Room
{
	@Id
	@Column(name="Room_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id; //방 구분 아이디

	@Column(name="Room_rname", nullable=false)
	private String rname; // 방 이름

	@Column(name="Room_rtype", nullable=false)
	private String rtype; // 방 종류

	@Column(name="Room_rcnt", nullable=false)
	private int rcnt; // 방 수량

	//방 수정
	public void updateRoom(RoomDTO roomDTO)
	{
		this.rname = roomDTO.getRname();
		this.rtype = roomDTO.getRtype();
		this.rcnt = roomDTO.getRcnt();
	}

	//방 갯수 증가
	public void addCnt(int rcnt)
	{
		this.rcnt += rcnt;
	}

	//방 갯수 감소
	public void removeCnt(int rcnt)
	{
		int restCnt = this.rcnt-rcnt;
		if(restCnt<0)
		{
			throw new OutOfRcntException("방 예약이 불가능 합니다."
					+ " (현재 잔여 방: " + this.rcnt + "개)");
		}
	}
}
