package com.penpick.reservation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.penpick.pension.service.PensionService;
import com.penpick.reservation.model.Reservation;
import com.penpick.reservation.repository.ReservationRepository;
import com.penpick.users.model.Users;
import com.penpick.users.service.UserService;


@Service
public class ReservationService{

    @Autowired
    private UserService userService;

    @Autowired
    private PensionService pensionService;

    @Autowired
    private ReservationRepository reservationRepository; 
    

    public void makeReservation(String email,String phoneNumber,int people,boolean pick,String payment,int pay,/*Date checkInday,Date checkOutDay,*/String roomType)  {
//    	Pensions pensions = pensionService.getPensionName(name);
    	Users users = userService.getUserByEmail(email);
       

        Reservation reservation = new Reservation();
        reservation.setPenpickUser(users);
//        reservation.setPensions(pensions);
        reservation.setPhoneNumber(phoneNumber);
        reservation.setPeople(people);
        reservation.setPick(pick);
        reservation.setPayment(payment);
        reservation.setPay(pay);
//        reservation.setCheckInDay(checkInday);
//        reservation.setCheckOutDay(checkOutDay);
        reservation.setRoomType(roomType);

        reservationRepository.save(reservation);
    }

    
    public List<Reservation> getAllReservation(){
    	return reservationRepository.findAll();
    }
    
    public Optional<Reservation> getReservation(Long id){
    	return reservationRepository.findById(id);
    }
//    
//    public Optional<Reservation> deleteReservation() {
//    	return reservationRepository.deleteById();
//    }
    
    //예약 정보 전화번호로 불러오기
    public List<Reservation> findReservationsByPhoneNumber(String phoneNumber) {
      return reservationRepository.findReservationsByPhoneNumber(phoneNumber);
    }
    
    //예약 수정을 위한 저장 메서드
    public Reservation updateReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }
    
    
}