package org.apekking.service;


import java.math.BigDecimal;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import org.apekking.dto.OrderEvent;
import org.apekking.entity.OrderEntity;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.jboss.logging.Logger;

@ApplicationScoped 
public class OrderKafkaProcessor {

    private static final Logger LOG = Logger.getLogger(OrderKafkaProcessor.class);

    @Incoming ("orders-in")
    @Outgoing ("orders-out")
    @Transactional 
    public OrderEvent processAndSaveOrder(OrderEvent event) {
        LOG.infof("Processing Order ID: %s for Customer: %s", event.orderId, event.customerName);

        BigDecimal subtotal = event.pricePerItem.multiply(BigDecimal.valueOf(event.quantity));
        BigDecimal tax = subtotal.multiply(new BigDecimal("0.11"));
        BigDecimal finalTotal = subtotal.add(tax);

        event.totalPrice = finalTotal;
        event.status = "PROCESSED";

        OrderEntity entity = new OrderEntity();
        entity.orderId = event.orderId;
        entity.customerName = event.customerName;
        entity.item = event.item;
        entity.quantity = event.quantity;
        entity.pricePerItem = event.pricePerItem;
        entity.totalPrice = event.totalPrice;
        entity.status = event.status;
        entity.persist();

        LOG.infof("Order ID: %s saved to DB with Total: %s", event.orderId, event.totalPrice);

        return event;
    }
}