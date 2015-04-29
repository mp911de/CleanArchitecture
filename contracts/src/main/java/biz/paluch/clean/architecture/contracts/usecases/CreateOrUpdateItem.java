package biz.paluch.clean.architecture.contracts.usecases;

import biz.paluch.clean.architecture.contracts.repositories.ItemRepository;

/**
 * Create or update item Use-Case.
 * 
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 */
public interface CreateOrUpdateItem {

    /**
     * Creates a new or updates an existing item.
     * 
     * @param item
     */
    void createOrUpdateItem(String item);

    /**
     * Setter to {@link ItemRepository} dependency.
     * 
     * @param itemRepository
     */
    void setItemRepository(ItemRepository itemRepository);
}
