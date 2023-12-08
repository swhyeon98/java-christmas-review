package christmas.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
}
