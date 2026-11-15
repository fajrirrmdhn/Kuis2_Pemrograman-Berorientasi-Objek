/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistembengkelotomotif;

/**
 *
 * @author Fajri Ramadhan
 */
import java.io.Serializable;

public class AreaBengkel implements Serializable {
    private static final long serialVersionUID = 1L;

    private String namaArea;
    private int jumlahStall;

    public AreaBengkel(String namaArea, int jumlahStall) {
        this.namaArea = namaArea;
        this.jumlahStall = jumlahStall;
    }

    public void aktifkanArea() {
        System.out.println("-> Fasilitas [" + this.namaArea + "] siap beroperasi (" + this.jumlahStall + " stall servis).");
    }
}