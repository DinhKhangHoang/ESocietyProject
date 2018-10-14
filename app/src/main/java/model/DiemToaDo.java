package model;

import java.io.Serializable;

public class DiemToaDo implements Serializable {
    String ID;
    String Tittle;
    String Description;
    double Lattitude;
    double Longitude;
    String AreaID;
    public String getAreaID() {
        return AreaID;
    }

    public void setAreaID(String areaID) {
        AreaID = areaID;
    }



    public DiemToaDo(String ID, String tittle, String description, double lattitude, double longitude, String areaID) {
        this.ID = ID;
        Tittle = tittle;
        Description = description;
        Lattitude = lattitude;
        Longitude = longitude;
        AreaID = areaID;
    }





    public DiemToaDo() {
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTittle() {
        return Tittle;
    }

    public void setTittle(String tittle) {
        Tittle = tittle;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public double getLattitude() {
        return Lattitude;
    }

    public void setLattitude(double lattitude) {
        Lattitude = lattitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

}
