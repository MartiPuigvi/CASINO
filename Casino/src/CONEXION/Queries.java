/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONEXION;

import static CONTROLER.Casino.Admins;
import static CONTROLER.Casino.Users;
import static CONTROLER.Casino.password;
import static CONTROLER.Casino.url;
import static CONTROLER.Casino.user;
import MODEL.Admin;
import MODEL.Usuari;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

/**
 *
 * @author nicod
 */
public class Queries {

    public static void usuariAaraylist() {
        String sql = "SELECT id, username, password, email, age, saldo FROM user";

        try (
                Connection conn = DriverManager.getConnection(url, user, password); 
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String nom = rs.getString("username");
                String password = rs.getString("password");
                int id = rs.getInt("id");
                String email = rs.getString("email");
                double saldo = rs.getDouble("saldo");
                LocalDate age = rs.getDate("age").toLocalDate();

                Users.add(new Usuari(id, nom, email, password, age, saldo));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void adminAarraylist() {
        String sql = "SELECT id, username, password FROM admin";

        try (
                Connection conn = DriverManager.getConnection(url, user, password); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String nom = rs.getString("username");
                String password = rs.getString("password");
                int id = rs.getInt("id");

                Admins.add(new Admin(id, nom, password));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void adminAarrayList() {
        String sql = "SELECT id, username, password FROM admin";

        try (
                Connection conn = DriverManager.getConnection(url, user, password); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("username");
                String password = rs.getString("password");

                Admins.add(new Admin(id, nom, password));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void signUp(String username, String pass, String email, String age) {
        String sql = "INSERT INTO user (username, password, email, age) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password); 
                java.sql.PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, username);
            ps.setString(2, pass);
            ps.setString(3, email);
            ps.setDate(4, java.sql.Date.valueOf(age));

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int idGenerat = rs.getInt(1);
                Users.add(new Usuari(idGenerat, username, email, pass, java.time.LocalDate.parse(age), 500.0
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error SQL al registre: " + e.getMessage());
        }
    }

    public static void addUser(String username, String pass, String email, java.time.LocalDate age, Double saldo) {
        String sql = "INSERT INTO user (username, password, email, age, saldo) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
                java.sql.PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, username);
            ps.setString(2, pass);
            ps.setString(3, email);
            ps.setDate(4, java.sql.Date.valueOf(age));
            ps.setDouble(5, saldo);

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int idGenerat = rs.getInt(1);
                Users.add(new Usuari(idGenerat, username, email, pass, age, saldo));
            }

        } catch (SQLException e) {
            System.out.println("Error SQL al registre: " + e.getMessage());
        }
    }

    public static void modUser(int id, String nouNom, String nouEmail, String novaPass, java.time.LocalDate novaEdat, double nouSaldo) {
        String sql = "UPDATE user SET username = ?, email = ?, password = ?, age = ?, saldo = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password); 
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nouNom);
            ps.setString(2, nouEmail);
            ps.setString(3, novaPass);
            ps.setDate(4, java.sql.Date.valueOf(novaEdat));
            ps.setDouble(5, nouSaldo);
            ps.setInt(6, id);

            int filesAfectades = ps.executeUpdate();

            if (filesAfectades > 0) {
                System.out.println("Usuari actualitzat correctament a la base de dades.");
            }

        } catch (java.sql.SQLIntegrityConstraintViolationException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Error: L'email o el nom d'usuari ja existeixen!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void delUser(int id) {
        String sql = "DELETE FROM user WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int filesAfectades = ps.executeUpdate();

            if (filesAfectades > 0) {
                javax.swing.JOptionPane.showMessageDialog(null, "Usuari eliminat correctament.");
            } else {
                javax.swing.JOptionPane.showMessageDialog(null, "No s'ha trobat cap usuari amb aquest ID.");
            }

        } catch (SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "No es pot eliminar: l'usuari té dades vinculades al sistema.");
            e.printStackTrace();
        }
    }

    public static void updateSaldo(int id, double nouSaldo) {
        String sql = "UPDATE user SET saldo = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, nouSaldo);
            ps.setInt(2, id);

            int filesAfectades = ps.executeUpdate();

            if (filesAfectades > 0) {
                System.out.println("Saldo actualitzat a la base de dades correctament.");
            }

        } catch (SQLException e) {
            System.out.println("Error al actualitzar el saldo: " + e.getMessage());
        }
    }
}
