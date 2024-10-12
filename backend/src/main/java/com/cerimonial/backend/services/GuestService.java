package com.cerimonial.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cerimonial.backend.dto.CreateGuestDTO;
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

    public Guest createGuest(CreateGuestDTO createGuestDTO, String eventId) {
        Guest guest = new Guest();
        
        guest.setEventId(eventId);
        guest.setName(createGuestDTO.getName());
        guest.setTableId(createGuestDTO.getTableId());
        
        return guestRepository.save(guest);
    }

    public Optional<Guest> getGuest(String guestId) {
        return guestRepository.findById(guestId);
    }

    public void deleteGuest(Guest guest) {
        guestRepository.deleteById(guest.getId());
    }
}
