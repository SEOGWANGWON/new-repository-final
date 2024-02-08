package com.penpick.reservation.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.penpick.pension.service.PensionService;
import com.penpick.reservation.model.Reservation;
import com.penpick.reservation.service.ReservationService;


@RestController
@RequestMapping("/reservation")
@CrossOrigin(origins="http://localhost:3000", 
allowCredentials="true",
allowedHeaders="*")
public class ReservationController {
	
	@Autowired
	private ReservationService reservationService;
	
	 @GetMapping("/list")
		public ResponseEntity<List<Reservation>> getAllReservation() {
			List<Reservation> reservationList = null;
			try {
				reservationList = reservationService.getAllReservation();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return ResponseEntity.ok(reservationList);
	 }
	

	 
	 @GetMapping("/check")
	 public List<Reservation> getReservation(@RequestParam String phoneNumber){
		 List<Reservation> reservationList = reservationService.findReservationsByPhoneNumber(phoneNumber);
		 return reservationList;
	 }
	 

	 
     //아이디값으로 조회
	 @GetMapping("/checkId")
	 public Optional<Reservation> getReservation(Long id){
		 return reservationService.getReservation(id);
	 }
	 
	 //예약 삭제
//	 @PostMapping("/delete")
//	 public Optional<Reservation> deleteReservation() {
//		 return reservationService.deleteReservation();
//	 }
	 
	 //예약 수정
	 @PutMapping("/update")
	 public ResponseEntity<Reservation> updateReservation(@PathVariable Long id,@RequestBody Reservation updatedReservation){
		 Optional<Reservation> existingReservation = reservationService.getReservation(id);
		 
		 if(existingReservation != null) {
			 Reservation reservation = existingReservation.get();
			 
			 reservation.setPeople(updatedReservation.getPeople());
			 reservation.setRoomType(updatedReservation.getRoomType());
			 reservation.setPickTime(updatedReservation.getPickTime());
			
			 Reservation updatedRes = reservationService.updateReservation(reservation);
			 return ResponseEntity.ok(updatedRes);
		 }else {
			 return ResponseEntity.notFound().build();
		 }
		
	 }
	 
	 
	 
	 
	 
	 @PostMapping("/makeReservation")
	 public ResponseEntity<String> makeReservation(@RequestBody Map<String, Object> request) {
		 
		 String email = (String) request.get("email");
	     String phoneNumber = (String) request.get("phoneNumber");
	     String pickBoolean = (String) request.get("pick");
	     String peopleString = (String) request.get("people");
	     String payment = (String) request.get("payment");
	     String payString = (String) request.get("pay");
//	     String checkIndayDate = (String) request.get("checkInDay");
//	     String checkOutdayDate = (String) request.get("checkOutDay");
	     String roomType = (String) request.get("roomType");
	   
	     try {
	         int people = Integer.parseInt(peopleString);
	         boolean pick = Boolean.parseBoolean(pickBoolean);
	         int pay = Integer.parseInt(payString);
	        
	         
	         reservationService.makeReservation(email,phoneNumber, people,pick,payment,pay,/*checkIndayDate,checkOutdayDate,*/roomType);
	         return ResponseEntity.ok("구매에 성공하였습니다.");
	     } catch (NumberFormatException e) {
	         return ResponseEntity.badRequest().body("구매실패했습니다.");
	     }
	 }

}
