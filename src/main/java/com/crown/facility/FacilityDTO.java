package com.crown.facility;

import org.modelmapper.ModelMapper;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FacilityDTO
{
	private int fid; //시설 구분 번호

	private String fname; //시설 이름

	private String ftype; //시설 종류

	private int fcnt; //시설 갯수

	//DTO의 값을 Entity 클래스와 연결해서 자동 값 주입 by modelmapper
	private static ModelMapper modelMapper = new ModelMapper();

	//Client에서 넘어오는 값을 DTO에 담아서 Facility(Entity class)에 적용 후 DB에 저장
	public Facility createFacility()
	{
		return modelMapper.map(this, Facility.class);
	}

	//DB에서 가져온 Facility를 DTO에 주입해서 client로 전송하기 위한 맵핑
	public static FacilityDTO of(Facility facility)
	{
		return modelMapper.map(facility, FacilityDTO.class);
	}

}
