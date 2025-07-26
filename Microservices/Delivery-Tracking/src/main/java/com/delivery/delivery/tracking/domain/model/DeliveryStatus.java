package com.delivery.delivery.tracking.domain.model;

import java.util.Arrays;
import java.util.List;

public enum DeliveryStatus {
    DRAFT,
    WAITING_FOR_COURIER(DRAFT),
    IN_TRANSIT(WAITING_FOR_COURIER),
    DELIVERY(IN_TRANSIT);

    private final List<DeliveryStatus> previoesStatuses;

    DeliveryStatus(DeliveryStatus... previousStatuses) {
        this.previoesStatuses = Arrays.asList(previousStatuses);
    }

    public boolean canNotChangeTo(DeliveryStatus newStatus) {
        DeliveryStatus current = this;
        return !newStatus.previoesStatuses.contains(current);
    }

    public boolean canChangeTo(DeliveryStatus newStatus) {
        return !canNotChangeTo(newStatus);
    }
}
