package com.cs3343.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cook", schema = "cs3343courseproject")
public class CookEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "COOK_id", nullable = false)
    private long cookId;
    @Basic
    @Column(name = "COOK_expertise", nullable = true, length = 255)
    private String cookExpertise;
    @Basic
    @Column(name = "COOK_name", nullable = false, length = 255)
    private String cookName;
    @Basic
    @Column(name = "COOK_rank", nullable = false)
    private int cookRank;

    public CookEntity(){}

    public CookEntity(String cookName, String cookExpertise, int cookRank){
//        this.cookId = cookId;
        this.cookName = cookName;
        this.cookExpertise = cookExpertise;
        this.cookRank = cookRank;
    }

    public long getCookId() {
        return cookId;
    }

    public void setCookId(long cookId) {
        this.cookId = cookId;
    }

    public String getCookExpertise() {
        return cookExpertise;
    }

    public void setCookExpertise(String cookExpertise) {
        this.cookExpertise = cookExpertise;
    }

    public String getCookName() {
        return cookName;
    }

    public void setCookName(String cookName) {
        this.cookName = cookName;
    }

    public int getCookRank() {
        return cookRank;
    }

    public void setCookRank(int cookRank) {
        this.cookRank = cookRank;
    }
}
