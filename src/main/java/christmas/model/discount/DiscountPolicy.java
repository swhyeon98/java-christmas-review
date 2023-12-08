package christmas.model.discount;

import christmas.model.Order;

import java.time.LocalDateTime;

public interface DiscountPolicy {

    int discount(Order order, LocalDateTime date);
    String getDiscountType();
}
