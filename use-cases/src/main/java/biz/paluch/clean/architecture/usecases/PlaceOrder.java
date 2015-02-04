package biz.paluch.clean.architecture.usecases;

import java.util.List;

import javax.inject.Inject;

import biz.paluch.clean.architecture.applicationmodel.NotFoundException;
import biz.paluch.clean.architecture.applicationmodel.Order;
import biz.paluch.clean.architecture.applicationmodel.OrderItem;
import biz.paluch.clean.architecture.commons.DateProvider;
import biz.paluch.clean.architecture.contracts.ItemRepository;
import biz.paluch.clean.architecture.contracts.OrderRepository;
import biz.paluch.clean.architecture.contracts.UserRepository;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 * @since 01.08.13 07:15
 */
public class PlaceOrder {
    private OrderRepository orderRepository;
    private ItemRepository itemRepository;
    private UserRepository userRepository;

    /**
     * Place order and return the OrderId.
     *
     * @param items Items to create an order of
     * @param userName Requesting user
     * @return the OrderId
     * @throws NotFoundException
     */
    public String placeOrder(List<String> items, String userName) throws NotFoundException {
        ValidateOrder.newInstance(itemRepository, userRepository).validate(items, userName);

        String orderId = createOrderId(userName);

        Order order = constructOrder(orderId, items, userName);

        storeOrder(order);

        return order.getOrderId();
    }

    private String createOrderId(String userName) {
        int nextOrderId = orderRepository.getNextOrderId();
        return userName + "-" + nextOrderId;
    }

    private Order constructOrder(String orderId, List<String> items, String userName) {
        Order order = new Order();
        order.setOrderDate(DateProvider.get());
        order.setOrderId(orderId);
        order.setCreatedBy(userRepository.find(userName));

        for (String item : items) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderItem(item);
            order.getItems().add(orderItem);
        }

        return order;
    }

    private void storeOrder(Order order) {
        orderRepository.persist(order);
    }

    @Inject
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Inject
    public void setItemRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Inject
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
