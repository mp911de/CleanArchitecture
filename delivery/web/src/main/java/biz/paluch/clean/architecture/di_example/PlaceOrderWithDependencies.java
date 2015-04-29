package biz.paluch.clean.architecture.di_example;

import javax.inject.Inject;

import biz.paluch.clean.architecture.contracts.repositories.ItemRepository;
import biz.paluch.clean.architecture.contracts.repositories.OrderRepository;
import biz.paluch.clean.architecture.contracts.repositories.UserRepository;
import biz.paluch.clean.architecture.usecases.advanced.PlaceOrderImpl;

/**
 * This Use-Case wrapper extends the {@link PlaceOrderImpl} use case class in order to use declarative dependency injection.
 * 
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 */
public class PlaceOrderWithDependencies extends PlaceOrderImpl {

    @Inject
    @Override
    public void setOrderRepository(OrderRepository orderRepository) {
        super.setOrderRepository(orderRepository);
    }

    @Inject
    @Override
    public void setItemRepository(ItemRepository itemRepository) {
        super.setItemRepository(itemRepository);
    }

    @Inject
    @Override
    public void setUserRepository(UserRepository userRepository) {
        super.setUserRepository(userRepository);
    }
}
