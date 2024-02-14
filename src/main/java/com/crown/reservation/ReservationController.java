package com.crown.reservation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ReservationController
{
	private final ReservationService reservationService;

	//생성
	@PostMapping(value = "/reservation/create")
	public String reservationCreate
	(
			@Valid ReservationDTO reservationDTO,
			BindingResult bindingResult,
            Model model
	)
	{
	       if(bindingResult.hasErrors()){
	            return "/reservation";
	        }

	        // itemService 에서 File객체를 사용해서 파일을 저장시 예외 처리가 필수인데 throws로 미루었기 때문에
	        // controller 에서 throws로 미룬 예외 처리를 한다.
	        try
	        {
	        	reservationService.createReservation(reservationDTO);
	        }

	        catch (Exception e)
	        {
	            model.addAttribute("errorMessage", "예약 생성 중 에러가 발생하였습니다.");
	            return "/reservation";
	        }

	        return "redirect:/";
	}


	//수정
	@PostMapping(value = "/reservation/update")
	public String reservationUpdate
	(
			@Valid ReservationDTO reservationDTO,
			BindingResult bindingResult,
            Model model
	)
	{
	       if(bindingResult.hasErrors()){
	            return "/reservation";
	        }

	        // itemService 에서 File객체를 사용해서 파일을 저장시 예외 처리가 필수인데 throws로 미루었기 때문에
	        // controller 에서 throws로 미룬 예외 처리를 한다.
	        try
	        {
	        	reservationService.updateReservation(reservationDTO);
	        }

	        catch (Exception e)
	        {
	            model.addAttribute("errorMessage", "예약 수정 중 에러가 발생하였습니다.");
	            return "/reservation";
	        }

	        return "redirect:/";
	}

	//삭제
	@PostMapping(value = "/reservation/delete")
	public String reservationDelete
	(
			@Valid ReservationDTO reservationDTO,
			BindingResult bindingResult,
            Model model
	)
	{
	       if(bindingResult.hasErrors()){
	            return "/reservation";
	        }

	        // itemService 에서 File객체를 사용해서 파일을 저장시 예외 처리가 필수인데 throws로 미루었기 때문에
	        // controller 에서 throws로 미룬 예외 처리를 한다.
	        try
	        {
	        	reservationService.deleteReservation(reservationDTO);
	        }

	        catch (Exception e)
	        {
	            model.addAttribute("errorMessage", "예약 삭제 중 에러가 발생하였습니다.");
	            return "/reservation";
	        }

	        return "redirect:/";
	}
}
