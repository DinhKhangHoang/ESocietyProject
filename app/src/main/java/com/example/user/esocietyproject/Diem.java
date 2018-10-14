package com.example.user.esocietyproject;

import java.io.Serializable;

public class Diem implements Serializable {
    public String ID;
    public String Tittle;
    public String Description;
    public double Lattitude;
    public double Longitude;
    public String AreaID;

    public Diem() {
    }

    public Diem(String ID, String tittle, String description, double lattitude, double longitude, String areaID) {
        this.ID = ID;
        Tittle = tittle;
        Description = description;
        Lattitude = lattitude;
        Longitude = longitude;
        AreaID = areaID;
    }
}
