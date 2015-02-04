package biz.paluch.clean.architecture.frontend.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 * @since 02.08.13 13:39
 */
@Model
@SessionScoped
public class OrderModel implements Serializable
{
    private String userName;
    private List<OrderItem> orderItems = new ArrayList<>();
    private String selectedItem;

    public String getSelectedItem()
    {
        return selectedItem;
    }

    public void setSelectedItem(String selectedItem)
    {
        this.selectedItem = selectedItem;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public List<OrderItem> getOrderItems()
    {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems)
    {
        this.orderItems = orderItems;
    }
}
