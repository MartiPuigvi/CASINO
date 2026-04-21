package MODEL;

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
    private String Contrasenya;
    private Double Saldo;

    public Usuari(int id, String nom, String Contrasenya, Double Saldo) {
        this.id = id;
        this.nom = nom;
        this.Contrasenya = Contrasenya;
        this.Saldo = Saldo;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getContrasenya() {
        return Contrasenya;
    }

    public Double getSaldo() {
        return Saldo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setContrasenya(String Contrasenya) {
        this.Contrasenya = Contrasenya;
    }

    public void setSaldo(Double Saldo) {
        this.Saldo = Saldo;
    }

    @Override
    public String toString() {
        return "Usuari{" + "id=" + id + ", nom=" + nom + ", Contrasenya=" + Contrasenya + ", Saldo=" + Saldo + '}';
    }
    
    
}
