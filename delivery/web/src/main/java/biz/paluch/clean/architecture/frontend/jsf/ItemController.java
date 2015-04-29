package biz.paluch.clean.architecture.frontend.jsf;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import biz.paluch.clean.architecture.applicationmodel.Item;
import biz.paluch.clean.architecture.facade.ItemService;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 * @since 02.08.13 13:10
 */
@RequestScoped
@Named("itemController")
public class ItemController {
    @EJB
    private ItemService itemService;

    @Inject
    private ItemModel itemModel;

    public List<SelectItem> getItems() {
        List<Item> items = itemService.listItems();
        List<SelectItem> result = new ArrayList<>();

        for (Item item : items) {
            SelectItem selectItem = new SelectItem(item.getItem(), item.getItem());
            result.add(selectItem);
        }

        return result;
    }

    public void createOrUpdateItem() {
        itemService.createOrUpdateItem(itemModel.getItem());
        itemModel.setItem("");
    }
}
