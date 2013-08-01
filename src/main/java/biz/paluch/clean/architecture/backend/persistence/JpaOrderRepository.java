package biz.paluch.clean.architecture.backend.persistence;

import biz.paluch.clean.architecture.applicationmodel.Order;
import biz.paluch.clean.architecture.applicationmodel.OrderItem;
import biz.paluch.clean.architecture.applicationmodel.User;
import biz.paluch.clean.architecture.usecases.boundaries.OrderRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 * @since 01.08.13 08:22
 */
@ApplicationScoped
public class JpaOrderRepository implements OrderRepository
{
    @Inject
    private EntityManager entityManager;

    @Override
    public int getNextOrderId()
    {
        List<Number> maxOrderId =
                entityManager.createQuery("SELECT count(orderId) from " + OrderEntity.class.getSimpleName())
                        .getResultList();
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
        return (UserEntity) entityManager
                .createQuery("SELECT u from " + UserEntity.class.getSimpleName() + " u where u.userName = :userName")
                .setParameter("userName", userName).getSingleResult();
    }


    public void setEntityManager(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    public void deleteAll()
    {
        entityManager.createQuery("DELETE from  " + OrderEntity.class.getSimpleName()).executeUpdate();
    }

    public Order find(String orderId)
    {
        List<OrderEntity> list = entityManager.createQuery("SELECT o from " + OrderEntity.class.getSimpleName() +
                                                                   " o where o.orderId= :orderId", OrderEntity.class)
                .setParameter("orderId", orderId).getResultList();
        if (list.isEmpty())
        {
            return null;
        }


        List<OrderItemEntity> list2 = entityManager.createQuery("SELECT o from " + OrderItemEntity.class.getSimpleName() +
                                                                           " o ", OrderItemEntity.class).getResultList();
        
        Order order = new Order();
        OrderEntity orderEntity = list.get(0);

        order.setCreatedBy(new User(orderEntity.getCreatedBy().getUserName()));
        order.setOrderDate(orderEntity.getOrderDate());
        order.setOrderId(orderEntity.getOrderId());

        for (OrderItemEntity orderItemEntity : orderEntity.getItems())
        {
            order.getItems().add(new OrderItem(orderItemEntity.getOrderItem()));
        }


        return order;
    }
}
