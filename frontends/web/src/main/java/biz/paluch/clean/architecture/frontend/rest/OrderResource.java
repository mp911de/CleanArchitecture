package biz.paluch.clean.architecture.frontend.rest;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import biz.paluch.clean.architecture.facade.OrderService;

@Path("/orders")
@RequestScoped
public class OrderResource {

    @EJB
    private OrderService orderService;

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public OrdersRepresentation list() {
        OrdersRepresentation result = new OrdersRepresentation();
        result.setOrders(orderService.listOrders());
        return result;
    }

}
