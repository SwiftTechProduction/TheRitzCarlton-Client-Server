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
public class Pelanggan 
{
    private String kodePelanggan;
    private String nama;
    private String alamat;
    private String noTelepon;
    private String email;
    private String password;

    public String getKodePelanggan() 
    {
        return kodePelanggan;
    }

    public void setKodePelanggan(String kodePelanggan) 
    {
        this.kodePelanggan = kodePelanggan;
    }

    public String getNoTelepon() 
    {
        return noTelepon;
    }

    public void setNoTelepon(String noTelepon) 
    {
        this.noTelepon = noTelepon;
    }

    public String getNama() 
    {
        return nama;
    }

    public void setNama(String nama) 
    {
        this.nama = nama;
    }

    public String getAlamat() 
    {
        return alamat;
    }

    public void setAlamat(String alamat) 
    {
        this.alamat = alamat;
    }

    public String getEmail() 
    {
        return email;
    }

    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getPassword() 
    {
        return password;
    }

    public void setPassword(String password) 
    {
        this.password = password;
    }
    
}
