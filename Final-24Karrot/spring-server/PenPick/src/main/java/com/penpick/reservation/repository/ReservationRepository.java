package com.penpick.reservation.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.penpick.reservation.model.Reservation;
import com.penpick.users.model.Users;


public interface ReservationRepository extends JpaRepository<Reservation, Long> {
//    List<Reservation> findByPenpickUser(Users penpickUser);
    
    
//    @Query("SELECT r FROM Reservation r JOIN Users p ON r.phoneNumber = p.phoneNumber WHERE r.phoneNumber = :phoneNumber")
//    List<Reservation> findByPhoneNumber(@Param("phoneNumber") String phoneNumber);
//    @Query("SELECT r FROM Reservation r JOIN Users p ON r.phoneNumber = p.phoneNumber)
//    List<Reservation> findByEmail(@Param("email") String email);
//    	@Query("SELECT r FROM Reservation r JOIN Users p ON r.phoneNumber = p.phoneNumber")
//    	List<Reservation> findByPhoneNumber(@Param("phoneNumber") String phoneNumber);
    	
    	//유저와 일치하는 예약 불러오기
    	@Query("SELECT r FROM Reservation r WHERE r.phoneNumber =:phoneNumber")
    	List<Reservation> findReservationsByPhoneNumber(@Param("phoneNumber") String phoneNumber);
    	
//    	Optional<Reservation> deleteById();
}