/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Lukas
 */
public class Booking 
{
    private int id;
    private String kodeKamar;
    private String namaKamar;
    private String kodePelanggan;
    private String nama;
    private String harga;
    private String checkIn;
    private String checkOut;

    public int getId() 
    {
        return id;
    }

    public void setId(int id) 
    {
        this.id = id;
    }

    public String getKodeKamar() 
    {
        return kodeKamar;
    }

    public void setKodeKamar(String kodeKamar) 
    {
        this.kodeKamar = kodeKamar;
    }

    public String getNamaKamar() 
    {
        return namaKamar;
    }

    public void setNamaKamar(String namaKamar) 
    {
        this.namaKamar = namaKamar;
    }

    public String getKodePelanggan() 
    {
        return kodePelanggan;
    }

    public void setKodePelanggan(String kodePelanggan) 
    {
        this.kodePelanggan = kodePelanggan;
    }

    public String getCheckIn() 
    {
        return checkIn;
    }

    public void setCheckIn(String checkIn) 
    {
        this.checkIn = checkIn;
    }

    public String getCheckOut() 
    {
        return checkOut;
    }

    public void setCheckOut(String checkOut) 
    {
        this.checkOut = checkOut;
    }

    public String getNama() 
    {
        return nama;
    }

    public void setNama(String nama) 
    {
        this.nama = nama;
    }

    public String getHarga() 
    {
        return harga;
    }

    public void setHarga(String harga) 
    {
        this.harga = harga;
    }
    
}
