package dev.ohhoonim.modulith_demo.orders;

import org.springframework.modulith.events.Externalized;

@Externalized(target = OrdersIntegrationConfiguration.ORDER_DESTINATION)
public record OrderPlacedEvent(
    int order,
    int quantity,
    int product
) {

}
