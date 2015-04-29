package biz.paluch.clean.architecture.contracts.usecases;

import biz.paluch.clean.architecture.applicationmodel.NotFoundException;

/**
 * This use-case input boundary does not define any dependencies. The {@link #placeOrder(PlaceOrderRequest, PlaceOrderOutput)}
 * method accept a request data structure and an output boundary for returning the result of the operation.
 * 
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 */
public interface PlaceOrder {

    /**
     * Places an order.
     * 
     * @param request
     * @param output
     */
    void placeOrder(PlaceOrderRequest request, PlaceOrderOutput output) throws NotFoundException;
}
