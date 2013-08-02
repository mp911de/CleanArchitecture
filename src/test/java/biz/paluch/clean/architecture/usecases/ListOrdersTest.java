package biz.paluch.clean.architecture.usecases;

import biz.paluch.clean.architecture.applicationmodel.Order;
import biz.paluch.clean.architecture.usecases.boundaries.OrderRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 * @since 02.08.13 11:13
 */
@RunWith(MockitoJUnitRunner.class)
public class ListOrdersTest
{

    private ListOrders sut = new ListOrders();

    @Mock
    private OrderRepository orderRepository;

    @Before
    public void before() throws Exception
    {
        sut.setOrderRepository(orderRepository);

    }

    @Test
    public void testListOrders() throws Exception
    {
        when(orderRepository.findOrders()).thenReturn(Arrays.asList(new Order()));

        List<Order> result = sut.listOrders();
        assertEquals(1, result.size());

    }
}
