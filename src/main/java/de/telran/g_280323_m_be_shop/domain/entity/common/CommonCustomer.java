package de.telran.g_280323_m_be_shop.domain.entity.common;

import de.telran.g_280323_m_be_shop.domain.entity.interfaces.Cart;
import de.telran.g_280323_m_be_shop.domain.entity.interfaces.Customer;

public class CommonCustomer implements Customer {
    private int id;
    private String name;
    private Cart cart;
    private int age;
    private String email;

    public CommonCustomer() {
    }

    public CommonCustomer(int id, String name, Cart cart) {
        this.id=id;
        this.name=name;
        this.cart=cart;
    }

    public CommonCustomer(int id, String name, Cart cart, int age, String email) {
        this.id=id;
        this.name=name;
        this.cart=cart;
        this.age=age;
        this.email=email;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id=id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    @Override
    public Cart getCart() {
        return cart;
    }

    @Override
    public String getEMail() {
        return null;
    }

    @Override
    public int getAge() {
        return 0;
    }

    public void setCart(Cart cart) {
        this.cart=cart;
    }
}
