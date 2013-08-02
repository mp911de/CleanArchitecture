package biz.paluch.clean.architecture.backend.persistence.repository;

import biz.paluch.clean.architecture.applicationmodel.Item;
import biz.paluch.clean.architecture.backend.persistence.entity.ItemEntity;
import biz.paluch.clean.architecture.usecases.boundaries.ItemRepository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 * @since 02.08.13 12:55
 */
public class JpaItemRepository implements ItemRepository
{
    @Inject
    @PersistenceUnit(unitName = "primary")
    private EntityManager entityManager;

    @Override
    public Item find(String item)
    {

        List<ItemEntity> list = entityManager.createNamedQuery(ItemEntity.QUERY_FIND_BY_ITEM_NAME, ItemEntity.class)
                .setParameter("item", item).getResultList();
        if (list.isEmpty())
        {
            return null;
        }

        ItemEntity itemEntity = list.get(0);

        return toItem(itemEntity);
    }

    private Item toItem(ItemEntity itemEntity)
    {
        Item result = new Item();
        result.setItem(itemEntity.getItem());

        return result;
    }

    @Override
    public void persist(Item item)
    {

        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setItem(item.getItem());
        entityManager.persist(itemEntity);
    }

    @Override
    public List<Item> findAll()
    {
        List<ItemEntity> list = entityManager.createNamedQuery(ItemEntity.QUERY_FIND_ALL, ItemEntity.class).getResultList();
        List<Item> result = new ArrayList<>();
        for (ItemEntity itemEntity : list)
        {
            result.add(toItem(itemEntity));
        }
        
        return result;
    }

    public void setEntityManager(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }
}
