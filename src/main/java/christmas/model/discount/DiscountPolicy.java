package christmas.model.discount;

import christmas.model.order.Order;

import java.time.LocalDate;

public interface DiscountPolicy {

    int discount(Order order, LocalDate date);
    String getDiscountType();
}
