package org.apekking.resource;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.apekking.entity.OrderEntity;
import org.apekking.service.CheckoutService;

@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
public class OrderResource {

    @Inject
    CheckoutService checkoutService;

    @GET
    public List<OrderEntity> getAllOrders() {
        return checkoutService.getAllOrders();
    }
}