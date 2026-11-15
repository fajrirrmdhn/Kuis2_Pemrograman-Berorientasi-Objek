/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistembengkelotomotif;

/**
 *
 * @author Fajri Ramadhan
 */
public class ServisEkonomis extends LayananServis {
    private static final long serialVersionUID = 1L;
    private String paketBahan;

    public ServisEkonomis(String kodeLayanan, String namaLayanan, int kuota, String paketBahan) {
        super(kodeLayanan, namaLayanan, kuota);
        this.paketBahan = paketBahan;
    }

    @Override
    public void tampilkanInfo() {
        System.out.printf("[EKONOMIS - %s] %-22s | Paket: %-14s | Sisa Kuota: %d%n",
                getKodeLayanan(), getNamaLayanan(), this.paketBahan, getKuota());
    }

    @Override
    public void prosedurServis() {
        System.out.println("Prosedur: Layanan cepat & hemat di area perawatan umum.");
    }
}