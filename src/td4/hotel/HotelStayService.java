package td4.hotel;

import td4.Service;
import td4.core.Description;
import td4.core.Service4PI;
import td4.util.DateTools;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HotelStayService extends Service4PI<HotelStay> implements Service {

    private List<Hotel> hotels;

    public HotelStayService(List<HotelStay> payingItemList, List<Hotel> h) {
        super(payingItemList);
        hotels = h;
    }

    public HotelStayService(List<HotelStay> payingItemList) {
        super(payingItemList);
        hotels = new ArrayList<>();
    }

    public HotelStay find(Description desc){
        List<HotelStay> availableStays = new ArrayList<>();
        List<Hotel> availableHotels;
        LocalDate jourSortie = DateTools.addDays(desc.getDateDepart(),desc.getDuree());
        for(HotelStay hs : payingItemList){
            //on indexe d'abord les réservations existantes
            if(hs.getInDate().isEqual(desc.getDateDepart()) && hs.getOutDate().isEqual(jourSortie))
                availableStays.add(hs);
        }
        //on calcule ensuite les Hotels pleins durant le séjour
        availableHotels = getAvailableHotels(desc.getDateDepart(),jourSortie);
        //on enlève toutes les réservations déjà pleines
        //=> on enlève toutes les réservations dont leur hôtel ne figure pas dans la liste des Hotels disponible
        availableStays.removeIf((HotelStay hs) -> !availableHotels.contains(hs.getHotel()));

        //Pour les autres Hotels, on crée des HotelStays qui correspondent à la date donnée, puisque ces Hotels sont disponibles.
        for(Hotel h : availableHotels){
            availableStays.add(new HotelStay(h,desc.getDateDepart(),jourSortie));
        }
        availableStays.sort(Comparator.comparing(HotelStay::getPrice));
        return availableStays.get(0);
    }

    /**
     * Give a Hotel's availability in a given timeframe.
     * @param h - The hotel
     * @param in - Date of arrival
     * @param out - Date of departure
     * @return a boolean whether someone can book the hotel for this timeframe or not
     */
    public boolean hotelIsAvailable(Hotel h,LocalDate in, LocalDate out){
        int hotelReservations = 0;
        for(HotelStay hs : payingItemList){
            if(hs.getHotel().equals(h) && (hs.dateIsBetween(in) || hs.dateIsBetween(out)))
                hotelReservations++;
        }
        return hotelReservations < h.getCapacity();
    }

    /**
     * Give a list of Hotels available in a given timeframe.
     * @param inDate - Date of arrival
     * @param outDate - Date of departure
     * @return a list of hotels available for the timeframe.
     */
    public List<Hotel> getAvailableHotels(LocalDate inDate,LocalDate outDate){
        List<Hotel> availableHotels = new ArrayList<>();
        for(Hotel h : hotels){
            if(hotelIsAvailable(h,inDate,outDate))
                availableHotels.add(h);
        }
        return availableHotels;
    }


}
