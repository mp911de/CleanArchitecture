package biz.paluch.clean.architecture.usecases;

import java.util.List;

import javax.inject.Inject;

import biz.paluch.clean.architecture.applicationmodel.Order;
import biz.paluch.clean.architecture.dataaccess.OrderRepository;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 * @since 01.08.13 07:22
 */
public class ListOrders {
    private OrderRepository orderRepository;

    public List<Order> listOrders() {
        return orderRepository.findOrders();
    }

    @Inject
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
}
