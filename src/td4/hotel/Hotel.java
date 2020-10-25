package td4.hotel;

import td4.util.HotelFullException;

import java.util.Objects;

public class Hotel {

    private final int capacity;
    private int quantity;
    private double price;

    public Hotel(int capacity, int quantity,int price) {
        this.capacity = capacity;
        this.quantity = quantity;
        this.price = price;
    }

    public Hotel(int humanCapacity) {
        this.capacity = humanCapacity;
        this.quantity = 0;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void bookNight() throws HotelFullException {
        if(isFull())
            throw new HotelFullException("Hotel already full.");
        else
            quantity++;
    }
    public int getQuantity(){
        return quantity;
    }

    public boolean isEmpty(){
        return quantity > 0;
    }

    public boolean isFull(){
        return capacity == quantity;
    }

    public double getPrice(){
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hotel hotel = (Hotel) o;
        return capacity == hotel.capacity &&
                quantity == hotel.quantity &&
                Double.compare(hotel.price, price) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(capacity, quantity, price);
    }
}
