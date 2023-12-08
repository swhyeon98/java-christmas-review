package christmas;

import christmas.controller.ChristmasController;
import christmas.model.discount.DiscountPolicy;
import christmas.model.discount.SpecialDiscountPolicy;
import christmas.model.discount.WeekdayDiscountPolicy;
import christmas.model.discount.WeekendDiscountPolicy;
import christmas.model.discount.XmasDdayDiscountPolicy;
import christmas.model.menu.Menu;

import java.util.Arrays;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        Menu menu = new Menu();
        List<DiscountPolicy> discountPolicies = Arrays.asList(new XmasDdayDiscountPolicy(),
                new WeekdayDiscountPolicy(menu),
                new WeekendDiscountPolicy(menu),
                new SpecialDiscountPolicy());

        ChristmasController controller = new ChristmasController(discountPolicies, menu);
        controller.run();
    }
}
