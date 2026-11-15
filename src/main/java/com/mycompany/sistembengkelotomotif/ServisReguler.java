/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistembengkelotomotif;

/**
 *
 * @author Fajri Ramadhan
 */
public class ServisReguler extends LayananServis implements GaransiServis {
    private static final long serialVersionUID = 1L;
    private int estimasiMenit;

    public ServisReguler(String kodeLayanan, String namaLayanan, int kuota, int estimasiMenit) {
        super(kodeLayanan, namaLayanan, kuota);
        this.estimasiMenit = estimasiMenit;
    }

    @Override
    public void tampilkanInfo() {
        System.out.printf("[REGULER - %s] %-22s | Estimasi: %d mnt      | Sisa Kuota: %d%n",
                getKodeLayanan(), getNamaLayanan(), this.estimasiMenit, getKuota());
    }

    @Override
    public void prosedurServis() {
        System.out.println("Prosedur: Pengerjaan standar berkala sesuai nomor urut antrean bengkel.");
    }

    @Override
    public void klaimGaransi() {
        System.out.println("[GARANSI] Mendapatkan garansi pengerjaan ulang selama 7 hari.");
    }
}
