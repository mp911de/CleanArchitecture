package biz.paluch.clean.architecture.usecases;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import biz.paluch.clean.architecture.applicationmodel.Item;
import biz.paluch.clean.architecture.applicationmodel.NotFoundException;
import biz.paluch.clean.architecture.applicationmodel.Order;
import biz.paluch.clean.architecture.applicationmodel.User;
import biz.paluch.clean.architecture.commons.StaticDateProvider;
import biz.paluch.clean.architecture.contracts.repositories.ItemRepository;
import biz.paluch.clean.architecture.contracts.repositories.OrderRepository;
import biz.paluch.clean.architecture.contracts.repositories.UserRepository;
import biz.paluch.clean.architecture.contracts.usecases.PlaceOrderOutput;
import biz.paluch.clean.architecture.contracts.usecases.PlaceOrderRequest;
import biz.paluch.clean.architecture.usecases.advanced.PlaceOrderImpl;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 * @since 01.08.13 07:31
 */
@RunWith(MockitoJUnitRunner.class)
public class PlaceOrderImplTest {
    public static final String ITEM_NAME_SCISSORS = "Scissors";
    public static final String ITEM_NAME_PAPER = "Paper";
    public static final String ITEM_NAME_GLUE = "Glue";
    public static final String USER_NAME_MARK = "mark";

    private PlaceOrderImpl sut = new PlaceOrderImpl();

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PlaceOrderOutput orderOutput;

    private PlaceOrderRequest request = new PlaceOrderRequest();

    @Before
    public void setUp() throws Exception {
        sut.setItemRepository(itemRepository);
        sut.setOrderRepository(orderRepository);
        sut.setUserRepository(userRepository);
    }

    @Test
    public void testPlaceOrder() throws Exception {
        List<String> items = Arrays.asList(ITEM_NAME_SCISSORS, ITEM_NAME_PAPER, ITEM_NAME_GLUE);

        mockItemRepository();
        mockUserRepository();
        mockOrderRepository();

        request.items = items;
        request.userName = USER_NAME_MARK;

        sut.placeOrder(request, orderOutput);

        verify(orderOutput).onResponse("mark-42");
    }

    @Test
    public void testPlaceOrderAndVerify() throws Exception {
        List<String> items = Arrays.asList(ITEM_NAME_SCISSORS, ITEM_NAME_PAPER, ITEM_NAME_GLUE);

        mockItemRepository();
        mockUserRepository();
        mockOrderRepository();

        Date orderDate = new Date(42424242L);
        StaticDateProvider.initialize(orderDate);

        request.items = items;
        request.userName = USER_NAME_MARK;

        sut.placeOrder(request, orderOutput);

        ArgumentCaptor<Order> captor = ArgumentCaptor.forClass(Order.class);
        verify(orderRepository).persist(captor.capture());

        Order theOrder = captor.getValue();

        assertEquals(orderDate, theOrder.getOrderDate());
        assertEquals(USER_NAME_MARK, theOrder.getCreatedBy().getUserName());
        assertEquals(items.size(), theOrder.getItems().size());

        verify(orderOutput).onResponse("mark-42");

    }

    private void mockOrderRepository() {
        when(orderRepository.getNextOrderId()).thenReturn(42);
    }

    private void mockUserRepository() {
        when(userRepository.find(USER_NAME_MARK)).thenReturn(new User(USER_NAME_MARK));
    }

    private void mockItemRepository() {
        when(itemRepository.find(ITEM_NAME_SCISSORS)).thenReturn(new Item());
        when(itemRepository.find(ITEM_NAME_PAPER)).thenReturn(new Item());
        when(itemRepository.find(ITEM_NAME_GLUE)).thenReturn(new Item());
    }

    @Test(expected = NotFoundException.class)
    public void testPlaceOrderItemNotFound() throws Exception {
        List<String> items = Arrays.asList(ITEM_NAME_SCISSORS, ITEM_NAME_PAPER, ITEM_NAME_GLUE);

        mockItemRepository();

        request.items = items;
        request.userName = USER_NAME_MARK;
        sut.placeOrder(request, orderOutput);

    }

    @Test(expected = NotFoundException.class)
    public void testPlaceOrderUserNotFound() throws Exception {
        List<String> items = Arrays.asList(ITEM_NAME_SCISSORS, ITEM_NAME_PAPER, ITEM_NAME_GLUE);
        mockItemRepository();

        request.items = items;
        request.userName = USER_NAME_MARK;

        sut.placeOrder(request, orderOutput);

    }
}
