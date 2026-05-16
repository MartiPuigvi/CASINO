/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLER;

/**
 *
 * @author nicod
 */
import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;

public class estils {

    public static void border(JPanel panel) {
        panel.setOpaque(false);
        panel.setBorder(null);

        panel.setBorder(new AbstractBorder() {
            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(Color.BLACK);
                g2.fillRoundRect(x, y, width, height, 40, 40);

                g2.setColor(new Color(212, 175, 55));
                g2.setStroke(new BasicStroke(3));
                g2.drawRoundRect(x + 1, y + 1, width - 2, height - 2, 40, 40);

                g2.dispose();
            }
        });
    }
    


    public static void estilbuto(JButton button) {
        button.putClientProperty("FlatLaf.style", """
            arc:999;
            background: #0F0F0F;
            foreground: #D4AF37;

            borderWidth: 2;
            borderColor: #D4AF37;

            hoverBackground: #191919;
            pressedBackground: #2D2D2D;

            hoverBorderColor: #D4AF37;
            pressedBorderColor: #D4AF37;
        """);

        button.setFocusPainted(false);
    }

    public static void borderTp(JPanel panel) {
        panel.setOpaque(false);
        panel.setBorder(null);

        panel.setBorder(new AbstractBorder() {
            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(new Color(25, 0, 35));
                g2.fillRoundRect(x, y, width, height, 40, 40);

                g2.setColor(new Color(212, 175, 55));
                g2.setStroke(new BasicStroke(3));
                g2.drawRoundRect(x + 1, y + 1, width - 2, height - 2, 40, 40);

                g2.dispose();
            }
        });
    }

    public static void estilbutoRule(JButton button) {
        button.putClientProperty("FlatLaf.style", """
            arc:999;
            background: #072807;
            foreground: #D4AF37;
            borderWidth: 2;
            borderColor: #D4AF37;
            hoverBackground: #191919;
            pressedBackground: #2D2D2D;
            hoverBorderColor: #D4AF37;
            pressedBorderColor: #D4AF37;
        """);

        button.setFocusPainted(false);
    }

    public static void estilbutoRule2(JButton button) {
        button.putClientProperty("FlatLaf.style", """
            arc:999;
            background: #007b00;
            foreground: #D4AF37;
            borderWidth: 2;
            borderColor: #D4AF37;
            hoverBackground: #191919;
            pressedBackground: #2D2D2D;
            hoverBorderColor: #D4AF37;
            pressedBorderColor: #D4AF37;
        """);

        button.setFocusPainted(false);
    }

    public static void borderRule(JPanel panel) {
        // Configuraciones básicas
        panel.setOpaque(false);
        panel.setBorder(null);

        panel.setBorder(new AbstractBorder() {
            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(new Color(7, 40, 7));
                g2.fillRoundRect(x, y, width, height, 40, 40);

                g2.setColor(new Color(212, 175, 55));
                g2.setStroke(new BasicStroke(3));
                g2.drawRoundRect(x + 1, y + 1, width - 2, height - 2, 40, 40);

                g2.dispose();
            }
        });
    }

}
