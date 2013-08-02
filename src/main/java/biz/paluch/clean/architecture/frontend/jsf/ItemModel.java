package biz.paluch.clean.architecture.frontend.jsf;

import javax.enterprise.inject.Model;
import java.io.Serializable;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 * @since 02.08.13 13:10
 */
@Model
public class ItemModel implements Serializable
{
    private String item;

    public String getItem()
    {
        return item;
    }

    public void setItem(String item)
    {
        this.item = item;
    }
}
