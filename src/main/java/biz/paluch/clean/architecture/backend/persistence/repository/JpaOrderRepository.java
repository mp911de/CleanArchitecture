package biz.paluch.clean.architecture.backend.persistence.repository;

import biz.paluch.clean.architecture.applicationmodel.Order;
import biz.paluch.clean.architecture.applicationmodel.OrderItem;
import biz.paluch.clean.architecture.applicationmodel.User;
import biz.paluch.clean.architecture.backend.persistence.entity.OrderEntity;
import biz.paluch.clean.architecture.backend.persistence.entity.OrderItemEntity;
import biz.paluch.clean.architecture.backend.persistence.entity.UserEntity;
import biz.paluch.clean.architecture.usecases.boundaries.OrderRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 * @since 01.08.13 08:22
 */
@ApplicationScoped
public class JpaOrderRepository implements OrderRepository
{
    @Inject
    @PersistenceUnit(unitName = "primary")
    private EntityManager entityManager;

    @Override
    public int getNextOrderId()
    {
        List<Number> maxOrderId = entityManager.createNamedQuery(OrderEntity.QUERY_COUNT).getResultList();
        if (maxOrderId.isEmpty())
        {
            return 1;
        }
        return maxOrderId.get(0).intValue() + 1;
    }

    @Override
    public void persist(Order order)
    {

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderDate(order.getOrderDate());
        orderEntity.setOrderId(order.getOrderId());

        UserEntity user = getUser(order.getCreatedBy().getUserName());
        orderEntity.setCreatedBy(user);


        for (OrderItem orderItem : order.getItems())
        {
            OrderItemEntity orderItemEntity = new OrderItemEntity();
            orderItemEntity.setOrder(orderEntity);
            orderItemEntity.setOrderItem(orderItem.getOrderItem());
            orderEntity.getItems().add(orderItemEntity);
        }

        entityManager.persist(orderEntity);
    }

    private UserEntity getUser(String userName)
    {
        return (UserEntity) entityManager.createNamedQuery(UserEntity.QUERY_FIND_BY_USERNAME)
                .setParameter("userName", userName).getSingleResult();
    }


    public void deleteAll()
    {
        entityManager.createQuery("DELETE from  " + OrderEntity.class.getSimpleName()).executeUpdate();
    }

    public Order find(String orderId)
    {
        List<OrderEntity> list = entityManager.createNamedQuery(OrderEntity.QUERY_FIND_BY_ORDERID, OrderEntity.class)
                .setParameter("orderId", orderId).getResultList();
        if (list.isEmpty())
        {
            return null;
        }

        OrderEntity orderEntity = list.get(0);

        return toOrder(orderEntity);
    }


    private Order toOrder(OrderEntity orderEntity)
    {
        Order order = new Order();


        order.setCreatedBy(new User(orderEntity.getCreatedBy().getUserName()));
        order.setOrderDate(orderEntity.getOrderDate());
        order.setOrderId(orderEntity.getOrderId());

        for (OrderItemEntity orderItemEntity : orderEntity.getItems())
        {
            order.getItems().add(new OrderItem(orderItemEntity.getOrderItem()));
        }
        return order;
    }

    @Override
    public List<Order> findOrders()
    {

        List<OrderEntity> list =
                entityManager.createNamedQuery(OrderEntity.QUERY_FIND_ALL, OrderEntity.class).getResultList();

        List<Order> result = new ArrayList<>();
        for (OrderEntity orderEntity : list)
        {
            result.add(toOrder(orderEntity));
        }
        return result;
    }

    public void setEntityManager(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }
}
