package biz.paluch.clean.architecture.contracts.usecases;

import java.util.List;

import biz.paluch.clean.architecture.applicationmodel.Item;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 */
public interface ListItemsOutput {

    void onResponse(List<Item> items);
}
