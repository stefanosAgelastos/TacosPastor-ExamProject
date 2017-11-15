package com.example.tacs3rdattempt;

import org.springframework.boot.autoconfigure.web.ResourceProperties;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class SoldProduct {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date soldDate;
    @ManyToOne
    private Product product;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getSoldDate() {
        return soldDate;
    }

    public void setSoldDate(Date soldDate) {
        this.soldDate = soldDate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "SoldProduct{" +
                "id=" + id +
                ", soldDate=" + soldDate +
                ", product=" + product +
                '}';
    }
}