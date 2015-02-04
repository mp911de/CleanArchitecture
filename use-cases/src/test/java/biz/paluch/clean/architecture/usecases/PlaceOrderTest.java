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
import org.mockito.runners.MockitoJUnitRunner;

import biz.paluch.clean.architecture.applicationmodel.Item;
import biz.paluch.clean.architecture.applicationmodel.NotFoundException;
import biz.paluch.clean.architecture.applicationmodel.Order;
import biz.paluch.clean.architecture.applicationmodel.User;
import biz.paluch.clean.architecture.commons.StaticDateProvider;
import biz.paluch.clean.architecture.contracts.ItemRepository;
import biz.paluch.clean.architecture.contracts.OrderRepository;
import biz.paluch.clean.architecture.contracts.UserRepository;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 * @since 01.08.13 07:31
 */
@RunWith(MockitoJUnitRunner.class)
public class PlaceOrderTest {
    public static final String ITEM_NAME_SCISSORS = "Scissors";
    public static final String ITEM_NAME_PAPER = "Paper";
    public static final String ITEM_NAME_GLUE = "Glue";
    public static final String USER_NAME_MARK = "mark";

    private PlaceOrder sut = new PlaceOrder();

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private UserRepository userRepository;

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

        String orderIdResult = sut.placeOrder(items, USER_NAME_MARK);

        assertEquals("mark-42", orderIdResult);
    }

    @Test
    public void testPlaceOrderAndVerify() throws Exception {
        List<String> items = Arrays.asList(ITEM_NAME_SCISSORS, ITEM_NAME_PAPER, ITEM_NAME_GLUE);

        mockItemRepository();
        mockUserRepository();
        mockOrderRepository();

        Date orderDate = new Date(42424242L);
        StaticDateProvider.initialize(orderDate);

        String orderIdResult = sut.placeOrder(items, USER_NAME_MARK);

        ArgumentCaptor<Order> captor = ArgumentCaptor.forClass(Order.class);
        verify(orderRepository).persist(captor.capture());

        Order theOrder = captor.getValue();

        assertEquals(orderDate, theOrder.getOrderDate());
        assertEquals(USER_NAME_MARK, theOrder.getCreatedBy().getUserName());
        assertEquals(orderIdResult, theOrder.getOrderId());
        assertEquals(items.size(), theOrder.getItems().size());

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
        sut.placeOrder(items, USER_NAME_MARK);

    }

    @Test(expected = NotFoundException.class)
    public void testPlaceOrderUserNotFound() throws Exception {
        List<String> items = Arrays.asList(ITEM_NAME_SCISSORS, ITEM_NAME_PAPER, ITEM_NAME_GLUE);
        mockItemRepository();
        sut.placeOrder(items, USER_NAME_MARK);

    }
}
