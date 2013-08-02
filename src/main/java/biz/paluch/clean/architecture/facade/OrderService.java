package biz.paluch.clean.architecture.facade;

import biz.paluch.clean.architecture.applicationmodel.NotFoundException;
import biz.paluch.clean.architecture.applicationmodel.Order;
import biz.paluch.clean.architecture.usecases.CreateOrUpdateUser;
import biz.paluch.clean.architecture.usecases.ListOrders;
import biz.paluch.clean.architecture.usecases.PlaceOrder;
import biz.paluch.clean.architecture.usecases.boundaries.ItemRepository;
import biz.paluch.clean.architecture.usecases.boundaries.OrderRepository;
import biz.paluch.clean.architecture.usecases.boundaries.UserRepository;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 * @since 02.08.13 12:45
 */
@Stateless
public class OrderService
{

    @Inject
    private OrderRepository orderRepository;

    @Inject
    private UserRepository userRepository;

    @Inject
    private ItemRepository itemRepository;

    @Inject
    private CreateOrUpdateUser createOrUpdateUser;

    @Inject
    private ListOrders listOrders;

    @Inject
    private PlaceOrder placeOrder;

    @PostConstruct
    public void postConstruct()
    {

        listOrders.setOrderRepository(orderRepository);

        createOrUpdateUser.setUserRepository(userRepository);

        placeOrder.setItemRepository(itemRepository);
        placeOrder.setUserRepository(userRepository);
        placeOrder.setOrderRepository(orderRepository);
    }

    public List<Order> listOrders()
    {
        return listOrders.listOrders();
    }

    public String placeOrder(List<String> items, String userName) throws NotFoundException
    {
        createOrUpdateUser.createOrUpdateUser(userName);
        return placeOrder.placeOrder(items, userName);
    }
}
