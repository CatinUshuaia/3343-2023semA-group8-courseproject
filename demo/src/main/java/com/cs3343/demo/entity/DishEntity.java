package com.cs3343.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "dish", schema = "cs3343courseproject")
public class DishEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "DISH_id", nullable = false)
    private long dishId;
    @Basic
    @Column(name = "DISH_name", nullable = false, length = 255)
    private String dishName;
    @Basic
    @Column(name = "DISH_product_time", nullable = false)
    private int dishProductTime;

    public long getDishId() {
        return dishId;
    }

    public void setDishId(long dishId) {
        this.dishId = dishId;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public int getDishProductTime() {
        return dishProductTime;
    }

    public void setDishProductTime(int dishProductTime) {
        this.dishProductTime = dishProductTime;
    }
}
