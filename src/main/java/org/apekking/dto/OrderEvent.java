package org.apekking.dto;

import java.math.BigDecimal;

public class OrderEvent {
    public String orderId;
    public String customerName;
    public String item;
    public Integer quantity;
    public BigDecimal pricePerItem;
    public BigDecimal totalPrice;
    public String status;
}