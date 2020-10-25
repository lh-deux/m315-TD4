package td4.core;

import java.time.LocalDate;

public class Description {

    LocalDate dateDepart;
    String depart;
    String arrivee;
    int duree;

    public Description(LocalDate dateDepart, String depart, String arrivee, int duree) {
        this.dateDepart = dateDepart;
        this.depart = depart;
        this.arrivee = arrivee;
        this.duree = duree;
    }

    public LocalDate getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(LocalDate dateDepart) {
        this.dateDepart = dateDepart;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getArrivee() {
        return arrivee;
    }

    public void setArrivee(String arrivee) {
        this.arrivee = arrivee;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }
}
