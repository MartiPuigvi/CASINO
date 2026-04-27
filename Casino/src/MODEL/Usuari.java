package MODEL;

import java.time.LocalDate;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author nicod
 */
public class Usuari {
    private int id;
    private String nom;
    private String email;
    private String Contrasenya;
    private LocalDate edat;
    private Double Saldo;
    
    public Usuari(int id, String nom, String email, String Contrasenya, LocalDate edat, Double Saldo) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.Contrasenya = Contrasenya;
        this.edat = edat;
        this.Saldo = Saldo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasenya() {
        return Contrasenya;
    }

    public void setContrasenya(String Contrasenya) {
        this.Contrasenya = Contrasenya;
    }

    public LocalDate getEdat() {
        return edat;
    }

    public void setEdat(LocalDate edat) {
        this.edat = edat;
    }

    public Double getSaldo() {
        return Saldo;
    }

    public void setSaldo(Double Saldo) {
        this.Saldo = Saldo;
    }


    @Override
    public String toString() {
        return "Usuari{" + "id=" + id + ", nom=" + nom + ", email=" + email + ", Contrasenya=" + Contrasenya + ", edat=" + edat + ", Saldo=" + Saldo + '}';
    }

   
    
}
