package christmas.controller;

import christmas.model.EventResult;
import christmas.model.discount.DiscountPolicy;
import christmas.model.event.Badge;
import christmas.model.menu.Menu;
import christmas.model.order.Order;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ChristmasController {

    private final List<DiscountPolicy> discountPolicies;
    private final InputView inputView;
    private final OutputView outputView;
    private final Menu menu;
    private final Order order;
    private final EventResult eventResult;

    public ChristmasController(List<DiscountPolicy> discountPolicies, Menu menu) {
        this.discountPolicies = discountPolicies;
        this.menu = menu;
        this.order = new Order(menu);
        this.eventResult = new EventResult(discountPolicies);
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }


    public void run() {
        outputView.printWelcomeMessage();
        LocalDate orderDate = readDateTime();

        outputView.printMenu(menu.getMenus());

        processOrderInput(inputView.readMenu());

        int totalPrice = takeOrder();

        outputView.printPromotionEvent(eventResult.calculateEventPromotion(totalPrice));
        outputView.printBenefits(eventResult.calculateBenefits(order, orderDate));
        int totalDiscount = eventResult.calculateTotalDiscount(order, orderDate);
        outputView.printTotalDiscount(totalDiscount);
        outputView.printExpectationsAmount(eventResult.getExpectationsAmount(totalPrice, totalDiscount));
        Badge badge = eventResult.getBadge(totalDiscount);
        outputView.printEventBadge(badge.toString());
    }

    private int takeOrder() {
        outputView.printOrderItems(order.getOrderItems());
        int totalPrice = order.calculateTotalPrice();
        outputView.printTotalPrice(totalPrice);
        return totalPrice;
    }

    private LocalDate readDateTime() {
        while (true) {
            try {
                int date = inputView.readDate();
                return LocalDate.of(2023, Month.DECEMBER, date);
            } catch (DateTimeException | NumberFormatException e) {
                outputView.printErrorMessage("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
            }
        }
    }

    private void processOrderInput(String input) {
        List<String> items = splitByComma(input);
        items.forEach(this::addMenuItemToOrder);
    }

    private List<String> splitByComma(String input) {
        return Arrays.stream(input.split(","))
                .collect(Collectors.toList());
    }

    private void addMenuItemToOrder(String item) {
        List<String> parts = splitByDash(item);
        String menuName = parts.get(0).trim();
        int quantity = Integer.parseInt(parts.get(1).trim());
        order.addOrderItem(menuName, quantity);
    }

    private List<String> splitByDash(String input) {
        return Arrays.asList(input.split("-"));
    }
}
