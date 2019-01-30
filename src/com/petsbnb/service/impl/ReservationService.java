package com.petsbnb.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.petsbnb.persistance.mapper.ReservationMapper;
import com.petsbnb.service.IReservationService;

@Service("ReservationService")
public class ReservationService implements IReservationService {

	@Resource(name="ReservationMapper")
	private ReservationMapper reservationMapper;
	
}
