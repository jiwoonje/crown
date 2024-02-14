package com.crown.room;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RoomController
{
	private final RoomService roomService;

	//방 등록
	@GetMapping(value = "/room/new")
	public String roomNew
	(
		@Valid RoomDTO roomDTO,
		BindingResult bindingResult,
		Model model
	)
	{
		if(bindingResult.hasErrors())
		{
			return "room";
		}

		try
		{
			roomService.saveRoom(roomDTO);
		}

		catch(Exception e)
		{
			model.addAttribute("errorMessage", "방 등록 중 에러가 발생");
			return "room";
		}

		return "redirect:/";
	}


	//방 수정
	@GetMapping(value = "/room/update")
	public String roomUpdate
	(
		@Valid RoomDTO roomDTO,
		BindingResult bindingResult,
		Model model
	)
	{
		if(bindingResult.hasErrors())
		{
			return "room";
		}

		try
		{
			roomService.updateRoom(roomDTO);
		}

		catch(Exception e)
		{
			model.addAttribute("errorMessage", "방 등록 중 에러가 발생");
			return "room";
		}

		return "redirect:/";
	}


	//방삭제
	@GetMapping(value = "/room/delete")
	public String roomDelete
	(
		@Valid RoomDTO roomDTO,
		BindingResult bindingResult,
		Model model
	)
	{
		if(bindingResult.hasErrors())
		{
			return "room";
		}

		try
		{
			roomService.updateRoom(roomDTO);
		}

		catch(Exception e)
		{
			model.addAttribute("errorMessage", "방 등록 중 에러가 발생");
			return "room";
		}

		return "redirect:/";
	}

}
