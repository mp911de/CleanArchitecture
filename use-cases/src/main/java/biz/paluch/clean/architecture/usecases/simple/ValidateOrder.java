package biz.paluch.clean.architecture.usecases.simple;

import java.util.List;

import biz.paluch.clean.architecture.applicationmodel.Item;
import biz.paluch.clean.architecture.applicationmodel.NotFoundException;
import biz.paluch.clean.architecture.applicationmodel.User;
import biz.paluch.clean.architecture.contracts.repositories.ItemRepository;
import biz.paluch.clean.architecture.contracts.repositories.UserRepository;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 * @since 01.08.13 07:49
 */
public class ValidateOrder {
    private ItemRepository itemRepository;
    private UserRepository userRepository;

    private ValidateOrder(ItemRepository itemRepository, UserRepository userRepository) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    public static ValidateOrder newInstance(ItemRepository itemRepository, UserRepository userRepository) {
        return new ValidateOrder(itemRepository, userRepository);
    }

    public void validate(List<String> items, String userName) {
        validateItems(items);
        validateUser(userName);
    }

    private void validateUser(String userName) {
        User user = userRepository.find(userName);
        if (user == null) {
            throw new NotFoundException("User " + userName + " not found");
        }
    }

    private void validateItems(List<String> items) {
        for (String item : items) {
            validateItem(item);

        }
    }

    private void validateItem(String item) {
        Item itemInCatalog = itemRepository.find(item);
        if (itemInCatalog == null) {
            throw new NotFoundException("Item " + item + " not found");
        }
    }
}
