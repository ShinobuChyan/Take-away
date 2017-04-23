package com.takeaway.model.menu;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    private Long id;

    private Integer type;

    private Long count;

    public Menu() {}

    public Menu(Long id, Integer type, Long count) {
        this.id = id;
        this.type = type;
        this.count = count;
    }

    public static List<Menu> getMenu(List<Object[]> objs) {

        List<Menu> menu = new ArrayList<>();

        for (Object[] a : objs) {
            menu.add(new Menu((Long) a[0], (Integer) a[1], (Long) a[2]));
        }

        return menu;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
