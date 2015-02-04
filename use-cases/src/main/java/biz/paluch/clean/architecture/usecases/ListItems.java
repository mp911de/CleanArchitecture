package biz.paluch.clean.architecture.usecases;

import java.util.List;

import javax.inject.Inject;

import biz.paluch.clean.architecture.applicationmodel.Item;
import biz.paluch.clean.architecture.contracts.ItemRepository;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 * @since 02.08.13 13:02
 */
public class ListItems {
    private ItemRepository itemRepository;

    public List<Item> listItems() {
        return itemRepository.findAll();
    }

    @Inject
    public void setItemRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }
}
