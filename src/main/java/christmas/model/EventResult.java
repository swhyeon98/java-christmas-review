package christmas.model;

import christmas.model.discount.DiscountPolicy;
import christmas.model.event.Badge;
import christmas.model.order.Order;

import java.time.LocalDate;
import java.util.List;

public class EventResult {

    private static final int PROMOTION_THRESHOLD = 120000;
    private static final String PROMOTION_ITEM = "샴페인";

    private final List<DiscountPolicy> discountPolicies;

    public EventResult(List<DiscountPolicy> discountPolicies) {
        this.discountPolicies = discountPolicies;
    }

    public String calculateEventPromotion(int totalPrice) {
        if (totalPrice >= PROMOTION_THRESHOLD) {
            return PROMOTION_ITEM;
        }
        return "없음";
    }

    public String calculateBenefits(Order order, LocalDate date) {
        StringBuilder benefits = new StringBuilder();

        for (DiscountPolicy policy : discountPolicies) {
            int discountAmount = policy.discount(order, date);
            if (discountAmount > 0) {
                benefits.append(policy.getDiscountType()).append(": -").append(discountAmount).append("원\n");
            }
        }

        if (benefits.length() == 0) {
            benefits.append("없음");
        }

        return benefits.toString();
    }

    public int calculateTotalDiscount(Order order, LocalDate date) {
        return discountPolicies.stream()
                .mapToInt(policy -> policy.discount(order, date))
                .sum();
    }

    public int getExpectationsAmount(int totalPrice, int totalDiscount) {
        return totalPrice - totalDiscount;
    }

    public Badge getBadge(int totalDiscount) {
        return Badge.getBadgeByBenefitAmount(totalDiscount);
    }
}
