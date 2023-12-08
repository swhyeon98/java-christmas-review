package christmas.model.discount;

import christmas.model.order.Order;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class SpecialDiscountPolicy implements DiscountPolicy {

    private final Set<Integer> specialDays = new HashSet<>();

    public SpecialDiscountPolicy() {
        specialDays.add(3);
        specialDays.add(10);
        specialDays.add(17);
        specialDays.add(24);
        specialDays.add(25);
        specialDays.add(31);
    }

    @Override
    public int discount(Order order, LocalDate date) {
        if (isSpecialDay(date)) {
            return 1000;
        }
        return 0;
    }

    @Override
    public String getDiscountType() {
        return "특별 할인";
    }

    private boolean isSpecialDay(LocalDate date) {
        return specialDays.contains(date.getDayOfMonth());
    }
}
