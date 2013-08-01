package biz.paluch.clean.architecture.usecases;

import biz.paluch.clean.architecture.applicationmodel.Item;
import biz.paluch.clean.architecture.applicationmodel.NotFoundException;
import biz.paluch.clean.architecture.applicationmodel.User;
import biz.paluch.clean.architecture.usecases.boundaries.ItemRepository;
import biz.paluch.clean.architecture.usecases.boundaries.UserRepository;

import java.util.List;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 * @since 01.08.13 07:49
 */
public class PlaceOrderValidator
{
    private ItemRepository itemRepository;
    private UserRepository userRepository;

    private PlaceOrderValidator(ItemRepository itemRepository, UserRepository userRepository)
    {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    public static PlaceOrderValidator newInstance(ItemRepository itemRepository, UserRepository userRepository)
    {
        return new PlaceOrderValidator(itemRepository, userRepository);
    }

    public void validate(List<String> items, String userName)
    {
        validateItems(items);
        validateUser(userName);
    }

    private void validateUser(String userName)
    {
        User user = userRepository.find(userName);
        if (user == null)
        {
            throw new NotFoundException("User " + userName + " not found");
        }
    }

    private void validateItems(List<String> items)
    {
        for (String item : items)
        {
            validateItem(item);

        }
    }

    private void validateItem(String item)
    {
        Item itemInCatalog = itemRepository.find(item);
        if (itemInCatalog == null)
        {
            throw new NotFoundException("Item " + item + " not found");
        }
    }
}
