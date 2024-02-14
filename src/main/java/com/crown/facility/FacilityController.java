package com.crown.facility;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class FacilityController
{
	private final FacilityService facilityService;

	//시설 등록
	@GetMapping(value = "/facility/new")
	public String facilityNew
	(
		@Valid FacilityDTO facilityDTO,
		BindingResult bindingResult,
		Model model
	)
	{
		if(bindingResult.hasErrors())
		{
			return "facility";
		}

		try
		{
			facilityService.saveFacility(facilityDTO);
		}

		catch(Exception e)
		{
			model.addAttribute("errorMessage", "시설 등록 중 에러가 발생");
			return "facility";
		}

		return "redirect:/";
	}


	//시설 수정
	@GetMapping(value = "/facility/update")
	public String facilityUpdate
	(
		@Valid FacilityDTO facilityDTO,
		BindingResult bindingResult,
		Model model
	)
	{
		if(bindingResult.hasErrors())
		{
			return "facility";
		}

		try
		{
			facilityService.updateFacility(facilityDTO);
		}

		catch(Exception e)
		{
			model.addAttribute("errorMessage", "시설 등록 중 에러가 발생");
			return "facility";
		}

		return "redirect:/";
	}


	//시설 삭제
	@GetMapping(value = "/facility/delete")
	public String facilityDelete
	(
		@Valid FacilityDTO facilityDTO,
		BindingResult bindingResult,
		Model model
	)
	{
		if(bindingResult.hasErrors())
		{
			return "facility";
		}

		try
		{
			facilityService.updateFacility(facilityDTO);
		}

		catch(Exception e)
		{
			model.addAttribute("errorMessage", "시설 삭제 중 에러가 발생");
			return "facility";
		}

		return "redirect:/";
	}
}
