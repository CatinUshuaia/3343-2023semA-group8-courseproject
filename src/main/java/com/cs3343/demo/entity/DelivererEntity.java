package com.cs3343.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "deliverer", schema = "cs3343courseproject", catalog = "")
public class DelivererEntity {
    @Basic
    @Column(name = "delivererName")
    private String delivererName;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "delivererID")
    private long delivererId;

    public String getDelivererName() {
        return delivererName;
    }

    public void setDelivererName(String delivererName) {
        this.delivererName = delivererName;
    }

    public long getDelivererId() {
        return delivererId;
    }

    public void setDelivererId(long delivererId) {
        this.delivererId = delivererId;
    }
}
