package biz.paluch.clean.architecture.frontend.jsf;

import biz.paluch.clean.architecture.applicationmodel.Order;
import biz.paluch.clean.architecture.applicationmodel.User;
import biz.paluch.clean.architecture.facade.OrderService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 * @since 02.08.13 13:41
 */
@RequestScoped
@Named("orderController")
public class OrderController
{
    @EJB
    private OrderService orderService;

    @Inject
    private OrderModel orderModel;


    public void addItem()
    {
        OrderItemModel oim = new OrderItemModel();
        oim.setItem(orderModel.getSelectedItem());
        orderModel.getOrderItems().add(oim);
    }

    public void createOrder()
    {
        List<String> items = new ArrayList<>();

        for (OrderItemModel orderItemModel : orderModel.getOrderItems())
        {
            items.add(orderItemModel.getItem());
        }
        orderService.placeOrder(items, orderModel.getUserName());
    }

}
