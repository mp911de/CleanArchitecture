package biz.paluch.clean.architecture.facade;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import biz.paluch.clean.architecture.applicationmodel.Item;
import biz.paluch.clean.architecture.dataaccess.ItemRepository;
import biz.paluch.clean.architecture.usecases.CreateOrUpdateItem;
import biz.paluch.clean.architecture.usecases.ListItems;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 * @since 02.08.13 12:45
 */
@Stateless
public class ItemService {

    @Inject
    private ItemRepository itemRepository;

    @Inject
    private ListItems listItems;

    @Inject
    private CreateOrUpdateItem createOrUpdateItem;

    public List<Item> listItems() {
        return listItems.listItems();
    }

    public void createOrUpdateItem(String item) {
        createOrUpdateItem.createOrUpdateItem(item);
    }

    public Item find(String itemId) {
        return itemRepository.find(itemId);
    }
}
