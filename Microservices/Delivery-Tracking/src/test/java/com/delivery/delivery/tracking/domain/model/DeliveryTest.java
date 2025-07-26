package com.delivery.delivery.tracking.domain.model;

import com.delivery.delivery.tracking.domain.exception.DomainException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryTest {

    @Test
    public void shouldChangeToPlaced() {
       Delivery draft = Delivery.draf();

       draft.editPreparationDetails(createdValidPrepationDetails());

       draft.place();

       assertEquals(DeliveryStatus.WAITING_FOR_COURIER, draft.getStatus());
       assertNotNull(draft.getPlacedAt());
    }

    private Delivery.PreparationDetails createdValidPrepationDetails() {
        ContactPoint sender = ContactPoint.builder()
                .zipCode("000000-000")
                .street("Rua Nova Olinda")
                .number("500")
                .complement("Arena verde")
                .name("Rinaldo Alves")
                .phone("(81) 99999-9999")
                .build();

        ContactPoint recipient = ContactPoint.builder()
                .zipCode("111111-000")
                .street("Rua Nova Olinda")
                .number("500")
                .complement("Arena verde")
                .name("Rinaldo Alves")
                .phone("(81) 99999-8888")
                .build();

        return Delivery.PreparationDetails.builder()
                .sender(sender)
                .recipient(recipient)
                .distanceFee(new BigDecimal("15.00"))
                .courierPayout(new BigDecimal("5.00"))
                .expectedDeliveryTime(Duration.ofHours(5))
                .build();
    }

    @Test
    public void shouldNotPlaced() {
        Delivery draft = Delivery.draf();

        assertThrows(DomainException.class, () -> {
            draft.place();
        });

        assertEquals(DeliveryStatus.DRAFT, draft.getStatus());
        assertNull(draft.getPlacedAt());
    }
}