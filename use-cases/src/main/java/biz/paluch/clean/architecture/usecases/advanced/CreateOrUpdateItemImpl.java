package biz.paluch.clean.architecture.usecases.advanced;

import biz.paluch.clean.architecture.applicationmodel.Item;
import biz.paluch.clean.architecture.contracts.repositories.ItemRepository;
import biz.paluch.clean.architecture.contracts.usecases.CreateOrUpdateItem;

/**
 *
 * This Use-Case class implements the input boundary {@link CreateOrUpdateItem} and requires a dependency to
 * {@link ItemRepository}. Everything within this class is POJO-style. There is no response boundary since the completion of the
 * use case does not create any result data structure.
 *
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 * @since 02.08.13 13:02
 */
public class CreateOrUpdateItemImpl implements CreateOrUpdateItem {

    private ItemRepository itemRepository;

    public void createOrUpdateItem(String item) {

        Item theItem = itemRepository.find(item);
        if (theItem == null) {
            theItem = new Item();
            theItem.setItem(item);
            itemRepository.persist(theItem);
        }
    }

    public void setItemRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }
}
