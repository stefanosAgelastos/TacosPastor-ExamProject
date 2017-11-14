package com.example.tacs3rdattempt;

public class DailyReportLine {
    private Product product;
    private int lunchSales;
    private int dinnerSales;
    int total;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getLunchSales() {
        return lunchSales;
    }

    public void setLunchSales(int lunchSales) {
        this.lunchSales = lunchSales;
    }

    public int getDinnerSales() {
        return dinnerSales;
    }

    public void setDinnerSales(int dinnerSales) {
        this.dinnerSales = dinnerSales;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}

