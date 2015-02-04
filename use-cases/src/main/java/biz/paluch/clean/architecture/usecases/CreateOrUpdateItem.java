package biz.paluch.clean.architecture.usecases;

import javax.inject.Inject;

import biz.paluch.clean.architecture.applicationmodel.Item;
import biz.paluch.clean.architecture.dataaccess.ItemRepository;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 * @since 02.08.13 13:02
 */
public class CreateOrUpdateItem {
    private ItemRepository itemRepository;

    public void createOrUpdateItem(String item) {

        Item theItem = itemRepository.find(item);
        if (theItem == null) {
            theItem = new Item();
            theItem.setItem(item);
            itemRepository.persist(theItem);
        }
    }

    @Inject
    public void setItemRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }
}
