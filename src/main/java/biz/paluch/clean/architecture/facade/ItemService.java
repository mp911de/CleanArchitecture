package biz.paluch.clean.architecture.facade;

import biz.paluch.clean.architecture.applicationmodel.Item;
import biz.paluch.clean.architecture.applicationmodel.NotFoundException;
import biz.paluch.clean.architecture.applicationmodel.Order;
import biz.paluch.clean.architecture.usecases.CreateOrUpdateItem;
import biz.paluch.clean.architecture.usecases.CreateOrUpdateUser;
import biz.paluch.clean.architecture.usecases.ListItems;
import biz.paluch.clean.architecture.usecases.ListOrders;
import biz.paluch.clean.architecture.usecases.PlaceOrder;
import biz.paluch.clean.architecture.usecases.boundaries.ItemRepository;
import biz.paluch.clean.architecture.usecases.boundaries.OrderRepository;
import biz.paluch.clean.architecture.usecases.boundaries.UserRepository;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 * @since 02.08.13 12:45
 */
@Stateless
public class ItemService
{

    @Inject
    private ItemRepository itemRepository;


    @Inject
    private ListItems listItems;

    @Inject
    private CreateOrUpdateItem createOrUpdateItem;

    @PostConstruct
    public void postConstruct()
    {
        createOrUpdateItem.setItemRepository(itemRepository);

        listItems.setItemRepository(itemRepository);
    }

    public List<Item> listItems()
    {
        return listItems.listItems();
    }

    public void createOrUpdateItem(String item)
    {
        createOrUpdateItem.createOrUpdateItem(item);
    }

    public Item find(String itemId)
    {
        return itemRepository.find(itemId);
    }
}
