/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistembengkelotomotif;

/**
 *
 * @author Fajri Ramadhan
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class SistemBengkel {
    private String namaBengkel;

    private AreaBengkel areaUtama;

    private ArrayList<LayananServis> daftarLayanan;
    private HashMap<String, Pelanggan> dataPelanggan;
    private ArrayList<TransaksiServis> riwayatTransaksi;

    public SistemBengkel(String namaBengkel) {
        this.namaBengkel = namaBengkel;
        this.areaUtama = new AreaBengkel("Workshop Utama & Ruang Tunggu", 12);
        this.daftarLayanan = new ArrayList<>();
        this.dataPelanggan = new HashMap<>();
        this.riwayatTransaksi = new ArrayList<>();

        System.out.println("INISIALISASI SISTEM: " + this.namaBengkel.toUpperCase() + "");
        this.areaUtama.aktifkanArea();
    }

    public void tambahPelanggan(String id, String nama) {
        Pelanggan pelangganBaru = new Pelanggan(id, nama);
        this.dataPelanggan.put(id, pelangganBaru);
    }

    public void tambahLayananServis(String kode, String nama, int kuota) {
        LayananServis layanan;
        String namaLower = nama.toLowerCase();

        if (namaLower.contains("premium") || kode.equalsIgnoreCase("OBJ-A")) {
            layanan = new ServisPremium(kode, nama, kuota, "Ir. Hendra");
        } else if (namaLower.contains("reguler") || kode.equalsIgnoreCase("OBJ-B")) {
            layanan = new ServisReguler(kode, nama, kuota, 45);
        } else {
            layanan = new ServisEkonomis(kode, nama, kuota, "Shampoo Snow");
        }
        this.daftarLayanan.add(layanan);
    }

    public void tambahLayananServis(LayananServis layanan) {
        this.daftarLayanan.add(layanan);
    }

    public LayananServis cariLayanan(String kode) {
        for (LayananServis layanan : this.daftarLayanan) {
            if (layanan.getKodeLayanan().equalsIgnoreCase(kode)) {
                return layanan;
            }
        }
        return null;
    }

    public ArrayList<LayananServis> cariLayanan(int minimalKuota) {
        ArrayList<LayananServis> hasil = new ArrayList<>();
        for (LayananServis layanan : this.daftarLayanan) {
            if (layanan.getKuota() >= minimalKuota) {
                hasil.add(layanan);
            }
        }
        return hasil;
    }

    public void jalankanTransaksiDariFile(String namaFile) throws Exception {
        File fileInput = new File(namaFile);
        if (!fileInput.exists()) {
            throw new FileNotFoundException("File '" + namaFile + "' tidak ditemukan di direktori proyek!");
        }

        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(fileInput);
            int nomorBaris = 0;

            while (fileScanner.hasNextLine()) {
                String baris = fileScanner.nextLine().trim();
                if (baris.isEmpty()) {
                    continue;
                }
                nomorBaris++;

                try {
                    String[] bagian = baris.split("\\.");
                    if (bagian.length < 3) {
                        throw new IllegalArgumentException("Format baris tidak valid: " + baris);
                    }

                    String idPelanggan = bagian[0].trim().split(">")[1].trim();
                    String kodeLayanan = bagian[1].trim().split(">")[1].trim();
                    int jumlah = Integer.parseInt(bagian[2].trim().split(">")[1].trim());

                    if (!this.dataPelanggan.containsKey(idPelanggan)) {
                        throw new PelangganTidakDitemukanException(
                            "Aktor dengan ID '" + idPelanggan + "' tidak terdaftar di sistem!"
                        );
                    }

                    LayananServis layananDipilih = cariLayanan(kodeLayanan);
                    if (layananDipilih == null) {
                        throw new Exception("Kode layanan '" + kodeLayanan + "' tidak ditemukan!");
                    }

                    Pelanggan pelanggan = this.dataPelanggan.get(idPelanggan);
                    TransaksiServis trx = TransaksiServis.prosesTransaksi(pelanggan, layananDipilih, jumlah);
                    this.riwayatTransaksi.add(trx);

                } catch (PelangganTidakDitemukanException | KuotaServisHabisException e) {
                    System.out.println("\n[TRANSAKSI GAGAL - Baris " + nomorBaris + "] " + e.getMessage());
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("\n[PARSING ERROR - Baris " + nomorBaris + "] Format angka/delimiter salah: " + baris);
                } catch (Exception e) {
                    System.out.println("\n[ERROR - Baris " + nomorBaris + "] " + e.getMessage());
                }
            }
        } finally {
            if (fileScanner != null) {
                fileScanner.close();
            }
            System.out.println("\nPEMBACAAN FILE TRANSAKSI SELESAI");
        }
    }

  
    public void simpanLaporanAkhir() {
        System.out.println("\nMENYIMPAN LAPORAN AKHIR & STATE SISTEM");

        try (FileOutputStream fileOut = new FileOutputStream("sistem_state.ser");
             ObjectOutputStream outStream = new ObjectOutputStream(fileOut)) {

            outStream.writeObject(this.dataPelanggan);
            outStream.writeObject(this.daftarLayanan);
            outStream.writeObject(this.riwayatTransaksi);
            System.out.println("[SERIALIZATION BERHASIL] State objek disimpan ke 'sistem_state.ser'.");
        } catch (IOException e) {
            System.out.println("[SERIALIZATION GAGAL] " + e.getMessage());
        }

        try (FileWriter writer = new FileWriter("laporan.txt")) {
            writer.write("DAFTAR AKTOR AKTIF (" + this.namaBengkel.toUpperCase() + ")\n");

            for (Pelanggan p : this.dataPelanggan.values()) {
                writer.write("ID: " + p.getIdPelanggan() + " | Nama: " + p.getNama() + "\n");
            }

            System.out.println("[LAPORAN TEKS BERHASIL] Daftar Aktor Aktif ditulis ke 'laporan.txt'.");
        } catch (IOException e) {
            System.out.println("[LAPORAN TEKS GAGAL] " + e.getMessage());
        }
    }
}
