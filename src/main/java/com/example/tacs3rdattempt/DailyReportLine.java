package com.example.tacs3rdattempt;

public class DailyReportLine {
    private Product product;
    private int lunchSales;
    private int dinnerSales;
    private int totalSales;
    double total;

    private void update(){
        totalSales=lunchSales+dinnerSales;
        total = totalSales*product.getPrice();
    }

    public int getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(int totalSales) {
        this.totalSales = totalSales;
    }

    public void setTotal(double total) {
        this.total = total;
    }

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
        update();
    }

    public int getDinnerSales() {
        return dinnerSales;
    }

    public void setDinnerSales(int dinnerSales) {
        this.dinnerSales = dinnerSales;
        update();
    }

    public double getTotal() {
        update();
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}

