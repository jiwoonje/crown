package com.crown.room;

import org.modelmapper.ModelMapper;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomDTO
{
	private int id; //방 구분 번호

	private String rname; //방 이름

	private String rtype; // 방 종류

	private int rcnt; // 방 갯수

	//DTO의 값을 Entity 클래스와 연결해서 자동 값 주입 by modelmapper
	private static ModelMapper modelMapper = new ModelMapper();

	//Client에서 넘어오는 값을 DTO에 담아서 Room(Entity class)에 적용 후 DB에 저장
	public Room createRoom()
	{
		return modelMapper.map(this, Room.class);
	}

	//DB에서 가져온 Room을 DTO에 주입해서 client로 전송하기 위한 맵핑
	public static RoomDTO of(Room room)
	{
		return modelMapper.map(room, RoomDTO.class);
	}

}
