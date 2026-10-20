package com.example.modulul32;
import java.io.Serializable;
    public class DispozitivAudio implements Serializable {
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

        public String getDenumire() {
            return denumire;
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
