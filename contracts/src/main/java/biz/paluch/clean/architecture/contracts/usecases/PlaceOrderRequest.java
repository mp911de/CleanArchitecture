package biz.paluch.clean.architecture.contracts.usecases;

import java.util.List;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 */
public class PlaceOrderRequest {

    public List<String> items;
    public String userName;

}
