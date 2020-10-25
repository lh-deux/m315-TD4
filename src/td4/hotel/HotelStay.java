package td4.hotel;

import td4.core.Product;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class HotelStay implements Product {
    private LocalDate inDate;
    private LocalDate outDate;
    private Hotel hotel;
    public HotelStay(Hotel hotel, LocalDate inDate, LocalDate outDate) {
        super();
        this.hotel = hotel;
        this.inDate = inDate;
        this.outDate = outDate;
    }

    @Override
    public double getPrice() {
        return PRODUCT_MARGIN*hotel.getPrice()*outDate.until(inDate, ChronoUnit.DAYS);
    }

    public LocalDate getInDate() {
        return inDate;
    }

    public LocalDate getOutDate() {
        return outDate;
    }

    public boolean dateIsBetween(LocalDate compare){
        return inDate.isBefore(compare) && outDate.isAfter(compare);
    }

    public Hotel getHotel(){
        return hotel;
    }
}
