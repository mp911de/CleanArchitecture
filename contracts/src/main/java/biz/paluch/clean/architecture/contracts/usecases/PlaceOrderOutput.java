package biz.paluch.clean.architecture.contracts.usecases;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 */
public interface PlaceOrderOutput {
    void onResponse(String orderId);
}
