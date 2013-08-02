package biz.paluch.clean.architecture.frontend.rest;

import biz.paluch.clean.architecture.applicationmodel.Order;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 * @since 02.08.13 13:56
 */
@XmlRootElement
public class OrdersRepresentation
{
    private List<Order> orders = new ArrayList<>();

    public List<Order> getOrders()
    {
        return orders;
    }

    public void setOrders(List<Order> orders)
    {
        this.orders = orders;
    }
}
