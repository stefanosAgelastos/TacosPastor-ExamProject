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
    private Date localDateTime;
    @OneToOne
    private Product product;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(Date localDateTime) {
        this.localDateTime = localDateTime;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}