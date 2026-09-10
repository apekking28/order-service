package org.apekking.service;



import java.math.BigDecimal;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import org.apekking.entity.OrderEntity;
import org.jboss.logging.Logger;

@ApplicationScoped 
public class CheckoutService {

    private static final Logger LOG = Logger.getLogger(CheckoutService.class);

    public boolean reserveStock(String orderId, String item, Integer quantity) {
        LOG.infof("[Inventory Service] Reserving %d units of %s for Order %s", quantity, item, orderId);
        return true;
    }

    public boolean processPayment(String orderId, Double amount) {
        LOG.infof("[Payment Service] Processing payment of IDR %.2f for Order %s", amount, orderId);
        return true;
    }

    @Transactional
    public String createDeliveryOrder(String orderId, String customerName, String item, Integer quantity, Double amount) {
        String trackingNumber = "LOG-REG-" + System.currentTimeMillis();
        LOG.infof("[Logistics Service] Created delivery %s for %s (Order %s)", trackingNumber, customerName, orderId);

        
        OrderEntity entity = new OrderEntity();
        entity.orderId = orderId;
        entity.customerName = customerName;
        entity.item = item;
        entity.quantity = quantity;
        entity.pricePerItem = amount != null ? BigDecimal.valueOf(amount) : BigDecimal.ZERO;
        entity.totalPrice = amount != null ? BigDecimal.valueOf(amount) : BigDecimal.ZERO;
        entity.status = "COMPLETED";
        entity.persist();

        LOG.infof("[Database] Successfully save order with order id : %s", orderId);

        return trackingNumber;
    }

    @Transactional
    public List<OrderEntity> getAllOrders() {
        LOG.info("Succesfully get all order");
        return OrderEntity.listAll();
    }
}