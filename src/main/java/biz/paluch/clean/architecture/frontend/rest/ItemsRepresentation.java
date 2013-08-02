package biz.paluch.clean.architecture.frontend.rest;

import biz.paluch.clean.architecture.applicationmodel.Item;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 * @since 02.08.13 13:53
 */
@XmlRootElement
public class ItemsRepresentation
{
    private List<Item> items = new ArrayList<>();

    public List<Item> getItems()
    {
        return items;
    }

    public void setItems(List<Item> items)
    {
        this.items = items;
    }
}
