package biz.paluch.clean.architecture.applicationmodel;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 * @since 01.08.13 07:24
 */
public class OrderItem extends AbstractModel {
    private String orderItem;

    public OrderItem() {
    }

    public OrderItem(String orderItem) {
        this.orderItem = orderItem;
    }

    public String getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(String orderItem) {
        this.orderItem = orderItem;
    }
}
