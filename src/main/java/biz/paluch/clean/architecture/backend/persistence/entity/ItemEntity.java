package biz.paluch.clean.architecture.backend.persistence.entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 * @since 01.08.13 08:18
 */
@Entity
@Table(name = "Items")
@NamedQueries({ @NamedQuery(name = ItemEntity.QUERY_FIND_BY_ITEM_NAME,
                            query = "SELECT i from ItemEntity i where i.item = :item"),
                      @NamedQuery(name = ItemEntity.QUERY_FIND_ALL, query = "SELECT i from ItemEntity i ") })
public class ItemEntity
{
    public static final String QUERY_FIND_BY_ITEM_NAME = "ItemEntity.findByItem";
    public static final String QUERY_FIND_ALL = "ItemEntity.findAll";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Basic
    private String item;


    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getItem()
    {
        return item;
    }

    public void setItem(String item)
    {
        this.item = item;
    }
}
