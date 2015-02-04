package biz.paluch.clean.architecture.facade;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import biz.paluch.clean.architecture.applicationmodel.NotFoundException;
import biz.paluch.clean.architecture.applicationmodel.Order;
import biz.paluch.clean.architecture.dataaccess.ItemRepository;
import biz.paluch.clean.architecture.dataaccess.OrderRepository;
import biz.paluch.clean.architecture.dataaccess.UserRepository;
import biz.paluch.clean.architecture.usecases.CreateOrUpdateUser;
import biz.paluch.clean.architecture.usecases.ListOrders;
import biz.paluch.clean.architecture.usecases.PlaceOrder;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 * @since 02.08.13 12:45
 */
@Stateless
public class OrderService {

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

    public List<Order> listOrders() {
        return listOrders.listOrders();
    }

    public String placeOrder(List<String> items, String userName) throws NotFoundException {
        createOrUpdateUser.createOrUpdateUser(userName);
        return placeOrder.placeOrder(items, userName);
    }
}
