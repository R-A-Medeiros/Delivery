package com.delivery.delivery.tracking.infrastructure.fake;

import com.delivery.delivery.tracking.domain.model.ContactPoint;
import com.delivery.delivery.tracking.domain.services.DeliveryEstimate;
import com.delivery.delivery.tracking.domain.services.DeliveryTimeEstimationService;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class DeliveryTimeEstimationServiceFakeImpl implements DeliveryTimeEstimationService {
    @Override
    public DeliveryEstimate estimate(ContactPoint sender, ContactPoint receiver) {
        return new DeliveryEstimate(
                Duration.ofHours(3),
                3.1
        );
    }
}
