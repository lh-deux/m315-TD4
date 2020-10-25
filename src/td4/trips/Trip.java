package td4.trips;

import td4.core.Description;
import td4.core.Product;
import td4.core.Service4PI;

import java.util.ArrayList;
import java.util.List;

public class Trip extends Service4PI<Product> {

    Description desc;

    public Trip(Description desc) {
        super(new ArrayList<>());
        this.desc = desc;
    }

    public Description getDesc() {
        return desc;
    }

    public void setDesc(Description desc) {
        this.desc = desc;
    }

    public List<Product> getProducts(){
        return payingItemList;
    }

    public void setProducts(List<Product> payingItemList){
        this.payingItemList = payingItemList;
    }

    public double getPrice(){
        double sum = 0;
        for(Product p : payingItemList){
            sum+= p.getPrice();
        }
        return sum;
    }

    public void addProduct(Product p){
        if(!payingItemList.contains(p))
            payingItemList.add(p);
    }

    @Override
    public String toString() {
        return "Trip{" +
                "desc=" + desc +
                ", payingItemList=" + payingItemList +
                '}';
    }
}
