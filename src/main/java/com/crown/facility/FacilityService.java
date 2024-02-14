package com.crown.facility;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class FacilityService
{
	private final FacilityRepository facilityRepository;

	//방 등록
	public int saveFacility(FacilityDTO facilityDTO)
	{
		Facility facility = facilityDTO.createFacility();
		facilityRepository.save(facility);

		log.info("FacilityID"+facility.getFid());
		log.info("FacilityName"+facility.getFname());
		log.info("FacilityType"+facility.getFtype());
		log.info("FacilityCnt"+facility.getFcnt());

		return facility.getFid();
	}

	//방 수정
	public int updateFacility(FacilityDTO facilityDTO)
	{
		Facility facility = facilityRepository.findById(facilityDTO.getFid())
				.orElseThrow(EntityNotFoundException::new);

		facility.updateFacility(facilityDTO);

		return facility.getFid();
	}

	//방 삭제
	public void deleteFacility(FacilityDTO facilityDTO)
	{
		Facility facility = facilityRepository.findById(facilityDTO.getFid())
				.orElseThrow(EntityNotFoundException::new);
		facilityRepository.delete(facility);
	}
}
