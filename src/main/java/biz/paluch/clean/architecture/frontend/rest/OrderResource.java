package biz.paluch.clean.architecture.frontend.rest;

import biz.paluch.clean.architecture.applicationmodel.Item;
import biz.paluch.clean.architecture.applicationmodel.Order;
import biz.paluch.clean.architecture.facade.ItemService;
import biz.paluch.clean.architecture.facade.OrderService;
import org.jboss.resteasy.annotations.providers.jaxb.Wrapped;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/orders")
@RequestScoped
public class OrderResource
{

    @EJB
    private OrderService orderService;

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public OrdersRepresentation list()
    {
        OrdersRepresentation result = new OrdersRepresentation();
        result.setOrders(orderService.listOrders());
        return result;
    }

}
