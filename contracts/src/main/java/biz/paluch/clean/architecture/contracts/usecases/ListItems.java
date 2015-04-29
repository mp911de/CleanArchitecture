package biz.paluch.clean.architecture.contracts.usecases;

import biz.paluch.clean.architecture.contracts.repositories.ItemRepository;

/**
 * List items Use-Case.
 * 
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 */
public interface ListItems {

    /**
     * List all items and pass these to the {@link ListItemsOutput} output.
     * 
     * @param listItemsOutput
     */
    void listItems(ListItemsOutput listItemsOutput);

    /**
     * Setter to {@link ItemRepository} dependency.
     * 
     * @param itemRepository
     */
    void setItemRepository(ItemRepository itemRepository);
}
