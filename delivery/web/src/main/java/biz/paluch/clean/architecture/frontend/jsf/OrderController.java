package biz.paluch.clean.architecture.frontend.jsf;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import biz.paluch.clean.architecture.facade.OrderService;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 * @since 02.08.13 13:41
 */
@RequestScoped
@Named("orderController")
public class OrderController {
    @EJB
    private OrderService orderService;

    @Inject
    private OrderModel orderModel;

    public void addItem() {
        OrderItem oim = new OrderItem();
        oim.setItem(orderModel.getSelectedItem());
        orderModel.getOrderItems().add(oim);
    }

    public void removeItem(OrderItem item) {
        orderModel.getOrderItems().remove(item);
    }

    public void createOrder() {
        List<String> items = new ArrayList<>();

        for (OrderItem orderItem : orderModel.getOrderItems()) {
            items.add(orderItem.getItem());
        }
        orderService.placeOrder(items, orderModel.getUserName());
    }

}
