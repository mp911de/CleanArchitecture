package biz.paluch.clean.architecture.frontend.rest;

import biz.paluch.clean.architecture.applicationmodel.Item;
import biz.paluch.clean.architecture.facade.ItemService;
import biz.paluch.clean.architecture.frontend.jsf.ItemModel;
import org.jboss.resteasy.annotations.providers.jaxb.Wrapped;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

@Path("/items")
@RequestScoped
public class ItemResource
{

    @EJB
    private ItemService itemService;

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public ItemsRepresentation list()
    {
        ItemsRepresentation result = new ItemsRepresentation();
        result.setItems(itemService.listItems());
        return result;
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("{itemId}")
    public JAXBElement<Item> get(@PathParam("itemId") String itemId)
    {
        Item item = itemService.find(itemId);
        if (item == null)
        {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        return new JAXBElement<>(new QName("item"), Item.class, item);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public void createOrUpdate(JAXBElement<Item> item)
    {
        itemService.createOrUpdateItem(item.getValue().getItem());
    }

}
