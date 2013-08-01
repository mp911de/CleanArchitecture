package biz.paluch.clean.architecture.backend.persistence;

import biz.paluch.clean.architecture.applicationmodel.Order;
import biz.paluch.clean.architecture.applicationmodel.OrderItem;
import biz.paluch.clean.architecture.applicationmodel.User;
import org.junit.Test;

import java.util.Date;

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
        jpaUserRepository.persist(new User("mark"));
        entityManager.flush();

        Order order = new Order();
        order.setCreatedBy(new User("mark"));
        order.setOrderDate(new Date());
        order.setOrderId("mark-42");

        order.getItems().add(new OrderItem("A"));
        order.getItems().add(new OrderItem("B"));
        order.getItems().add(new OrderItem("C"));

        jpaOrderRepository.persist(order);

    }

    @Test
    public void testFind() throws Exception
    {

        Order order = new Order();
        order.setCreatedBy(new User("mark"));
        order.setOrderDate(new Date());
        order.setOrderId("mark-42");

        order.getItems().add(new OrderItem("A"));
        order.getItems().add(new OrderItem("B"));
        order.getItems().add(new OrderItem("C"));

        Order result = jpaOrderRepository.find(order.getOrderId());

        assertEquals(order.getCreatedBy().getUserName(), result.getCreatedBy().getUserName());
        assertEquals(order.getOrderId(), result.getOrderId());
        assertEquals(order.getItems().size(), result.getItems().size());
    }
}
