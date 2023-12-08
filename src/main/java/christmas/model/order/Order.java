package christmas.model.order;

import christmas.model.menu.Menu;
import christmas.model.menu.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private final List<OrderItem> orderItems;
    private final Menu menu;

    public Order(Menu menu) {
        this.orderItems = new ArrayList<>();
        this.menu = menu;
    }

    public void addOrderItem(String menuName, int quantity) {
        validateMenuName(menuName);
        validateDuplicateMenuName(menuName);
        orderItems.add(new OrderItem(menuName, quantity));
    }

    public int calculateTotalPrice() {
        int totalPrice = 0;
        for (OrderItem orderItem : orderItems) {
            int itemPrice = menu.findByName(orderItem.getMenuName())
                    .map(MenuItem::getPrice)
                    .orElse(0);
            totalPrice += itemPrice * orderItem.getQuantity();
        }
        return totalPrice;
    }

    private void validateMenuName(String menuName) {
        if (menu.findByName(menuName).isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private void validateDuplicateMenuName(String menuName) {
        if (isDuplicate(menuName)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private boolean isDuplicate(String menuName) {
        return orderItems.stream()
                .anyMatch(orderItem -> orderItem.getMenuName().equals(menuName));
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }
}
