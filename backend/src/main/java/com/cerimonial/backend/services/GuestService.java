package com.cerimonial.backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cerimonial.backend.models.Guest;
import com.cerimonial.backend.repositories.GuestRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class GuestService {
    private GuestRepository guestRepository;

    public List<Guest> listByTableId(String tableId) {
        return guestRepository.findByTableId(tableId);
    }

    public List<Guest> listByEventId(String eventId) {
        return guestRepository.findByEventId(eventId);
    }
}
