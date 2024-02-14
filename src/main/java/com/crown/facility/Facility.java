package com.crown.facility;

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
@Table(name="Facility")
@Getter
@Setter
@ToString
public class Facility
{
	@Id
	@Column(name="Facility_fid")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int fid; //시설 구분 아이디

	@Column(name="Facility_fname", nullable=false)
	private String fname; //시설 이름

	@Column(name="Facility_ftype", nullable=false)
	private String ftype; //시설 종류

	@Column(name="Facility_fcnt", nullable=false)
	private int fcnt; //시설 수량

	//시설 수정
	public void updateFacility(FacilityDTO facilityDTO)
	{
		this.fname = facilityDTO.getFname();
		this.ftype = facilityDTO.getFtype();
		this.fcnt = facilityDTO.getFcnt();
	}

	//시설 갯수 증가
	public void addCnt(int fcnt)
	{
		this.fcnt += fcnt;
	}

	//시설 갯수 감소
	public void removeCnt(int fcnt)
	{
		int restCnt = this.fcnt-fcnt;
		if(restCnt<0)
		{
			throw new OutOfRcntException("시설 예약이 불가능 합니다."
					+ " (현재 잔여 개수: " + this.fcnt + "개)");
		}
	}

}
