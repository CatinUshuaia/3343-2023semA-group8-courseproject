package com.cs3343.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "maporderdish", schema = "cs3343courseproject")
public class MaporderdishEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "MOD_id", nullable = false)
    private long modId;
    @Basic
    @Column(name = "MOD_dish_id", nullable = true)
    private Long modDishId;
    @Basic
    @Column(name = "MOD_order_id", nullable = true)
    private Long modOrderId;
    @ManyToOne
    @JoinColumn(name = "MOD_dish_id", referencedColumnName = "DISH_id", insertable=false, updatable=false)
    private DishEntity dishByModDishId;

    public long getModId() {
        return modId;
    }

    public void setModId(long modId) {
        this.modId = modId;
    }

    public Long getModDishId() {
        return modDishId;
    }

    public void setModDishId(Long modDishId) {
        this.modDishId = modDishId;
    }

    public Long getModOrderId() {
        return modOrderId;
    }

    public void setModOrderId(Long modOrderId) {
        this.modOrderId = modOrderId;
    }

    public DishEntity getDishByModDishId() {
        return dishByModDishId;
    }

    public void setDishByModDishId(DishEntity dishByModDishId) {
        this.dishByModDishId = dishByModDishId;
    }
}
