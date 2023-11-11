package de.telran.g_280323_m_be_shop.domain.entity.jpa;


import de.telran.g_280323_m_be_shop.domain.entity.interfaces.Customer;
import jakarta.persistence.*;

@Entity
@Table(name="customer")
public class JpaCustomer implements Customer {

    @Id
    @GeneratedValue (strategy= GenerationType.IDENTITY)
    @Column(name="customer_id")
    private int id;

    @Column(name="name")
    private String name;
    @Column(name="email")
    private String email;

    @Column(name="age")
    private int age;

    @OneToOne(mappedBy="customer")
    private JpaCart cart;


    public JpaCustomer() {
    }

    public JpaCustomer(int id, String name) {
        this.id=id;
        this.name=name;

    }

    public JpaCustomer(int id, String name, String email, int age) {
        this.id=id;
        this.name=name;
        this.email=email;
        this.age=age;
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
    public String getEMail() {
        return email;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public JpaCart getCart() {
        return cart;
    }



    public void setCart(JpaCart cart) {
        this.cart=cart;
    }
}
