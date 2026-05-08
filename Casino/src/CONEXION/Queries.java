/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONEXION;

import static CONEXION.Queries.historialRuleta;
import static CONTROLER.Casino.Admins;
import static CONTROLER.Casino.Users;
import static CONTROLER.Casino.historialBlackjack;
import static CONTROLER.Casino.historialRuleta;
import static CONTROLER.Casino.historialTragaperras;
import static CONTROLER.Casino.password;
import static CONTROLER.Casino.url;
import static CONTROLER.Casino.user;
import MODEL.Admin;
import MODEL.Usuari;
import MODEL.partidaBlackjack;
import MODEL.partidaRuleta;
import MODEL.partidaRuleta.colorRuleta;
import MODEL.partidaTragaperras;
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
                Connection conn = DriverManager.getConnection(url, user, password); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

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

        try (Connection conn = DriverManager.getConnection(url, user, password); java.sql.PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

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

        try (Connection conn = DriverManager.getConnection(url, user, password); java.sql.PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

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

        try (Connection conn = DriverManager.getConnection(url, user, password); PreparedStatement ps = conn.prepareStatement(sql)) {

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
            javax.swing.JOptionPane.showMessageDialog(null, "No es pot eliminar: l'usuari t� dades vinculades al sistema.");
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

    public static void guardarPartidaBlackjack(String nomJugador, int totalJugador, int totalDealer, boolean guanyador) {
        String sql = "INSERT INTO BLACKJACK (nomJugador, totalJugador, totalDealer, guanyador) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nomJugador);
            ps.setInt(2, totalJugador);
            ps.setInt(3, totalDealer);
            ps.setBoolean(4, guanyador);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void guardarPartidaRuleta(String nomJugador, int numero, colorRuleta color, boolean esParell, String guanyador) {
        String sql = "INSERT INTO RULETA (nomJugador, numero, color, esParell, guanyador) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nomJugador);
            ps.setInt(2, numero);
            ps.setString(3, color.name());
            ps.setBoolean(4, esParell);
            ps.setString(5, guanyador);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void guardarPartidaTragaperras(String nomJugador, String simbol1, String simbol2, String simbol3, String guanyador) {
        String sql = "INSERT INTO TRAGAPERRAS (nomJugador, simbol1, simbol2, simbol3, guanyador) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nomJugador);
            ps.setString(2, simbol1);
            ps.setString(3, simbol2);
            ps.setString(4, simbol3);
            ps.setString(5, guanyador);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void historialBlackjack() {
        String sql = "SELECT idPartida, nomJugador, totalJugador, totalDealer, guanyador FROM BLACKJACK";

        try (
                Connection conn = DriverManager.getConnection(url, user, password); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int idPartida = rs.getInt("idPartida");
                String nomJugador = rs.getString("nomJugador");
                int totalJugador = rs.getInt("totalJugador");
                int totalDealer = rs.getInt("totalDealer");
                boolean guanyador = rs.getBoolean("guanyador");

                historialBlackjack.add(new partidaBlackjack(idPartida, nomJugador, totalJugador, totalDealer, guanyador));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void historialRuleta() {
        String sql = "SELECT idPartida, nomJugador, numero, color, esParell, guanyador FROM RULETA";

        try (Connection conn = DriverManager.getConnection(url, user, password); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            historialRuleta.clear();

            while (rs.next()) {
                int id = rs.getInt("idPartida");
                String nom = rs.getString("nomJugador");
                int num = rs.getInt("numero");

                String colorText = rs.getString("color");
                colorRuleta colorEnum = colorRuleta.valueOf(colorText);

                boolean parell = rs.getBoolean("esParell");

                String guanyaText = rs.getString("guanyador");

                historialRuleta.add(new partidaRuleta(id, nom, num, colorEnum, parell, guanyaText));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void historialTragaperras() {
        String sql = "SELECT idPartida, nomJugador, simbol1, simbol2, simbol3, guanyador FROM TRAGAPERRAS";

        try (Connection conn = DriverManager.getConnection(url, user, password); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            historialTragaperras.clear();

            while (rs.next()) {
                int id = rs.getInt("idPartida");
                String nom = rs.getString("nomJugador");

                String simbol1 = rs.getString("simbol1");
                String simbol2 = rs.getString("simbol2");
                String simbol3 = rs.getString("simbol3");

                String guanyador = rs.getString("guanyador");
                historialTragaperras.add(new partidaTragaperras(id, nom, simbol1, simbol2, simbol3, guanyador));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
