package biz.paluch.clean.architecture.usecases;

import biz.paluch.clean.architecture.applicationmodel.Item;
import biz.paluch.clean.architecture.usecases.boundaries.ItemRepository;

import java.util.List;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 * @since 02.08.13 13:02
 */
public class ListItems
{
    private ItemRepository itemRepository;

    public List<Item> listItems()
    {
        return itemRepository.findAll();
    }

    public void setItemRepository(ItemRepository itemRepository)
    {
        this.itemRepository = itemRepository;
    }
}
