package com.cs3343.demo.entity;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "order", schema = "cs3343courseproject")
public class OrderEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ORDER_id", nullable = false)
    private long orderId;
    @OneToMany(mappedBy = "modOrderId")
    private Collection<MaporderdishEntity> maporderdishesByOrderId;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public Collection<MaporderdishEntity> getMaporderdishesByOrderId() {
        return maporderdishesByOrderId;
    }

    public void setMaporderdishesByOrderId(Collection<MaporderdishEntity> maporderdishesByOrderId) {
        this.maporderdishesByOrderId = maporderdishesByOrderId;
    }
}
