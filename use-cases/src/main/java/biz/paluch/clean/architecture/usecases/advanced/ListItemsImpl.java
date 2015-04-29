package biz.paluch.clean.architecture.usecases.advanced;

import biz.paluch.clean.architecture.contracts.repositories.ItemRepository;
import biz.paluch.clean.architecture.contracts.usecases.ListItems;
import biz.paluch.clean.architecture.contracts.usecases.ListItemsOutput;

/**
 *
 * This Use-Case class implements the input boundary {@link ListItems} and requires a dependency to {@link ItemRepository}.
 * Everything within this class is POJO-style. There is no response boundary since the completion of the use case does not
 * create any result data structure.
 *
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 * @since 02.08.13 13:02
 */
public class ListItemsImpl implements ListItems {
    private ItemRepository itemRepository;

    @Override
    public void listItems(ListItemsOutput listItemsOutput) {
        listItemsOutput.onResponse(itemRepository.findAll());
    }

    @Override
    public void setItemRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

}
