/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONEXION;

import static CONTROLER.Casino.Users;
import static CONTROLER.Casino.password;
import static CONTROLER.Casino.url;
import static CONTROLER.Casino.user;
import MODEL.Usuari;
import java.sql.Connection;
import java.sql.DriverManager;
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
        String sql = "SELECT id, username, password, email, age, saldo, admin FROM user";

        try (
                Connection conn = DriverManager.getConnection(url, user, password); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String nom = rs.getString("username");
                String password = rs.getString("password");
                int id = rs.getInt("id");
                String email = rs.getString("email");
                double saldo = rs.getDouble("saldo");
                boolean admin = rs.getBoolean("admin");
                LocalDate age = rs.getDate("age").toLocalDate();

                Users.add(new Usuari(id, nom, email, password, age, saldo, admin));
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
                Users.add(new Usuari(idGenerat,username,email,pass,java.time.LocalDate.parse(age),500.0,false
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error SQL al registre: " + e.getMessage());
        }
    }

}
