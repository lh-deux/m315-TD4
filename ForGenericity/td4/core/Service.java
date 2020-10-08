package td4.core;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import td4.core.PayingItem;

public class Service<T extends PayingItem> {
	protected List<T> payingItemList = new ArrayList<>();

	public Service(List<T> payingItemList) {
		this.payingItemList = payingItemList;
	}

	protected List<T> getpayingItemList() {
		return payingItemList;
	}

	public List<T> sortedByPrice() {
		payingItemList.sort(Comparator.comparing(PayingItem::getPrice));
		return new ArrayList<T>(payingItemList);
	}

	public T lessExpensiveItem() {
		sortedByPrice();
		return payingItemList.get(0);
	}

	public void add(T payingItem) {
		payingItemList.add(payingItem);
	}

}