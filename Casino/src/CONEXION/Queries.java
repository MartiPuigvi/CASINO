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

                Users.add(new Usuari(id, nom, password, email, age, saldo, admin));          
 //System.out.println(id + " - " + nom + " - ");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
