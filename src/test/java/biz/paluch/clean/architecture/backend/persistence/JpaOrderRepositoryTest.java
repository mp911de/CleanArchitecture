package biz.paluch.clean.architecture.backend.persistence;

import biz.paluch.clean.architecture.applicationmodel.Order;
import biz.paluch.clean.architecture.applicationmodel.OrderItem;
import biz.paluch.clean.architecture.applicationmodel.User;
import biz.paluch.clean.architecture.backend.persistence.repository.JpaOrderRepository;
import biz.paluch.clean.architecture.backend.persistence.repository.JpaUserRepository;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 * @since 01.08.13 08:50
 */
public class JpaOrderRepositoryTest extends AbstractJpaTest
{
    private JpaUserRepository jpaUserRepository = new JpaUserRepository();
    private JpaOrderRepository jpaOrderRepository = new JpaOrderRepository();

    @Override
    public void before() throws Exception
    {
        super.before();
        jpaOrderRepository.setEntityManager(entityManager);
        jpaUserRepository.setEntityManager(entityManager);
    }

    @Test
    public void testGetNextOrderId() throws Exception
    {
        int result = jpaOrderRepository.getNextOrderId();
        assertEquals(1, result);
    }

    @Test
    public void testPersistAndFind() throws Exception
    {
        jpaUserRepository.store(new User("mark"));
        entityManager.flush();

        Order order = mockOrder();

        jpaOrderRepository.persist(order);

    }

    private Order mockOrder()
    {
        Order order = new Order();
        order.setCreatedBy(new User("mark"));
        order.setOrderDate(new Date());
        order.setOrderId("mark-42");

        order.getItems().add(new OrderItem("A"));
        order.getItems().add(new OrderItem("B"));
        order.getItems().add(new OrderItem("C"));
        return order;
    }

    @Test
    public void testFind() throws Exception
    {

        Order order = mockOrder();
        createOrder(order);

        Order result = jpaOrderRepository.find(order.getOrderId());

        assertEquals(order.getCreatedBy().getUserName(), result.getCreatedBy().getUserName());
        assertEquals(order.getOrderId(), result.getOrderId());
        assertEquals(order.getItems().size(), result.getItems().size());
    }

    @Test
    public void testFindOrders() throws Exception
    {
        Order order = mockOrder();
        createOrder(order);

        List<Order> result = jpaOrderRepository.findOrders();
        assertEquals(1, result.size());

    }

    private void createOrder(Order order)
    {

        jpaUserRepository.store(new User("mark"));
        jpaOrderRepository.persist(order);
        entityManager.flush();
    }
}
