/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package VIEW;

import static CONTROLER.Casino.userActual;
import static CONEXION.Queries.guardarPartidaBlackjack;
import static CONEXION.Queries.guardarPartidaRuleta;
import static CONTROLER.GestioFitxers.tornarMenuClient;
import CONTROLER.GestioLog;
import static CONTROLER.GestioLog.escriureLog;
import CONTROLER.estils;
import static CONTROLER.estils.borderRule;
import static CONTROLER.estils.estilbutoRule;
import static CONTROLER.estils.estilbutoRule2;
import MODEL.ApostaRealitzada;
import MODEL.casellaRuleta;
import MODEL.jocRuleta;
import MODEL.partidaRuleta.colorRuleta;
import java.awt.Button;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author nicod
 */
public class frmRuleta extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(frmRuleta.class.getName());
    private ArrayList<casellaRuleta> tauler = new ArrayList<>();
    private Random random = new Random();
    private ArrayList<ApostaRealitzada> apostesActives = new ArrayList<>();
    private JButton[] btmNums;

    /**
     * Creates new form frmRuleta
     */
    public frmRuleta() {
        this.setUndecorated(true);

        this.setContentPane(Fondo);
        Fondo.setLayout(null);
        initComponents();
        this.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        estilbutoRule(btmMenu);
        borderRule(jPanel3);
        estilbutoRule2(btmGirar);

        btmNums = new JButton[]{
            btmNum1, btmNum2, btmNum3, btmNum4, btmNum5, btmNum6, btmNum7, btmNum8, btmNum9, btmNum10,
            btmNum11, btmNum12, btmNum13, btmNum14, btmNum15, btmNum16, btmNum17, btmNum18, btmNum19, btmNum20,
            btmNum21, btmNum22, btmNum23, btmNum24, btmNum25, btmNum26, btmNum27, btmNum28, btmNum29, btmNum30,
            btmNum31, btmNum32, btmNum33, btmNum34, btmNum35, btmNum36, btmNum37, btmNum38, btmNum39, btmNum40,
            btmNum41, btmNum42, btmNum43, btmNum44, btmNum45, btmNum46, btmNum47, btmNum48, btmNum49, btmNum50,
            btmNum51, btmNum52, btmNum53, btmNum54, btmNum55, btmNum56, btmNum57, btmNum58, btmNum59, btmNum60,
            btmNum61, btmNum62, btmNum63, btmNum64, btmNum65, btmNum66, btmNum67, btmNum68, btmNum69, btmNum70,
            btmNum71, btmNum72, btmNum73, btmSenar, btmParell, btmNegre, btmVermell, btmSenar1, btmParell1, btmNegre1,
            btmVermell1, btmNum0
        };

        for (int i = 0; i < btmNums.length; i++) {
            botoInvi(btmNums[i]);
        }

        prepararTauler();
        saldo();
        GestioLog.escriureLog(userActual + " esta jugant a la Ruleta");
    }

    public void saldo() {
        lblSaldo1.setText("SALDO: " + userActual.getSaldo());
    }

    private void mostrarPopUpAposta(String tipus, Object valor) {
        String[] fitxes = {"20", "50", "100", "200", "500"};
        String tria = (String) javax.swing.JOptionPane.showInputDialog(
                this,
                "Quant apostes a " + (tipus.equals("PARELL_SENAR") ? ((boolean) valor ? "Parell" : "Senar") : valor) + "?",
                "Selecciona la teva fitxa",
                javax.swing.JOptionPane.QUESTION_MESSAGE,
                null,
                fitxes,
                fitxes[0]);

        if (tria != null) {
            double diners = Double.parseDouble(tria);

            if (userActual.getSaldo() >= diners) {
                userActual.setSaldo(userActual.getSaldo() - diners);
                CONEXION.Queries.updateSaldo(userActual.getId(), userActual.getSaldo());
                saldo();

                apostesActives.add(new ApostaRealitzada(tipus, valor, diners));

                String valorText = valor.toString();
                if (tipus.equals("PARELL_SENAR")) {
                    valorText = (boolean) valor ? "Parell" : "Senar";
                }

                escriureLog(userActual.getNom() + " ha apostat " + diners + "€ a: " + valorText);

                txtLlistaApostes.append("APOSTA: " + diners + "€ al " + valorText + "\n");
            } else {

                javax.swing.JOptionPane.showMessageDialog(this, "No tens prou saldo per fer aquesta aposta!");
            }
        }
    }

    private void botoInvi(JButton boto) {
        boto.setContentAreaFilled(false);
        boto.setBorderPainted(false);
        boto.setFocusPainted(false);
        boto.setOpaque(false);
        boto.setText("");
    }

    private void fitxaNum(JButton label) {
        ImageIcon icon = new ImageIcon(getClass().getResource("/Images/Aposta.png"));
        label.setIcon(icon);

    }

    private void prepararTauler() {
        int[] vermells = {1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36};

        for (int i = 0; i <= 36; i++) {
            String color = "Negre";

            if (i == 0) {
                color = "Verd";
            } else {
                for (int j = 0; j < vermells.length; j++) {
                    if (vermells[j] == i) {
                        color = "Vermell";
                        break;
                    }
                }
            }

            casellaRuleta novaCasella = new casellaRuleta(i, color, false);
            tauler.add(novaCasella);
        }

    }
    JPanel Fondo = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            ImageIcon img = new ImageIcon(getClass().getResource("/Images/fon3.png"));
            g.drawImage(img.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    };

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btmNum1 = new javax.swing.JButton();
        btmNum2 = new javax.swing.JButton();
        btmNum3 = new javax.swing.JButton();
        btmNum4 = new javax.swing.JButton();
        btmNum5 = new javax.swing.JButton();
        btmNum6 = new javax.swing.JButton();
        btmNum7 = new javax.swing.JButton();
        btmNum8 = new javax.swing.JButton();
        btmNum9 = new javax.swing.JButton();
        btmNum10 = new javax.swing.JButton();
        btmNum11 = new javax.swing.JButton();
        btmNum12 = new javax.swing.JButton();
        btmNum13 = new javax.swing.JButton();
        btmNum14 = new javax.swing.JButton();
        btmNum15 = new javax.swing.JButton();
        btmNum16 = new javax.swing.JButton();
        btmNum17 = new javax.swing.JButton();
        btmNum18 = new javax.swing.JButton();
        btmNum0 = new javax.swing.JButton();
        btmNum19 = new javax.swing.JButton();
        btmNum20 = new javax.swing.JButton();
        btmNum21 = new javax.swing.JButton();
        btmNum22 = new javax.swing.JButton();
        btmNum23 = new javax.swing.JButton();
        btmNum24 = new javax.swing.JButton();
        btmNum25 = new javax.swing.JButton();
        btmNum26 = new javax.swing.JButton();
        btmNum27 = new javax.swing.JButton();
        btmNum33 = new javax.swing.JButton();
        btmNum28 = new javax.swing.JButton();
        btmNum29 = new javax.swing.JButton();
        btmNum30 = new javax.swing.JButton();
        btmNum31 = new javax.swing.JButton();
        btmNum32 = new javax.swing.JButton();
        btmNum34 = new javax.swing.JButton();
        btmNum35 = new javax.swing.JButton();
        btmNum36 = new javax.swing.JButton();
        btmParell = new javax.swing.JButton();
        btmSenar = new javax.swing.JButton();
        btmNegre = new javax.swing.JButton();
        btmVermell = new javax.swing.JButton();
        btmGirar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtLlistaApostes = new javax.swing.JTextArea();
        btmMenu = new javax.swing.JButton();
        lblrule = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblSaldo1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lblSaldo3 = new javax.swing.JLabel();
        btmNum37 = new javax.swing.JButton();
        btmNum38 = new javax.swing.JButton();
        btmNum39 = new javax.swing.JButton();
        btmNum40 = new javax.swing.JButton();
        btmNum41 = new javax.swing.JButton();
        btmNum42 = new javax.swing.JButton();
        btmNum43 = new javax.swing.JButton();
        btmNum44 = new javax.swing.JButton();
        btmNum45 = new javax.swing.JButton();
        btmNum46 = new javax.swing.JButton();
        btmNum47 = new javax.swing.JButton();
        btmNum48 = new javax.swing.JButton();
        btmNum49 = new javax.swing.JButton();
        btmNum50 = new javax.swing.JButton();
        btmNum51 = new javax.swing.JButton();
        btmNum52 = new javax.swing.JButton();
        btmNum53 = new javax.swing.JButton();
        btmNum54 = new javax.swing.JButton();
        btmNum55 = new javax.swing.JButton();
        btmNum56 = new javax.swing.JButton();
        btmNum57 = new javax.swing.JButton();
        btmNum58 = new javax.swing.JButton();
        btmNum59 = new javax.swing.JButton();
        btmNum60 = new javax.swing.JButton();
        btmNum61 = new javax.swing.JButton();
        btmNum62 = new javax.swing.JButton();
        btmNum63 = new javax.swing.JButton();
        btmNum64 = new javax.swing.JButton();
        btmNum65 = new javax.swing.JButton();
        btmNum66 = new javax.swing.JButton();
        btmNum67 = new javax.swing.JButton();
        btmNum68 = new javax.swing.JButton();
        btmNum69 = new javax.swing.JButton();
        btmNum70 = new javax.swing.JButton();
        btmNum71 = new javax.swing.JButton();
        btmNum72 = new javax.swing.JButton();
        btmNum73 = new javax.swing.JButton();
        btmParell1 = new javax.swing.JButton();
        btmSenar1 = new javax.swing.JButton();
        btmNegre1 = new javax.swing.JButton();
        btmVermell1 = new javax.swing.JButton();
        lblrule1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btmNum1.setBackground(new java.awt.Color(255, 0, 0));
        btmNum1.setForeground(new java.awt.Color(255, 255, 255));
        btmNum1.setText("1");
        btmNum1.setBorder(null);
        btmNum1.setBorderPainted(false);
        btmNum1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum1ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 605, 113, 87));

        btmNum2.setBackground(new java.awt.Color(0, 0, 0));
        btmNum2.setForeground(new java.awt.Color(255, 255, 255));
        btmNum2.setText("2");
        btmNum2.setBorder(null);
        btmNum2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum2ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 515, 113, 87));

        btmNum3.setBackground(new java.awt.Color(255, 0, 0));
        btmNum3.setForeground(new java.awt.Color(255, 255, 255));
        btmNum3.setText("3");
        btmNum3.setBorder(null);
        btmNum3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum3ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 423, 113, 89));

        btmNum4.setBackground(new java.awt.Color(0, 0, 0));
        btmNum4.setForeground(new java.awt.Color(255, 255, 255));
        btmNum4.setText("4");
        btmNum4.setBorder(null);
        btmNum4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum4ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum4, new org.netbeans.lib.awtextra.AbsoluteConstraints(255, 605, 113, 87));

        btmNum5.setBackground(new java.awt.Color(255, 0, 0));
        btmNum5.setForeground(new java.awt.Color(255, 255, 255));
        btmNum5.setText("5");
        btmNum5.setBorder(null);
        btmNum5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum5ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum5, new org.netbeans.lib.awtextra.AbsoluteConstraints(255, 515, 113, 87));

        btmNum6.setBackground(new java.awt.Color(0, 0, 0));
        btmNum6.setForeground(new java.awt.Color(255, 255, 255));
        btmNum6.setText("6");
        btmNum6.setBorder(null);
        btmNum6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum6ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum6, new org.netbeans.lib.awtextra.AbsoluteConstraints(255, 423, 113, 88));

        btmNum7.setBackground(new java.awt.Color(255, 0, 0));
        btmNum7.setForeground(new java.awt.Color(255, 255, 255));
        btmNum7.setText("7");
        btmNum7.setBorder(null);
        btmNum7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum7ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum7, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 605, 113, 87));

        btmNum8.setBackground(new java.awt.Color(0, 0, 0));
        btmNum8.setForeground(new java.awt.Color(255, 255, 255));
        btmNum8.setText("8");
        btmNum8.setBorder(null);
        btmNum8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum8ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum8, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 515, 113, 87));

        btmNum9.setBackground(new java.awt.Color(255, 0, 0));
        btmNum9.setForeground(new java.awt.Color(255, 255, 255));
        btmNum9.setText("9");
        btmNum9.setBorder(null);
        btmNum9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum9ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum9, new org.netbeans.lib.awtextra.AbsoluteConstraints(371, 423, 113, 88));

        btmNum10.setBackground(new java.awt.Color(0, 0, 0));
        btmNum10.setForeground(new java.awt.Color(255, 255, 255));
        btmNum10.setText("10");
        btmNum10.setBorder(null);
        btmNum10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum10ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum10, new org.netbeans.lib.awtextra.AbsoluteConstraints(489, 605, 111, 87));

        btmNum11.setBackground(new java.awt.Color(0, 0, 0));
        btmNum11.setForeground(new java.awt.Color(255, 255, 255));
        btmNum11.setText("11");
        btmNum11.setBorder(null);
        btmNum11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum11ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum11, new org.netbeans.lib.awtextra.AbsoluteConstraints(489, 515, 113, 87));

        btmNum12.setBackground(new java.awt.Color(255, 0, 0));
        btmNum12.setForeground(new java.awt.Color(255, 255, 255));
        btmNum12.setText("12");
        btmNum12.setBorder(null);
        btmNum12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum12ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum12, new org.netbeans.lib.awtextra.AbsoluteConstraints(489, 423, 113, 88));

        btmNum13.setBackground(new java.awt.Color(0, 0, 0));
        btmNum13.setForeground(new java.awt.Color(255, 255, 255));
        btmNum13.setText("13");
        btmNum13.setBorder(null);
        btmNum13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum13ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum13, new org.netbeans.lib.awtextra.AbsoluteConstraints(606, 605, 109, 87));

        btmNum14.setBackground(new java.awt.Color(255, 0, 0));
        btmNum14.setForeground(new java.awt.Color(255, 255, 255));
        btmNum14.setText("14");
        btmNum14.setBorder(null);
        btmNum14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum14ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum14, new org.netbeans.lib.awtextra.AbsoluteConstraints(606, 515, 109, 87));

        btmNum15.setBackground(new java.awt.Color(0, 0, 0));
        btmNum15.setForeground(new java.awt.Color(255, 255, 255));
        btmNum15.setText("15");
        btmNum15.setBorder(null);
        btmNum15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum15ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum15, new org.netbeans.lib.awtextra.AbsoluteConstraints(606, 423, 109, 88));

        btmNum16.setBackground(new java.awt.Color(255, 0, 0));
        btmNum16.setForeground(new java.awt.Color(255, 255, 255));
        btmNum16.setText("16");
        btmNum16.setBorder(null);
        btmNum16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum16ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum16, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 605, 110, 87));

        btmNum17.setBackground(new java.awt.Color(0, 0, 0));
        btmNum17.setForeground(new java.awt.Color(255, 255, 255));
        btmNum17.setText("17");
        btmNum17.setBorder(null);
        btmNum17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum17ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum17, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 515, 110, 87));

        btmNum18.setBackground(new java.awt.Color(255, 0, 0));
        btmNum18.setForeground(new java.awt.Color(255, 255, 255));
        btmNum18.setText("18");
        btmNum18.setBorder(null);
        btmNum18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum18ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum18, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 423, 110, 88));

        btmNum0.setBackground(new java.awt.Color(0, 255, 51));
        btmNum0.setText("0");
        btmNum0.setBorder(null);
        btmNum0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum0ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum0, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 423, 110, 270));

        btmNum19.setBackground(new java.awt.Color(255, 0, 0));
        btmNum19.setForeground(new java.awt.Color(255, 255, 255));
        btmNum19.setText("19");
        btmNum19.setBorder(null);
        btmNum19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum19ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum19, new org.netbeans.lib.awtextra.AbsoluteConstraints(833, 605, 110, 87));

        btmNum20.setBackground(new java.awt.Color(0, 0, 0));
        btmNum20.setForeground(new java.awt.Color(255, 255, 255));
        btmNum20.setText("20");
        btmNum20.setBorder(null);
        btmNum20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum20ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum20, new org.netbeans.lib.awtextra.AbsoluteConstraints(833, 515, 110, 87));

        btmNum21.setBackground(new java.awt.Color(255, 0, 0));
        btmNum21.setForeground(new java.awt.Color(255, 255, 255));
        btmNum21.setText("21");
        btmNum21.setToolTipText("");
        btmNum21.setBorder(null);
        btmNum21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum21ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum21, new org.netbeans.lib.awtextra.AbsoluteConstraints(833, 423, 110, 88));

        btmNum22.setBackground(new java.awt.Color(0, 0, 0));
        btmNum22.setForeground(new java.awt.Color(255, 255, 255));
        btmNum22.setText("22");
        btmNum22.setBorder(null);
        btmNum22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum22ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum22, new org.netbeans.lib.awtextra.AbsoluteConstraints(945, 605, 113, 87));

        btmNum23.setBackground(new java.awt.Color(255, 0, 0));
        btmNum23.setForeground(new java.awt.Color(255, 255, 255));
        btmNum23.setText("23");
        btmNum23.setBorder(null);
        btmNum23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum23ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum23, new org.netbeans.lib.awtextra.AbsoluteConstraints(945, 515, 113, 87));

        btmNum24.setBackground(new java.awt.Color(0, 0, 0));
        btmNum24.setForeground(new java.awt.Color(255, 255, 255));
        btmNum24.setText("24");
        btmNum24.setBorder(null);
        btmNum24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum24ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum24, new org.netbeans.lib.awtextra.AbsoluteConstraints(945, 423, 113, 88));

        btmNum25.setBackground(new java.awt.Color(255, 0, 0));
        btmNum25.setForeground(new java.awt.Color(255, 255, 255));
        btmNum25.setText("25");
        btmNum25.setBorder(null);
        btmNum25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum25ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum25, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 605, 110, 87));

        btmNum26.setBackground(new java.awt.Color(0, 0, 0));
        btmNum26.setForeground(new java.awt.Color(255, 255, 255));
        btmNum26.setText("26");
        btmNum26.setBorder(null);
        btmNum26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum26ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum26, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 515, 110, 87));

        btmNum27.setBackground(new java.awt.Color(255, 0, 0));
        btmNum27.setForeground(new java.awt.Color(255, 255, 255));
        btmNum27.setText("27");
        btmNum27.setBorder(null);
        btmNum27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum27ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum27, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 423, 110, 88));

        btmNum33.setBackground(new java.awt.Color(0, 0, 0));
        btmNum33.setForeground(new java.awt.Color(255, 255, 255));
        btmNum33.setText("33");
        btmNum33.setBorder(null);
        btmNum33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum33ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum33, new org.netbeans.lib.awtextra.AbsoluteConstraints(1288, 423, 107, 88));

        btmNum28.setBackground(new java.awt.Color(0, 0, 0));
        btmNum28.setForeground(new java.awt.Color(255, 255, 255));
        btmNum28.setText("28");
        btmNum28.setBorder(null);
        btmNum28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum28ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum28, new org.netbeans.lib.awtextra.AbsoluteConstraints(1175, 605, 110, 87));

        btmNum29.setBackground(new java.awt.Color(0, 0, 0));
        btmNum29.setForeground(new java.awt.Color(255, 255, 255));
        btmNum29.setText("29");
        btmNum29.setBorder(null);
        btmNum29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum29ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum29, new org.netbeans.lib.awtextra.AbsoluteConstraints(1175, 515, 110, 87));

        btmNum30.setBackground(new java.awt.Color(255, 0, 0));
        btmNum30.setForeground(new java.awt.Color(255, 255, 255));
        btmNum30.setText("30");
        btmNum30.setBorder(null);
        btmNum30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum30ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum30, new org.netbeans.lib.awtextra.AbsoluteConstraints(1175, 423, 110, 88));

        btmNum31.setBackground(new java.awt.Color(0, 0, 0));
        btmNum31.setForeground(new java.awt.Color(255, 255, 255));
        btmNum31.setText("31");
        btmNum31.setBorder(null);
        btmNum31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum31ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum31, new org.netbeans.lib.awtextra.AbsoluteConstraints(1288, 605, 107, 87));

        btmNum32.setBackground(new java.awt.Color(255, 0, 0));
        btmNum32.setForeground(new java.awt.Color(255, 255, 255));
        btmNum32.setText("32");
        btmNum32.setBorder(null);
        btmNum32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum32ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum32, new org.netbeans.lib.awtextra.AbsoluteConstraints(1288, 515, 107, 87));

        btmNum34.setBackground(new java.awt.Color(255, 0, 0));
        btmNum34.setForeground(new java.awt.Color(255, 255, 255));
        btmNum34.setText("34");
        btmNum34.setBorder(null);
        btmNum34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum34ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum34, new org.netbeans.lib.awtextra.AbsoluteConstraints(1398, 605, 110, 87));

        btmNum35.setBackground(new java.awt.Color(0, 0, 0));
        btmNum35.setForeground(new java.awt.Color(255, 255, 255));
        btmNum35.setText("35");
        btmNum35.setBorder(null);
        btmNum35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum35ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum35, new org.netbeans.lib.awtextra.AbsoluteConstraints(1398, 515, 110, 87));

        btmNum36.setBackground(new java.awt.Color(255, 0, 0));
        btmNum36.setForeground(new java.awt.Color(255, 255, 255));
        btmNum36.setText("36");
        btmNum36.setBorder(null);
        btmNum36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum36ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum36, new org.netbeans.lib.awtextra.AbsoluteConstraints(1398, 423, 110, 88));

        btmParell.setText("PARELL");
        btmParell.setBorder(null);
        btmParell.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmParellActionPerformed(evt);
            }
        });
        getContentPane().add(btmParell, new org.netbeans.lib.awtextra.AbsoluteConstraints(833, 697, 340, 130));

        btmSenar.setText("SENAR");
        btmSenar.setToolTipText("");
        btmSenar.setBorder(null);
        btmSenar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmSenarActionPerformed(evt);
            }
        });
        getContentPane().add(btmSenar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1175, 697, 330, 130));

        btmNegre.setBackground(new java.awt.Color(0, 0, 0));
        btmNegre.setForeground(new java.awt.Color(255, 255, 255));
        btmNegre.setText("NEGRE");
        btmNegre.setBorder(null);
        btmNegre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNegreActionPerformed(evt);
            }
        });
        getContentPane().add(btmNegre, new org.netbeans.lib.awtextra.AbsoluteConstraints(489, 697, 340, 130));

        btmVermell.setBackground(new java.awt.Color(255, 0, 0));
        btmVermell.setForeground(new java.awt.Color(255, 255, 255));
        btmVermell.setText("VERMELL");
        btmVermell.setBorder(null);
        btmVermell.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmVermellActionPerformed(evt);
            }
        });
        getContentPane().add(btmVermell, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 697, 345, 130));

        btmGirar.setFont(new java.awt.Font("Segoe UI Symbol", 1, 24)); // NOI18N
        btmGirar.setText("🔄GIRAR");
        btmGirar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmGirarActionPerformed(evt);
            }
        });
        getContentPane().add(btmGirar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1270, 210, 160, 70));

        txtLlistaApostes.setEditable(false);
        txtLlistaApostes.setBackground(new java.awt.Color(0, 123, 0));
        txtLlistaApostes.setColumns(20);
        txtLlistaApostes.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        txtLlistaApostes.setForeground(new java.awt.Color(212, 175, 55));
        txtLlistaApostes.setRows(5);
        txtLlistaApostes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(212, 175, 55), 4));
        jScrollPane1.setViewportView(txtLlistaApostes);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 153, 440, -1));

        btmMenu.setFont(new java.awt.Font("Segoe UI Symbol", 1, 24)); // NOI18N
        btmMenu.setText("☰ MENU");
        btmMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmMenuActionPerformed(evt);
            }
        });
        getContentPane().add(btmMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 15, 160, 60));

        lblrule.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ru.gif"))); // NOI18N
        getContentPane().add(lblrule, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 80, -1, -1));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 270, -1, -1));

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setPreferredSize(new java.awt.Dimension(1920, 1080));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblSaldo1.setFont(new java.awt.Font("Georgia", 1, 24)); // NOI18N
        lblSaldo1.setForeground(new java.awt.Color(212, 175, 55));
        lblSaldo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSaldo1.setText("lblSaldo");
        lblSaldo1.setAlignmentX(0.5F);
        lblSaldo1.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 1, 1, 1));
        lblSaldo1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel3.add(lblSaldo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 50));

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));
        jPanel4.setPreferredSize(new java.awt.Dimension(1920, 1080));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Georgia", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(212, 175, 55));
        jLabel4.setText("SALDO");
        jLabel4.setAlignmentX(0.5F);
        jLabel4.setAlignmentY(0.0F);
        jLabel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 1, 1, 1));
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 110, -1));

        lblSaldo3.setFont(new java.awt.Font("Georgia", 1, 36)); // NOI18N
        lblSaldo3.setForeground(new java.awt.Color(212, 175, 55));
        lblSaldo3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSaldo3.setText("lblSaldo");
        lblSaldo3.setAlignmentX(0.5F);
        lblSaldo3.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 1, 1, 1));
        lblSaldo3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel4.add(lblSaldo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 31, 290, 60));

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 30, 290, 100));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 10, 300, 60));

        btmNum37.setBackground(new java.awt.Color(255, 0, 0));
        btmNum37.setForeground(new java.awt.Color(255, 255, 255));
        btmNum37.setText("1");
        btmNum37.setBorder(null);
        btmNum37.setBorderPainted(false);
        btmNum37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum37ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum37, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 605, 113, 87));

        btmNum38.setBackground(new java.awt.Color(0, 0, 0));
        btmNum38.setForeground(new java.awt.Color(255, 255, 255));
        btmNum38.setBorder(null);
        btmNum38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum38ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum38, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 515, 113, 87));

        btmNum39.setBackground(new java.awt.Color(255, 0, 0));
        btmNum39.setForeground(new java.awt.Color(255, 255, 255));
        btmNum39.setBorder(null);
        btmNum39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum39ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum39, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 423, 113, 89));

        btmNum40.setBackground(new java.awt.Color(0, 0, 0));
        btmNum40.setForeground(new java.awt.Color(255, 255, 255));
        btmNum40.setBorder(null);
        btmNum40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum40ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum40, new org.netbeans.lib.awtextra.AbsoluteConstraints(255, 605, 113, 87));

        btmNum41.setBackground(new java.awt.Color(255, 0, 0));
        btmNum41.setForeground(new java.awt.Color(255, 255, 255));
        btmNum41.setBorder(null);
        btmNum41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum41ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum41, new org.netbeans.lib.awtextra.AbsoluteConstraints(255, 515, 113, 87));

        btmNum42.setBackground(new java.awt.Color(0, 0, 0));
        btmNum42.setForeground(new java.awt.Color(255, 255, 255));
        btmNum42.setBorder(null);
        btmNum42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum42ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum42, new org.netbeans.lib.awtextra.AbsoluteConstraints(255, 423, 113, 88));

        btmNum43.setBackground(new java.awt.Color(255, 0, 0));
        btmNum43.setForeground(new java.awt.Color(255, 255, 255));
        btmNum43.setBorder(null);
        btmNum43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum43ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum43, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 605, 113, 87));

        btmNum44.setBackground(new java.awt.Color(0, 0, 0));
        btmNum44.setForeground(new java.awt.Color(255, 255, 255));
        btmNum44.setBorder(null);
        btmNum44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum44ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum44, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 515, 113, 87));

        btmNum45.setBackground(new java.awt.Color(255, 0, 0));
        btmNum45.setForeground(new java.awt.Color(255, 255, 255));
        btmNum45.setBorder(null);
        btmNum45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum45ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum45, new org.netbeans.lib.awtextra.AbsoluteConstraints(371, 423, 113, 88));

        btmNum46.setBackground(new java.awt.Color(0, 0, 0));
        btmNum46.setForeground(new java.awt.Color(255, 255, 255));
        btmNum46.setBorder(null);
        btmNum46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum46ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum46, new org.netbeans.lib.awtextra.AbsoluteConstraints(489, 605, 111, 87));

        btmNum47.setBackground(new java.awt.Color(0, 0, 0));
        btmNum47.setForeground(new java.awt.Color(255, 255, 255));
        btmNum47.setBorder(null);
        btmNum47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum47ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum47, new org.netbeans.lib.awtextra.AbsoluteConstraints(489, 515, 113, 87));

        btmNum48.setBackground(new java.awt.Color(255, 0, 0));
        btmNum48.setForeground(new java.awt.Color(255, 255, 255));
        btmNum48.setBorder(null);
        btmNum48.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum48ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum48, new org.netbeans.lib.awtextra.AbsoluteConstraints(489, 423, 113, 88));

        btmNum49.setBackground(new java.awt.Color(0, 0, 0));
        btmNum49.setForeground(new java.awt.Color(255, 255, 255));
        btmNum49.setBorder(null);
        btmNum49.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum49ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum49, new org.netbeans.lib.awtextra.AbsoluteConstraints(606, 605, 109, 87));

        btmNum50.setBackground(new java.awt.Color(255, 0, 0));
        btmNum50.setForeground(new java.awt.Color(255, 255, 255));
        btmNum50.setBorder(null);
        btmNum50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum50ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum50, new org.netbeans.lib.awtextra.AbsoluteConstraints(606, 515, 109, 87));

        btmNum51.setBackground(new java.awt.Color(0, 0, 0));
        btmNum51.setForeground(new java.awt.Color(255, 255, 255));
        btmNum51.setBorder(null);
        btmNum51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum51ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum51, new org.netbeans.lib.awtextra.AbsoluteConstraints(606, 423, 109, 88));

        btmNum52.setBackground(new java.awt.Color(255, 0, 0));
        btmNum52.setForeground(new java.awt.Color(255, 255, 255));
        btmNum52.setBorder(null);
        btmNum52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum52ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum52, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 605, 110, 87));

        btmNum53.setBackground(new java.awt.Color(0, 0, 0));
        btmNum53.setForeground(new java.awt.Color(255, 255, 255));
        btmNum53.setBorder(null);
        btmNum53.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum53ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum53, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 515, 110, 87));

        btmNum54.setBackground(new java.awt.Color(255, 0, 0));
        btmNum54.setForeground(new java.awt.Color(255, 255, 255));
        btmNum54.setBorder(null);
        btmNum54.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum54ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum54, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 423, 110, 88));

        btmNum55.setBackground(new java.awt.Color(0, 255, 51));
        btmNum55.setBorder(null);
        btmNum55.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum55ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum55, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 423, 110, 270));

        btmNum56.setBackground(new java.awt.Color(255, 0, 0));
        btmNum56.setForeground(new java.awt.Color(255, 255, 255));
        btmNum56.setBorder(null);
        btmNum56.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum56ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum56, new org.netbeans.lib.awtextra.AbsoluteConstraints(833, 605, 110, 87));

        btmNum57.setBackground(new java.awt.Color(0, 0, 0));
        btmNum57.setForeground(new java.awt.Color(255, 255, 255));
        btmNum57.setBorder(null);
        btmNum57.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum57ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum57, new org.netbeans.lib.awtextra.AbsoluteConstraints(833, 515, 110, 87));

        btmNum58.setBackground(new java.awt.Color(255, 0, 0));
        btmNum58.setForeground(new java.awt.Color(255, 255, 255));
        btmNum58.setToolTipText("");
        btmNum58.setBorder(null);
        btmNum58.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum58ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum58, new org.netbeans.lib.awtextra.AbsoluteConstraints(833, 423, 110, 88));

        btmNum59.setBackground(new java.awt.Color(0, 0, 0));
        btmNum59.setForeground(new java.awt.Color(255, 255, 255));
        btmNum59.setBorder(null);
        btmNum59.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum59ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum59, new org.netbeans.lib.awtextra.AbsoluteConstraints(945, 605, 113, 87));

        btmNum60.setBackground(new java.awt.Color(255, 0, 0));
        btmNum60.setForeground(new java.awt.Color(255, 255, 255));
        btmNum60.setBorder(null);
        btmNum60.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum60ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum60, new org.netbeans.lib.awtextra.AbsoluteConstraints(945, 515, 113, 87));

        btmNum61.setBackground(new java.awt.Color(0, 0, 0));
        btmNum61.setForeground(new java.awt.Color(255, 255, 255));
        btmNum61.setBorder(null);
        btmNum61.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum61ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum61, new org.netbeans.lib.awtextra.AbsoluteConstraints(945, 423, 113, 88));

        btmNum62.setBackground(new java.awt.Color(255, 0, 0));
        btmNum62.setForeground(new java.awt.Color(255, 255, 255));
        btmNum62.setBorder(null);
        btmNum62.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum62ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum62, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 605, 110, 87));

        btmNum63.setBackground(new java.awt.Color(0, 0, 0));
        btmNum63.setForeground(new java.awt.Color(255, 255, 255));
        btmNum63.setBorder(null);
        btmNum63.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum63ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum63, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 515, 110, 87));

        btmNum64.setBackground(new java.awt.Color(255, 0, 0));
        btmNum64.setForeground(new java.awt.Color(255, 255, 255));
        btmNum64.setBorder(null);
        btmNum64.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum64ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum64, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 423, 110, 88));

        btmNum65.setBackground(new java.awt.Color(0, 0, 0));
        btmNum65.setForeground(new java.awt.Color(255, 255, 255));
        btmNum65.setBorder(null);
        btmNum65.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum65ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum65, new org.netbeans.lib.awtextra.AbsoluteConstraints(1288, 423, 107, 88));

        btmNum66.setBackground(new java.awt.Color(0, 0, 0));
        btmNum66.setForeground(new java.awt.Color(255, 255, 255));
        btmNum66.setBorder(null);
        btmNum66.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum66ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum66, new org.netbeans.lib.awtextra.AbsoluteConstraints(1175, 605, 110, 87));

        btmNum67.setBackground(new java.awt.Color(0, 0, 0));
        btmNum67.setForeground(new java.awt.Color(255, 255, 255));
        btmNum67.setBorder(null);
        btmNum67.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum67ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum67, new org.netbeans.lib.awtextra.AbsoluteConstraints(1175, 515, 110, 87));

        btmNum68.setBackground(new java.awt.Color(255, 0, 0));
        btmNum68.setForeground(new java.awt.Color(255, 255, 255));
        btmNum68.setBorder(null);
        btmNum68.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum68ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum68, new org.netbeans.lib.awtextra.AbsoluteConstraints(1175, 423, 110, 88));

        btmNum69.setBackground(new java.awt.Color(0, 0, 0));
        btmNum69.setForeground(new java.awt.Color(255, 255, 255));
        btmNum69.setBorder(null);
        btmNum69.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum69ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum69, new org.netbeans.lib.awtextra.AbsoluteConstraints(1288, 605, 107, 87));

        btmNum70.setBackground(new java.awt.Color(255, 0, 0));
        btmNum70.setForeground(new java.awt.Color(255, 255, 255));
        btmNum70.setBorder(null);
        btmNum70.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum70ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum70, new org.netbeans.lib.awtextra.AbsoluteConstraints(1288, 515, 107, 87));

        btmNum71.setBackground(new java.awt.Color(255, 0, 0));
        btmNum71.setForeground(new java.awt.Color(255, 255, 255));
        btmNum71.setBorder(null);
        btmNum71.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum71ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum71, new org.netbeans.lib.awtextra.AbsoluteConstraints(1398, 605, 110, 87));

        btmNum72.setBackground(new java.awt.Color(0, 0, 0));
        btmNum72.setForeground(new java.awt.Color(255, 255, 255));
        btmNum72.setBorder(null);
        btmNum72.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum72ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum72, new org.netbeans.lib.awtextra.AbsoluteConstraints(1398, 515, 110, 87));

        btmNum73.setBackground(new java.awt.Color(255, 0, 0));
        btmNum73.setForeground(new java.awt.Color(255, 255, 255));
        btmNum73.setBorder(null);
        btmNum73.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNum73ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNum73, new org.netbeans.lib.awtextra.AbsoluteConstraints(1398, 423, 110, 88));

        btmParell1.setBorder(null);
        btmParell1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmParell1ActionPerformed(evt);
            }
        });
        getContentPane().add(btmParell1, new org.netbeans.lib.awtextra.AbsoluteConstraints(833, 697, 340, 130));

        btmSenar1.setToolTipText("");
        btmSenar1.setBorder(null);
        btmSenar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmSenar1ActionPerformed(evt);
            }
        });
        getContentPane().add(btmSenar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1175, 697, 330, 130));

        btmNegre1.setBackground(new java.awt.Color(0, 0, 0));
        btmNegre1.setForeground(new java.awt.Color(255, 255, 255));
        btmNegre1.setBorder(null);
        btmNegre1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNegre1ActionPerformed(evt);
            }
        });
        getContentPane().add(btmNegre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(489, 697, 340, 130));

        btmVermell1.setBackground(new java.awt.Color(255, 0, 0));
        btmVermell1.setForeground(new java.awt.Color(255, 255, 255));
        btmVermell1.setBorder(null);
        btmVermell1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmVermell1ActionPerformed(evt);
            }
        });
        getContentPane().add(btmVermell1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 697, 345, 130));

        lblrule1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/rule.gif"))); // NOI18N
        getContentPane().add(lblrule1, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 80, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btmNum0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum0ActionPerformed
        mostrarPopUpAposta("NUMERO", 0);
                fitxaNum(btmNum55);
    }//GEN-LAST:event_btmNum0ActionPerformed

    private void btmNum1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum1ActionPerformed
        mostrarPopUpAposta("NUMERO", 1);
        fitxaNum(btmNum37);
           }//GEN-LAST:event_btmNum1ActionPerformed


    private void btmNum2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum2ActionPerformed
        mostrarPopUpAposta("NUMERO", 2);
        fitxaNum(btmNum38);

}//GEN-LAST:event_btmNum2ActionPerformed

    private void btmNum3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum3ActionPerformed
        mostrarPopUpAposta("NUMERO", 3);
        fitxaNum(btmNum39);
}//GEN-LAST:event_btmNum3ActionPerformed

    private void btmNum4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum4ActionPerformed
        mostrarPopUpAposta("NUMERO", 4);
        fitxaNum(btmNum40);
}//GEN-LAST:event_btmNum4ActionPerformed

    private void btmNum5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum5ActionPerformed
        mostrarPopUpAposta("NUMERO", 5);
        fitxaNum(btmNum41);
}//GEN-LAST:event_btmNum5ActionPerformed

    private void btmNum6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum6ActionPerformed
        mostrarPopUpAposta("NUMERO", 6);
        fitxaNum(btmNum42);
 }//GEN-LAST:event_btmNum6ActionPerformed

    private void btmNum7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum7ActionPerformed
        mostrarPopUpAposta("NUMERO", 7);
        fitxaNum(btmNum43);
    }//GEN-LAST:event_btmNum7ActionPerformed

    private void btmNum8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum8ActionPerformed
        mostrarPopUpAposta("NUMERO", 8);
        fitxaNum(btmNum44);
}//GEN-LAST:event_btmNum8ActionPerformed

    private void btmNum9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum9ActionPerformed
        mostrarPopUpAposta("NUMERO", 9);
        fitxaNum(btmNum45);
    }//GEN-LAST:event_btmNum9ActionPerformed

    private void btmNum10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum10ActionPerformed
        mostrarPopUpAposta("NUMERO", 10);
        fitxaNum(btmNum46);
    }//GEN-LAST:event_btmNum10ActionPerformed

    private void btmNum11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum11ActionPerformed
        mostrarPopUpAposta("NUMERO", 11);
        fitxaNum(btmNum47);
    }//GEN-LAST:event_btmNum11ActionPerformed

    private void btmNum12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum12ActionPerformed
        mostrarPopUpAposta("NUMERO", 12);
        fitxaNum(btmNum48);
    }//GEN-LAST:event_btmNum12ActionPerformed

    private void btmNum13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum13ActionPerformed
        mostrarPopUpAposta("NUMERO", 13);
        fitxaNum(btmNum49);

    }//GEN-LAST:event_btmNum13ActionPerformed

    private void btmNum14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum14ActionPerformed
        mostrarPopUpAposta("NUMERO", 14);
        fitxaNum(btmNum50);
    }//GEN-LAST:event_btmNum14ActionPerformed

    private void btmNum15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum15ActionPerformed
        mostrarPopUpAposta("NUMERO", 15);
        fitxaNum(btmNum51);
    }//GEN-LAST:event_btmNum15ActionPerformed

    private void btmNum16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum16ActionPerformed
        mostrarPopUpAposta("NUMERO", 16);
        fitxaNum(btmNum52);
    }//GEN-LAST:event_btmNum16ActionPerformed

    private void btmNum17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum17ActionPerformed
        mostrarPopUpAposta("NUMERO", 17);
        fitxaNum(btmNum53);
    }//GEN-LAST:event_btmNum17ActionPerformed

    private void btmNum18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum18ActionPerformed
        mostrarPopUpAposta("NUMERO", 18);
        fitxaNum(btmNum54);
    }//GEN-LAST:event_btmNum18ActionPerformed

    private void btmNum19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum19ActionPerformed
        mostrarPopUpAposta("NUMERO", 19);
        fitxaNum(btmNum56);
    }//GEN-LAST:event_btmNum19ActionPerformed

    private void btmNum20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum20ActionPerformed
        mostrarPopUpAposta("NUMERO", 20);
        fitxaNum(btmNum57);
    }//GEN-LAST:event_btmNum20ActionPerformed

    private void btmNum21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum21ActionPerformed
        mostrarPopUpAposta("NUMERO", 21);
        fitxaNum(btmNum58);
    }//GEN-LAST:event_btmNum21ActionPerformed

    private void btmNum22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum22ActionPerformed
        mostrarPopUpAposta("NUMERO", 22);
        fitxaNum(btmNum59);
    }//GEN-LAST:event_btmNum22ActionPerformed

    private void btmNum23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum23ActionPerformed
        mostrarPopUpAposta("NUMERO", 23);
        fitxaNum(btmNum60);
    }//GEN-LAST:event_btmNum23ActionPerformed

    private void btmNum24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum24ActionPerformed
        mostrarPopUpAposta("NUMERO", 24);
        fitxaNum(btmNum61);
    }//GEN-LAST:event_btmNum24ActionPerformed

    private void btmNum25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum25ActionPerformed
        mostrarPopUpAposta("NUMERO", 25);
        fitxaNum(btmNum62);
    }//GEN-LAST:event_btmNum25ActionPerformed

    private void btmNum26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum26ActionPerformed
        mostrarPopUpAposta("NUMERO", 26);
        fitxaNum(btmNum63);
    }//GEN-LAST:event_btmNum26ActionPerformed

    private void btmNum27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum27ActionPerformed
        mostrarPopUpAposta("NUMERO", 27);
        fitxaNum(btmNum64);
    }//GEN-LAST:event_btmNum27ActionPerformed

    private void btmNum28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum28ActionPerformed
        mostrarPopUpAposta("NUMERO", 28);
        fitxaNum(btmNum66);
    }//GEN-LAST:event_btmNum28ActionPerformed

    private void btmNum29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum29ActionPerformed
        mostrarPopUpAposta("NUMERO", 29);
        fitxaNum(btmNum67);
    }//GEN-LAST:event_btmNum29ActionPerformed

    private void btmNum30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum30ActionPerformed
        mostrarPopUpAposta("NUMERO", 30);
        fitxaNum(btmNum68);
    }//GEN-LAST:event_btmNum30ActionPerformed

    private void btmNum31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum31ActionPerformed
        mostrarPopUpAposta("NUMERO", 31);
        fitxaNum(btmNum69);
    }//GEN-LAST:event_btmNum31ActionPerformed

    private void btmNum32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum32ActionPerformed
        mostrarPopUpAposta("NUMERO", 32);
        fitxaNum(btmNum70);
    }//GEN-LAST:event_btmNum32ActionPerformed

    private void btmNum33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum33ActionPerformed
        mostrarPopUpAposta("NUMERO", 33);
        fitxaNum(btmNum65);
    }//GEN-LAST:event_btmNum33ActionPerformed

    private void btmNum34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum34ActionPerformed
        mostrarPopUpAposta("NUMERO", 34);
        fitxaNum(btmNum71);
    }//GEN-LAST:event_btmNum34ActionPerformed

    private void btmNum35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum35ActionPerformed
        mostrarPopUpAposta("NUMERO", 35);
        fitxaNum(btmNum72);
    }//GEN-LAST:event_btmNum35ActionPerformed

    private void btmNum36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum36ActionPerformed
        mostrarPopUpAposta("NUMERO", 36);
        fitxaNum(btmNum73);
    }//GEN-LAST:event_btmNum36ActionPerformed

    private void btmParellActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmParellActionPerformed
        mostrarPopUpAposta("PARELL_SENAR", true);
        fitxaNum(btmParell1);
    }//GEN-LAST:event_btmParellActionPerformed

    private void btmSenarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmSenarActionPerformed
        mostrarPopUpAposta("PARELL_SENAR", false);
        fitxaNum(btmSenar1);
    }//GEN-LAST:event_btmSenarActionPerformed

    private void btmNegreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNegreActionPerformed
        mostrarPopUpAposta("COLOR", "Negre");
        fitxaNum(btmNegre1);
    }//GEN-LAST:event_btmNegreActionPerformed

    private void btmVermellActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmVermellActionPerformed
        mostrarPopUpAposta("COLOR", "Vermell");
        fitxaNum(btmVermell1);
    }//GEN-LAST:event_btmVermellActionPerformed

    private void btmGirarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmGirarActionPerformed
        if (apostesActives.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "No hi ha cap aposta feta!");
            return;
        }
        ImageIcon icon = new ImageIcon(getClass().getResource("/Images/rule.gif"));
        btmGirar.setEnabled(false);
        lblrule.setVisible(true);
        lblrule.setIcon(icon);
        btmMenu.setEnabled(false);
        btmsEnabledOff();
        javax.swing.Timer timer = new javax.swing.Timer(5000, e -> processarResultatRuleta());
        timer.setRepeats(false);
        timer.start();

    }//GEN-LAST:event_btmGirarActionPerformed

    private void btmMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmMenuActionPerformed

        tornarMenuClient();
        this.dispose();
    }//GEN-LAST:event_btmMenuActionPerformed

    private void btmNum37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum37ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btmNum37ActionPerformed

    private void btmNum38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum38ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btmNum38ActionPerformed

    private void btmNum39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum39ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btmNum39ActionPerformed

    private void btmNum40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum40ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btmNum40ActionPerformed

    private void btmNum41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum41ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btmNum41ActionPerformed

    private void btmNum42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum42ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btmNum42ActionPerformed

    private void btmNum43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum43ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btmNum43ActionPerformed

    private void btmNum44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum44ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btmNum44ActionPerformed

    private void btmNum45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum45ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btmNum45ActionPerformed

    private void btmNum46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum46ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btmNum46ActionPerformed

    private void btmNum47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum47ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btmNum47ActionPerformed

    private void btmNum48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum48ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btmNum48ActionPerformed

    private void btmNum49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum49ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btmNum49ActionPerformed

    private void btmNum50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum50ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btmNum50ActionPerformed

    private void btmNum51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum51ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btmNum51ActionPerformed

    private void btmNum52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum52ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btmNum52ActionPerformed

    private void btmNum53ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum53ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btmNum53ActionPerformed

    private void btmNum54ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum54ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btmNum54ActionPerformed

    private void btmNum55ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum55ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btmNum55ActionPerformed

    private void btmNum56ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum56ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btmNum56ActionPerformed

    private void btmNum57ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum57ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btmNum57ActionPerformed

    private void btmNum58ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum58ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btmNum58ActionPerformed

    private void btmNum59ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum59ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btmNum59ActionPerformed

    private void btmNum60ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum60ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btmNum60ActionPerformed

    private void btmNum61ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum61ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btmNum61ActionPerformed

    private void btmNum62ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum62ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btmNum62ActionPerformed

    private void btmNum63ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum63ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btmNum63ActionPerformed

    private void btmNum64ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum64ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btmNum64ActionPerformed

    private void btmNum65ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum65ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btmNum65ActionPerformed

    private void btmNum66ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum66ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btmNum66ActionPerformed

    private void btmNum67ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum67ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btmNum67ActionPerformed

    private void btmNum68ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum68ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btmNum68ActionPerformed

    private void btmNum69ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum69ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btmNum69ActionPerformed

    private void btmNum70ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum70ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btmNum70ActionPerformed

    private void btmNum71ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum71ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btmNum71ActionPerformed

    private void btmNum72ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum72ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btmNum72ActionPerformed

    private void btmNum73ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNum73ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btmNum73ActionPerformed

    private void btmParell1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmParell1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btmParell1ActionPerformed

    private void btmSenar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmSenar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btmSenar1ActionPerformed

    private void btmNegre1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNegre1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btmNegre1ActionPerformed

    private void btmVermell1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmVermell1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btmVermell1ActionPerformed

    private void processarResultatRuleta() {

        int indexGuanyador = random.nextInt(tauler.size());
        casellaRuleta resultat = tauler.get(indexGuanyador);

        escriureLog("La bola ha caigut al número: " + resultat.getNumero() + " (" + resultat.getColor() + ")");

        double totalPremi = 0;
        String tipusGuanyador = "Perdut";
        ruletaImg(lblrule, resultat.getNumero());

        for (ApostaRealitzada ap : apostesActives) {
            boolean guanyaApostaIndividual = false;
            double mult = 0;

            if (ap.tipus.equals("NUMERO") && resultat.getNumero() == (int) ap.valor) {
                guanyaApostaIndividual = true;
                mult = 36;
                tipusGuanyador = "Número";
            } else if (ap.tipus.equals("COLOR") && resultat.getColor().equalsIgnoreCase((String) ap.valor)) {
                guanyaApostaIndividual = true;
                mult = 2;
                if (tipusGuanyador.equals("Perdut")) {
                    tipusGuanyador = "Color";
                }
            } else if (ap.tipus.equals("PARELL_SENAR") && resultat.getNumero() != 0 && resultat.isParell() == (boolean) ap.valor) {
                guanyaApostaIndividual = true;
                mult = 2;
                if (tipusGuanyador.equals("Perdut")) {
                    tipusGuanyador = "Parell/Senar";
                }
            }

            if (guanyaApostaIndividual) {
                totalPremi += (ap.diners * mult);
            }
        }

        colorRuleta colorEnum = colorRuleta.valueOf(resultat.getColor());
        guardarPartidaRuleta(
                userActual.getNom(),
                resultat.getNumero(),
                colorEnum,
                resultat.isParell(),
                tipusGuanyador
        );

        if (totalPremi > 0) {
            userActual.setSaldo(userActual.getSaldo() + totalPremi);
            CONEXION.Queries.updateSaldo(userActual.getId(), userActual.getSaldo());
            javax.swing.JOptionPane.showMessageDialog(null,
                    "Número: " + resultat.getNumero() + " (" + resultat.getColor() + ")\n"
                    + "Has guanyat per: " + tipusGuanyador + "\n"
                    + "TOTAL PREMI: " + totalPremi + "€!");
            escriureLog("RESULTAT: " + userActual.getNom() + " ha guanyat " + totalPremi + "€");

        } else {
            javax.swing.JOptionPane.showMessageDialog(null, "Resultat: " + resultat.getNumero() + ". No has guanyat res.");
            escriureLog("RESULTAT: " + userActual.getNom() + " no ha guanyat res.");
        }

        numOff();

        apostesActives.clear();
        txtLlistaApostes.setText("");
        saldo();
        btmGirar.setEnabled(true);
        btmMenu.setEnabled(true);
        btmsEnabledOn();

    }

    private void numEnabled() {
    }

    public void numOff() {

        for (int i = 0; i < btmNums.length; i++) {
            btmNums[i].setIcon(null);
        }
    }

    private void btmsEnabledOff() {
        for (int i = 0; i < btmNums.length; i++) {
            btmNums[i].setDisabledIcon(btmNums[i].getIcon());
            btmNums[i].setEnabled(false);
        }
    }

    private void btmsEnabledOn() {
        for (int i = 0; i < btmNums.length; i++) {
            btmNums[i].setEnabled(true);
        }
    }

    public void ruletaImg(JLabel label, int numero) {

        ImageIcon icono = null;

        switch (numero) {

            case 0:
                icono = new ImageIcon(getClass().getResource("/Images/0b.gif"));
                break;

            case 1:
                icono = new ImageIcon(getClass().getResource("/Images/1b.gif"));
                break;

            case 2:
                icono = new ImageIcon(getClass().getResource("/Images/2b.gif"));
                break;

            case 3:
                icono = new ImageIcon(getClass().getResource("/Images/3b.gif"));
                break;

            case 4:
                icono = new ImageIcon(getClass().getResource("/Images/4b.gif"));
                break;

            case 5:
                icono = new ImageIcon(getClass().getResource("/Images/5b.gif"));
                break;

            case 6:
                icono = new ImageIcon(getClass().getResource("/Images/6b.gif"));
                break;

            case 7:
                icono = new ImageIcon(getClass().getResource("/Images/7b.gif"));
                break;

            case 8:
                icono = new ImageIcon(getClass().getResource("/Images/8b.gif"));
                break;

            case 9:
                icono = new ImageIcon(getClass().getResource("/Images/9b.gif"));
                break;

            case 10:
                icono = new ImageIcon(getClass().getResource("/Images/10b.gif"));
                break;

            case 11:
                icono = new ImageIcon(getClass().getResource("/Images/11b.gif"));
                break;

            case 12:
                icono = new ImageIcon(getClass().getResource("/Images/12b.gif"));
                break;

            case 13:
                icono = new ImageIcon(getClass().getResource("/Images/13b.gif"));
                break;

            case 14:
                icono = new ImageIcon(getClass().getResource("/Images/14b.gif"));
                break;

            case 15:
                icono = new ImageIcon(getClass().getResource("/Images/15b.gif"));
                break;

            case 16:
                icono = new ImageIcon(getClass().getResource("/Images/16b.gif"));
                break;

            case 17:
                icono = new ImageIcon(getClass().getResource("/Images/17b.gif"));
                break;

            case 18:
                icono = new ImageIcon(getClass().getResource("/Images/18b.gif"));
                break;

            case 19:
                icono = new ImageIcon(getClass().getResource("/Images/19b.gif"));
                break;

            case 20:
                icono = new ImageIcon(getClass().getResource("/Images/20b.gif"));
                break;

            case 21:
                icono = new ImageIcon(getClass().getResource("/Images/21b.gif"));
                break;

            case 22:
                icono = new ImageIcon(getClass().getResource("/Images/22b.gif"));
                break;

            case 23:
                icono = new ImageIcon(getClass().getResource("/Images/23b.gif"));
                break;

            case 24:
                icono = new ImageIcon(getClass().getResource("/Images/24b.gif"));
                break;

            case 25:
                icono = new ImageIcon(getClass().getResource("/Images/25b.gif"));
                break;

            case 26:
                icono = new ImageIcon(getClass().getResource("/Images/26b.gif"));
                break;

            case 27:
                icono = new ImageIcon(getClass().getResource("/Images/27b.gif"));
                break;

            case 28:
                icono = new ImageIcon(getClass().getResource("/Images/28b.gif"));
                break;

            case 29:
                icono = new ImageIcon(getClass().getResource("/Images/29b.gif"));
                break;

            case 30:
                icono = new ImageIcon(getClass().getResource("/Images/30b.gif"));
                break;

            case 31:
                icono = new ImageIcon(getClass().getResource("/Images/31b.gif"));
                break;

            case 32:
                icono = new ImageIcon(getClass().getResource("/Images/32b.gif"));
                break;

            case 33:
                icono = new ImageIcon(getClass().getResource("/Images/33b.gif"));
                break;

            case 34:
                icono = new ImageIcon(getClass().getResource("/Images/34b.gif"));
                break;

            case 35:
                icono = new ImageIcon(getClass().getResource("/Images/35b.gif"));
                break;

            case 36:
                icono = new ImageIcon(getClass().getResource("/Images/36b.gif"));
                break;
        }

        label.setIcon(icono);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new frmRuleta().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btmGirar;
    private javax.swing.JButton btmMenu;
    private javax.swing.JButton btmNegre;
    private javax.swing.JButton btmNegre1;
    private javax.swing.JButton btmNum0;
    private javax.swing.JButton btmNum1;
    private javax.swing.JButton btmNum10;
    private javax.swing.JButton btmNum11;
    private javax.swing.JButton btmNum12;
    private javax.swing.JButton btmNum13;
    private javax.swing.JButton btmNum14;
    private javax.swing.JButton btmNum15;
    private javax.swing.JButton btmNum16;
    private javax.swing.JButton btmNum17;
    private javax.swing.JButton btmNum18;
    private javax.swing.JButton btmNum19;
    private javax.swing.JButton btmNum2;
    private javax.swing.JButton btmNum20;
    private javax.swing.JButton btmNum21;
    private javax.swing.JButton btmNum22;
    private javax.swing.JButton btmNum23;
    private javax.swing.JButton btmNum24;
    private javax.swing.JButton btmNum25;
    private javax.swing.JButton btmNum26;
    private javax.swing.JButton btmNum27;
    private javax.swing.JButton btmNum28;
    private javax.swing.JButton btmNum29;
    private javax.swing.JButton btmNum3;
    private javax.swing.JButton btmNum30;
    private javax.swing.JButton btmNum31;
    private javax.swing.JButton btmNum32;
    private javax.swing.JButton btmNum33;
    private javax.swing.JButton btmNum34;
    private javax.swing.JButton btmNum35;
    private javax.swing.JButton btmNum36;
    private javax.swing.JButton btmNum37;
    private javax.swing.JButton btmNum38;
    private javax.swing.JButton btmNum39;
    private javax.swing.JButton btmNum4;
    private javax.swing.JButton btmNum40;
    private javax.swing.JButton btmNum41;
    private javax.swing.JButton btmNum42;
    private javax.swing.JButton btmNum43;
    private javax.swing.JButton btmNum44;
    private javax.swing.JButton btmNum45;
    private javax.swing.JButton btmNum46;
    private javax.swing.JButton btmNum47;
    private javax.swing.JButton btmNum48;
    private javax.swing.JButton btmNum49;
    private javax.swing.JButton btmNum5;
    private javax.swing.JButton btmNum50;
    private javax.swing.JButton btmNum51;
    private javax.swing.JButton btmNum52;
    private javax.swing.JButton btmNum53;
    private javax.swing.JButton btmNum54;
    private javax.swing.JButton btmNum55;
    private javax.swing.JButton btmNum56;
    private javax.swing.JButton btmNum57;
    private javax.swing.JButton btmNum58;
    private javax.swing.JButton btmNum59;
    private javax.swing.JButton btmNum6;
    private javax.swing.JButton btmNum60;
    private javax.swing.JButton btmNum61;
    private javax.swing.JButton btmNum62;
    private javax.swing.JButton btmNum63;
    private javax.swing.JButton btmNum64;
    private javax.swing.JButton btmNum65;
    private javax.swing.JButton btmNum66;
    private javax.swing.JButton btmNum67;
    private javax.swing.JButton btmNum68;
    private javax.swing.JButton btmNum69;
    private javax.swing.JButton btmNum7;
    private javax.swing.JButton btmNum70;
    private javax.swing.JButton btmNum71;
    private javax.swing.JButton btmNum72;
    private javax.swing.JButton btmNum73;
    private javax.swing.JButton btmNum8;
    private javax.swing.JButton btmNum9;
    private javax.swing.JButton btmParell;
    private javax.swing.JButton btmParell1;
    private javax.swing.JButton btmSenar;
    private javax.swing.JButton btmSenar1;
    private javax.swing.JButton btmVermell;
    private javax.swing.JButton btmVermell1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblSaldo1;
    private javax.swing.JLabel lblSaldo3;
    private javax.swing.JLabel lblrule;
    private javax.swing.JLabel lblrule1;
    private javax.swing.JTextArea txtLlistaApostes;
    // End of variables declaration//GEN-END:variables
}
