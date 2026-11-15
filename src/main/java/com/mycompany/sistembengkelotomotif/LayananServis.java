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

public abstract class LayananServis implements Serializable {
    private static final long serialVersionUID = 1L;

    private String kodeLayanan;
    private String namaLayanan;
    private int kuota;

    public static int totalLayananDibuat = 0;

    public LayananServis(String kodeLayanan, String namaLayanan, int kuota) {
        this.kodeLayanan = kodeLayanan;
        this.namaLayanan = namaLayanan;
        setKuota(kuota);
        totalLayananDibuat++;
    }

    public String getKodeLayanan() {
        return this.kodeLayanan;
    }

    public void setKodeLayanan(String kodeLayanan) {
        this.kodeLayanan = kodeLayanan;
    }

    public String getNamaLayanan() {
        return this.namaLayanan;
    }

    public void setNamaLayanan(String namaLayanan) {
        this.namaLayanan = namaLayanan;
    }

    public int getKuota() {
        return this.kuota;
    }

    public void setKuota(int kuota) {
        if (kuota >= 0) {
            this.kuota = kuota;
        }
    }

    public void kurangiKuota(int jumlah) throws KuotaServisHabisException {
        if (jumlah <= 0) {
            throw new KuotaServisHabisException("Jumlah pesanan harus lebih dari 0.");
        }
        if (this.kuota < jumlah) {
            throw new KuotaServisHabisException(
                "Kuota '" + this.namaLayanan + "' tidak cukup (Sisa: " + this.kuota + ", Diminta: " + jumlah + ")"
            );
        }
        this.kuota -= jumlah;
    }

    public void tampilkanInfo() {
        System.out.printf("[%s] %-22s | Sisa Kuota: %d%n",
                this.kodeLayanan, this.namaLayanan, this.kuota);
    }

    public abstract void prosedurServis();
}