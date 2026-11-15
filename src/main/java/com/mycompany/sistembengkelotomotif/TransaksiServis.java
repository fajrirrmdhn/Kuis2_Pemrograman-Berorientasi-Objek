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

public class TransaksiServis implements Serializable {
    private static final long serialVersionUID = 1L;

    private Pelanggan pelanggan;
    private LayananServis layanan;
    private int jumlah;

    public TransaksiServis(Pelanggan pelanggan, LayananServis layanan, int jumlah) {
        this.pelanggan = pelanggan;
        this.layanan = layanan;
        this.jumlah = jumlah;
    }

    public static TransaksiServis prosesTransaksi(Pelanggan pelanggan, LayananServis layanan, int jumlah)
            throws KuotaServisHabisException {
        layanan.kurangiKuota(jumlah);

        System.out.println("\n[TRANSAKSI BERHASIL]");
        System.out.println("Pelanggan : " + pelanggan.getNama() + " (" + pelanggan.getIdPelanggan() + ")");
        System.out.println("Layanan   : " + layanan.getNamaLayanan() + " [" + layanan.getKodeLayanan() + "] x" + jumlah);

        layanan.prosedurServis();

        if (layanan instanceof PrioritasAntrean) {
            ((PrioritasAntrean) layanan).prosesJalurCepat();
        }
        if (layanan instanceof GaransiServis) {
            ((GaransiServis) layanan).klaimGaransi();
        }

        return new TransaksiServis(pelanggan, layanan, jumlah);
    }

    public Pelanggan getPelanggan() {
        return pelanggan;
    }

    public LayananServis getLayanan() {
        return layanan;
    }

    public int getJumlah() {
        return jumlah;
    }
}
