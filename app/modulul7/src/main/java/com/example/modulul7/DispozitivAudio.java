package com.example.modulul7;

import android.os.Parcel;
import android.os.Parcelable;

public class DispozitivAudio implements Parcelable {
    private String denumire;
    private float pret;
    private boolean esteBluetooth;
    private String tipDispozitiv;
    private String marca;
    private float ratingCalitate;
    private boolean areGarantie;

    public DispozitivAudio(String denumire, float pret, boolean esteBluetooth, String tipDispozitiv, String marca, float ratingCalitate, boolean areGarantie) {
        this.denumire = denumire;
        this.pret = pret;
        this.esteBluetooth = esteBluetooth;
        this.tipDispozitiv = tipDispozitiv;
        this.marca = marca;
        this.ratingCalitate = ratingCalitate;
        this.areGarantie = areGarantie;
    }

    protected DispozitivAudio(Parcel in) {
        denumire = in.readString();
        pret = in.readFloat();
        esteBluetooth = in.readByte() != 0;
        tipDispozitiv = in.readString();
        marca = in.readString();
        ratingCalitate = in.readFloat();
        areGarantie = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(denumire);
        dest.writeFloat(pret);
        dest.writeByte((byte) (esteBluetooth ? 1 : 0));
        dest.writeString(tipDispozitiv);
        dest.writeString(marca);
        dest.writeFloat(ratingCalitate);
        dest.writeByte((byte) (areGarantie ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DispozitivAudio> CREATOR = new Creator<DispozitivAudio>() {
        @Override
        public DispozitivAudio createFromParcel(Parcel in) {
            return new DispozitivAudio(in);
        }

        @Override
        public DispozitivAudio[] newArray(int size) {
            return new DispozitivAudio[size];
        }
    };

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public float getPret() {
        return pret;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }

    public boolean isBluetooth() {
        return esteBluetooth;
    }

    public void setEsteBluetooth(boolean esteBluetooth) {
        this.esteBluetooth = esteBluetooth;
    }

    public String getTip() {
        return tipDispozitiv;
    }

    public void setTipDispozitiv(String tipDispozitiv) {
        this.tipDispozitiv = tipDispozitiv;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public float getRating() {
        return ratingCalitate;
    }

    public void setRatingCalitate(float ratingCalitate) {
        this.ratingCalitate = ratingCalitate;
    }

    public boolean isGarantie() {
        return areGarantie;
    }

    public void setAreGarantie(boolean areGarantie) {
        this.areGarantie = areGarantie;
    }

    @Override
    public String toString() {
        return "Dispozitiv Audio: " + denumire +
                "\nPreț: " + pret + " RON" +
                "\nBluetooth: " + (esteBluetooth ? "Da" : "Nu") +
                "\nTip: " + tipDispozitiv +
                "\nMarcă: " + marca +
                "\nRating calitate: " + ratingCalitate + "/5" +
                "\nGaranție: " + (areGarantie ? "Da" : "Nu");
    }
}