package christmas.model.discount;

import christmas.model.Category;
import christmas.model.Menu;
import christmas.model.MenuItem;
import christmas.model.Order;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class WeekdayDiscountPolicy implements DiscountPolicy {

    private final Menu menu;

    public WeekdayDiscountPolicy(Menu menu) {
        this.menu = menu;
    }

    @Override
    public int discount(Order order, LocalDateTime date) {
        if (isWeekDay(date)) {
            return calculateDiscount(order, Category.DESSERT);
        }

        return 0;
    }

    @Override
    public String getDiscountType() {
        return "평일 할인";
    }

    private boolean isWeekDay(LocalDateTime date) {
        return date.getDayOfWeek() != DayOfWeek.SATURDAY && date.getDayOfWeek() != DayOfWeek.FRIDAY;
    }

    private int calculateDiscount(Order order, Category category) {
        return order.getOrderItems().stream()
                .filter(orderItem -> {
                    MenuItem menuItem = menu.findByName(orderItem.getMenuName()).orElse(null);
                    return menuItem != null && menuItem.getCategory() == category;
                })
                .mapToInt(orderItem -> 2023 * orderItem.getQuantity())
                .sum();
    }
}
