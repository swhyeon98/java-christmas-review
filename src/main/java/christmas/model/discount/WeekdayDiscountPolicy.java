package christmas.model.discount;

import christmas.model.Category;
import christmas.model.Menu;
import christmas.model.MenuItem;
import christmas.model.Order;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekdayDiscountPolicy implements DiscountPolicy {

    private final Menu menu;

    public WeekdayDiscountPolicy(Menu menu) {
        this.menu = menu;
    }

    @Override
    public int discount(Order order, LocalDate date) {
        if (isWeekDay(date)) {
            return calculateDiscount(order);
        }
        return 0;
    }

    @Override
    public String getDiscountType() {
        return "평일 할인";
    }

    private boolean isWeekDay(LocalDate date) {
        return date.getDayOfWeek() != DayOfWeek.SATURDAY && date.getDayOfWeek() != DayOfWeek.FRIDAY;
    }

    private int calculateDiscount(Order order) {
        return order.getOrderItems().stream()
                .filter(orderItem -> {
                    MenuItem menuItem = menu.findByName(orderItem.getMenuName()).orElse(null);
                    return menuItem != null && menuItem.getCategory() == Category.DESSERT;
                })
                .mapToInt(orderItem -> 2023 * orderItem.getQuantity())
                .sum();
    }
}
