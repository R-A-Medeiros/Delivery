package com.delivery.delivery.tracking.domain.repository;

import com.delivery.delivery.tracking.domain.model.ContactPoint;
import com.delivery.delivery.tracking.domain.model.Delivery;
import lombok.ToString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DeliveryRepositoryTest {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Test
    public void shouldPersist() {
        Delivery delivery = Delivery.draft();

        delivery.editPreparationDetails(createdValidPrepationDetails());

        delivery.addItem("Computador", 2);
        delivery.addItem("Notebook", 2);

        deliveryRepository.saveAndFlush(delivery);

        Delivery persistedDelivery = deliveryRepository.findById(delivery.getId()).orElseThrow();

        assertEquals(2, persistedDelivery.getItems().size());
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
}