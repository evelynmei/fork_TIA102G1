package com.tia102g1.chart.model;


public class ProductSalesVO {

    private String proName;
    private Long totalQuantity;

    public ProductSalesVO(String proName, Long totalQuantity) {
        this.proName = proName;
        this.totalQuantity = totalQuantity;
    }

    // Getters and setters
    public String getProName() {
        return proName;
    }

    public void setProductName(String productName) {
        this.proName = productName;
    }

    public Long getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Long totalQuantity) {
        this.totalQuantity = totalQuantity;
    }
}
