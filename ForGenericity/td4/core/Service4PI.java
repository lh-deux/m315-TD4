package td4.core;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import td4.core.PayingItem;

public class Service4PI<T extends PayingItem> {
	protected List<T> payingItemList = new ArrayList<>();

	public Service4PI(List<T> payingItemList) {
		this.payingItemList = payingItemList;
	}

	protected List<T> getpayingItemList() {
		return payingItemList;
	}

	public List<T> sortedByPrice() {
		payingItemList.sort(Comparator.comparing(PayingItem::getPrice));
		return new ArrayList<T>(payingItemList);
	}

	private void sortedByPriceInPlace() {
		payingItemList.sort(Comparator.comparing(PayingItem::getPrice));
	}
	public T lessExpensiveItem() {
		sortedByPriceInPlace();
		return payingItemList.get(0);
	}

	public void add(T payingItem) {
		payingItemList.add(payingItem);
	}

}