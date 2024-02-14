package com.crown.room;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class RoomService
{
	private final RoomRepository roomRepository;

	//방 등록
	public int saveRoom(RoomDTO roomDTO)
	{
		Room room = roomDTO.createRoom();
		roomRepository.save(room);

		log.info("RoomID"+room.getId());
		log.info("RoomName"+room.getRname());
		log.info("RoomType"+room.getRtype());
		log.info("RoomCnt"+room.getRcnt());

		return room.getId();
	}

	//방 수정
	public int updateRoom(RoomDTO roomDTO)
	{
		Room room = roomRepository.findById(roomDTO.getId())
				.orElseThrow(EntityNotFoundException::new);

		room.updateRoom(roomDTO);

		return room.getId();
	}

	//방 삭제
	public void deleteRoom(RoomDTO roomDTO)
	{
		Room room = roomRepository.findById(roomDTO.getId())
				.orElseThrow(EntityNotFoundException::new);
		roomRepository.delete(room);
	}
}
