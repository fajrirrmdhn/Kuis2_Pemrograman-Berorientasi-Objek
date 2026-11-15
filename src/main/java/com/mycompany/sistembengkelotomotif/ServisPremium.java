/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistembengkelotomotif;

/**
 *
 * @author Fajri Ramadhan
 */
public class ServisPremium extends LayananServis implements GaransiServis, PrioritasAntrean {
    private static final long serialVersionUID = 1L;
    private String kepalaMekanik;

    public ServisPremium(String kodeLayanan, String namaLayanan, int kuota, String kepalaMekanik) {
        super(kodeLayanan, namaLayanan, kuota); 
        this.kepalaMekanik = kepalaMekanik;
    }

    public String getKepalaMekanik() {
        return this.kepalaMekanik;
    }

    @Override
    public void tampilkanInfo() {
        System.out.printf("[PREMIUM - %s] %-22s | Mekanik: %-12s | Sisa Kuota: %d%n",
                getKodeLayanan(), getNamaLayanan(), this.kepalaMekanik, getKuota());
    }

    @Override
    public void prosedurServis() {
        System.out.println("Prosedur: Pengecekan menyeluruh dengan diagnostic scanner & sparepart original.");
    }

    @Override
    public void prosesJalurCepat() {
        System.out.println("[VIP FAST TRACK] Kendaraan langsung masuk bay prioritas tanpa antre.");
    }

    @Override
    public void klaimGaransi() {
        System.out.println("[GARANSI] Mendapatkan garansi servis resmi selama 30 hari / 1.000 km.");
    }
}
