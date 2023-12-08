package christmas.model.menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Menu {

    private List<MenuItem> menus = new ArrayList<>();

    public Menu() {
        setUp();
    }

    private void setUp() {
        menus.add(new MenuItem("양송이수프", 6_000, Category.APPETIZER));
        menus.add(new MenuItem("타파스", 5_500, Category.APPETIZER));
        menus.add(new MenuItem("시저샐러드", 8_000, Category.APPETIZER));

        menus.add(new MenuItem("티본스테이크", 55_000, Category.MAIN));
        menus.add(new MenuItem("바비큐립", 54_000, Category.MAIN));
        menus.add(new MenuItem("해산물파스타", 35_000, Category.MAIN));
        menus.add(new MenuItem("크리스마스파스타", 25_000, Category.MAIN));

        menus.add(new MenuItem("초코케이크", 15_000, Category.DESSERT));
        menus.add(new MenuItem("아이스크림", 5_000, Category.DESSERT));

        menus.add(new MenuItem("제로콜라", 3_000, Category.DRINK));
        menus.add(new MenuItem("레드와인", 60_000, Category.DRINK));
        menus.add(new MenuItem("샴페인", 25_000, Category.DRINK));
    }

    public List<MenuItem> getMenus() {
        return Collections.unmodifiableList(menus);
    }

    public Optional<MenuItem> findByName(String menuName) {
        return menus.stream()
                .filter(menu -> menu.getName().equalsIgnoreCase(menuName))
                .findFirst();
    }
}
