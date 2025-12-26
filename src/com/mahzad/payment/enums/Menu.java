package com.mahzad.payment.enums;

public enum Menu {
    AccountBalance(1),
    Buy(2),
    Bill(3),
    Charge(4),
    Report(5);

    private int menuCode;
    Menu(int menuCode) {
        this.menuCode = menuCode;
    }

    public int getMenuCode() {
        return menuCode;
    }

    public String getMenuName() {
        return name();
    }

    public static Menu fromCode(int menuCode) {
        for (Menu menu : Menu.values()) {
            if (menu.getMenuCode() == menuCode) {
                return menu;
            }
        }
        throw new IllegalArgumentException("Menu code " + menuCode + " not found");
    }
}

