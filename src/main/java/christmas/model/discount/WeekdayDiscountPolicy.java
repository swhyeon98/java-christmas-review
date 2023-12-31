package christmas.model.discount;

import christmas.model.menu.Category;
import christmas.model.menu.Menu;
import christmas.model.menu.MenuItem;
import christmas.model.order.Order;
import christmas.model.order.OrderItem;

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
        int totalDiscount = 0;
        for (OrderItem orderItem : order.getOrderItems()) {
            if (isDessert(orderItem)) {
                totalDiscount += 2023 * orderItem.getQuantity();
            }
        }
        return totalDiscount;
    }

    private boolean isDessert(OrderItem orderItem) {
        MenuItem menuItem = menu.findByName(orderItem.getMenuName()).orElse(null);
        if (menuItem != null && menuItem.getCategory() == Category.DESSERT) {
            return true;
        }
        return false;
    }
}
