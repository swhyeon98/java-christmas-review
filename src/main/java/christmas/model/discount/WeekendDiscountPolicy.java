package christmas.model.discount;

import christmas.model.menu.Category;
import christmas.model.menu.Menu;
import christmas.model.menu.MenuItem;
import christmas.model.order.Order;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekendDiscountPolicy implements DiscountPolicy {

    private final Menu menu;

    public WeekendDiscountPolicy(Menu menu) {
        this.menu = menu;
    }

    @Override
    public int discount(Order order, LocalDate date) {
        if (isWeekendDay(date)) {
            return calculateDiscount(order);
        }
        return 0;
    }

    @Override
    public String getDiscountType() {
        return "주말 할인";
    }

    private boolean isWeekendDay(LocalDate date) {
        return date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.FRIDAY;
    }

    private int calculateDiscount(Order order) {
        return order.getOrderItems().stream()
                .filter(orderItem -> {
                    MenuItem menuItem = menu.findByName(orderItem.getMenuName()).orElse(null);
                    return menuItem != null && menuItem.getCategory() == Category.MAIN;
                })
                .mapToInt(orderItem -> 2023 * orderItem.getQuantity())
                .sum();
    }
}
