package biz.paluch.clean.architecture.dataaccess;

import java.util.List;

import biz.paluch.clean.architecture.applicationmodel.Item;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 * @since 01.08.13 07:29
 */
public interface ItemRepository {

    Item find(String item);

    List<Item> findAll();

    void persist(Item item);
}
