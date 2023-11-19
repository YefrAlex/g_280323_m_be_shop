package de.telran.g_280323_m_be_shop.domain.entity.jpa;


import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="task")
public class Task {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="description")
    private String description;

    @Column(name="executed_at")
    private Timestamp executed_at;

    public Task() {
        executed_at = new Timestamp(System.currentTimeMillis());
    }

    public Task( String description) {
        this.description=description;
        executed_at = new Timestamp(System.currentTimeMillis());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id=id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description=description;
    }

    public Timestamp getExecuted_at() {
        return executed_at;
    }

    public void setExecuted_at(Timestamp executed_at) {
        this.executed_at=executed_at;
    }
    public void findFiveLatestTask(){};

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", executed_at=" + executed_at +
                '}';
    }
}
/*
 1. Реализовать вывод в консоль каждые 30 секунд списка последних пяти выполненных задач.
   Время выполнения предыдущей задачи не должно влиять на старт следующей.
   Создавать новую задачу и логировать ничего не нужно.


Реализовать вывод в консоль последнего добавленного в БД товара.
Вывод должен производиться в 15 и 45 секунд каждой минуты.
Задача должна быть сохранена в БД.
Вывод в консоль должен быть осуществлён через логирование поля description созданной задачи.
Пример значения поля description - "Последний добавленный в БД продукт - Банан".
 */