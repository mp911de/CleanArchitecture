package biz.paluch.clean.architecture.external.jpa.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 * @since 01.08.13 08:18
 */
@Entity
@Table(name = "Orders")
@NamedQueries({ @NamedQuery(name = OrderEntity.QUERY_COUNT, query = "SELECT count(orderId) from OrderEntity"),
        @NamedQuery(name = OrderEntity.QUERY_FIND_BY_ORDERID, query = "SELECT o from OrderEntity o where o.orderId= :orderId"),
        @NamedQuery(name = OrderEntity.QUERY_FIND_ALL, query = "SELECT o from  OrderEntity o ") })
public class OrderEntity {
    public static final String QUERY_COUNT = "OrderEntity.count";
    public static final String QUERY_FIND_BY_ORDERID = "OrderEntity.findByOrderId";
    public static final String QUERY_FIND_ALL = "OrderEntity.findAll";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Basic
    private String orderId;

    @Basic
    private Date orderDate;

    @ManyToOne()
    private UserEntity createdBy;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderItemEntity> items = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public UserEntity getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserEntity createdBy) {
        this.createdBy = createdBy;
    }

    public List<OrderItemEntity> getItems() {
        return items;
    }

    public void setItems(List<OrderItemEntity> items) {
        this.items = items;
    }
}
