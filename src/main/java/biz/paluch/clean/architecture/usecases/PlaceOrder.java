package biz.paluch.clean.architecture.usecases;

import biz.paluch.clean.architecture.applicationmodel.NotFoundException;
import biz.paluch.clean.architecture.applicationmodel.Order;
import biz.paluch.clean.architecture.applicationmodel.OrderItem;
import biz.paluch.clean.architecture.timing.DateProvider;
import biz.paluch.clean.architecture.usecases.boundaries.ItemRepository;
import biz.paluch.clean.architecture.usecases.boundaries.OrderRepository;
import biz.paluch.clean.architecture.usecases.boundaries.UserRepository;

import java.util.List;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 * @since 01.08.13 07:15
 */
public class PlaceOrder
{
    private OrderRepository orderRepository;
    private ItemRepository itemRepository;
    private UserRepository userRepository;


    /**
     * Place order and return the OrderId.
     *
     * @param items
     * @param userName
     * @return the OrderId
     * @throws NotFoundException
     */
    public String placeOrder(List<String> items, String userName) throws NotFoundException
    {
        PlaceOrderValidator.newInstance(itemRepository, userRepository).validate(items, userName);

        String orderId = createOrderId(userName);

        Order order = constructOrder(orderId, items, userName);

        storeOrder(order);

        return order.getOrderId();
    }

    private String createOrderId(String userName)
    {
        int nextOrderId = orderRepository.getNextOrderId();
        return userName + "-" + nextOrderId;
    }


    private Order constructOrder(String orderId, List<String> items, String userName)
    {
        Order order = new Order();
        order.setOrderDate(DateProvider.get());
        order.setOrderId(orderId);
        order.setCreatedBy(userRepository.find(userName));

        for (String item : items)
        {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderItem(item);
            order.getItems().add(orderItem);
        }
        
        return order;
    }

    private void storeOrder(Order order)
    {
        orderRepository.persist(order);
    }


    public void setOrderRepository(OrderRepository orderRepository)
    {
        this.orderRepository = orderRepository;
    }

    public void setItemRepository(ItemRepository itemRepository)
    {
        this.itemRepository = itemRepository;
    }

    public void setUserRepository(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }
}
