/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.CardLayout;
import java.io.File;
import java.util.ListIterator;
import model.GameController;
import model.GameController.Dialogues;
import model.GameController.Event;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import model.utils.Couple;

/**
 *
 * @author franc
 */
public class GameGUI extends javax.swing.JFrame {

    static final int NORTH = 0;
    static final int WEST = 1;
    static final int SOUTH = 2;
    static final int EAST = 3;

    static GameController core;
    static CardLayout cl;
    static Couple<String, ListIterator> dialogue;

    /**
     * Creates new form Interface
     */
    public GameGUI() {
        initComponents();

        cl = (CardLayout) (jPanel1.getLayout());
        jLabel3.setVisible(false);
        jLabel4.setVisible(false);
        jButton7.setVisible(false);
        jComboBox1.setVisible(false);
        jButton9.setVisible(false);
        jButton5.setVisible(false);
        jButton14.setVisible(false);
        jTextField2.setVisible(false);

        cl.show(jPanel1, "card5");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton10 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButton12 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("mapproject21");
        setResizable(false);
        setSize(new java.awt.Dimension(1280, 720));
        getContentPane().setLayout(null);

        jLayeredPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setFocusPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jLayeredPane1.add(jButton1);
        jButton1.setBounds(410, 0, 500, 60);

        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.setFocusPainted(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jLayeredPane1.add(jButton3);
        jButton3.setBounds(0, 150, 80, 390);

        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setFocusPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jLayeredPane1.add(jButton2);
        jButton2.setBounds(410, 670, 490, 50);

        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.setFocusPainted(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jLayeredPane1.add(jButton4);
        jButton4.setBounds(1180, 170, 100, 370);

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Lucida Console", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Bottom text");
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel3.setAlignmentY(0.0F);
        jLabel3.setAutoscrolls(true);
        jLabel3.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel3.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel3.setOpaque(true);
        jLayeredPane1.add(jLabel3);
        jLabel3.setBounds(380, 560, 220, 40);

        jButton7.setFont(new java.awt.Font("Lucida Console", 1, 14)); // NOI18N
        jButton7.setText("Salva ed esci");
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton7.setDefaultCapable(false);
        jButton7.setFocusPainted(false);
        jButton7.setFocusable(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jLayeredPane1.add(jButton7);
        jButton7.setBounds(1120, 0, 160, 30);

        jButton5.setFont(new java.awt.Font("Lucida Console", 0, 11)); // NOI18N
        jButton5.setText("Usa");
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.setFocusPainted(false);
        jButton5.setFocusable(false);
        jLayeredPane1.add(jButton5);
        jButton5.setBounds(100, 30, 100, 30);

        jButton9.setFont(new java.awt.Font("Lucida Console", 0, 11)); // NOI18N
        jButton9.setText("Esamina");
        jButton9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton9.setDefaultCapable(false);
        jButton9.setFocusPainted(false);
        jButton9.setFocusable(false);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jLayeredPane1.add(jButton9);
        jButton9.setBounds(0, 30, 100, 30);

        jComboBox1.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        jComboBox1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 2, 1, 1));
        jComboBox1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox1.setFocusable(false);
        jLayeredPane1.add(jComboBox1);
        jComboBox1.setBounds(0, 0, 200, 30);

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Lucida Console", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Bottom text");
        jLabel4.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel4.setAlignmentY(0.0F);
        jLabel4.setAutoscrolls(true);
        jLabel4.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel4.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel4.setOpaque(true);
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        jLayeredPane1.add(jLabel4);
        jLabel4.setBounds(380, 600, 530, 70);

        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.setEnabled(false);
        jPanel1.setOpaque(false);
        jPanel1.setLayout(new java.awt.CardLayout());

        jPanel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.setOpaque(false);
        jPanel2.setLayout(null);

        jButton10.setBorderPainted(false);
        jButton10.setContentAreaFilled(false);
        jButton10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton10.setDefaultCapable(false);
        jButton10.setDoubleBuffered(true);
        jButton10.setFocusPainted(false);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton10);
        jButton10.setBounds(540, 310, 120, 210);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sword.png"))); // NOI18N
        jButton6.setBorderPainted(false);
        jButton6.setContentAreaFilled(false);
        jButton6.setDefaultCapable(false);
        jButton6.setFocusPainted(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton6);
        jButton6.setBounds(645, 524, 370, 100);

        jPanel1.add(jPanel2, "card2");

        jPanel3.setOpaque(false);
        jPanel3.setLayout(null);

        jButton12.setBorderPainted(false);
        jButton12.setContentAreaFilled(false);
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton12);
        jButton12.setBounds(730, 333, 140, 220);

        jPanel1.add(jPanel3, "card3");

        jPanel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel4.setOpaque(false);
        jPanel4.setLayout(null);
        jPanel1.add(jPanel4, "card4");

        jPanel5.setOpaque(false);
        jPanel5.setLayout(null);

        jButton8.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton8.setText("Nuova partita");
        jButton8.setDefaultCapable(false);
        jButton8.setFocusable(false);
        jButton8.setPreferredSize(new java.awt.Dimension(120, 23));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton8);
        jButton8.setBounds(400, 390, 190, 40);

        jButton11.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton11.setText("Carica partita");
        jButton11.setDefaultCapable(false);
        jButton11.setFocusable(false);
        jButton11.setPreferredSize(new java.awt.Dimension(120, 23));
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton11);
        jButton11.setBounds(590, 390, 190, 40);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Titolo molto bello");
        jPanel5.add(jLabel2);
        jLabel2.setBounds(400, 150, 420, 110);

        jLabel6.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Inserisci il nome");
        jLabel6.setOpaque(true);
        jPanel5.add(jLabel6);
        jLabel6.setBounds(400, 430, 190, 40);

        jTextField1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jTextField1.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jPanel5.add(jTextField1);
        jTextField1.setBounds(590, 430, 190, 40);

        jLabel7.setText("GitHub");
        jPanel5.add(jLabel7);
        jLabel7.setBounds(840, 490, 34, 14);

        jLabel8.setText("Manuale");
        jPanel5.add(jLabel8);
        jLabel8.setBounds(880, 490, 40, 14);

        jPanel1.add(jPanel5, "card5");

        jPanel6.setOpaque(false);
        jPanel6.setLayout(null);

        jButton13.setBorderPainted(false);
        jButton13.setContentAreaFilled(false);
        jButton13.setDefaultCapable(false);
        jButton13.setFocusPainted(false);
        jButton13.setOpaque(false);
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton13);
        jButton13.setBounds(740, 393, 70, 110);

        jButton14.setText("Tenta");
        jButton14.setEnabled(false);
        jButton14.setFocusPainted(false);
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton14);
        jButton14.setBounds(760, 500, 140, 30);

        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });
        jPanel6.add(jTextField2);
        jTextField2.setBounds(700, 500, 50, 30);

        jPanel1.add(jPanel6, "card6");

        jLayeredPane1.add(jPanel1);
        jPanel1.setBounds(0, 0, 1280, 720);

        jLabel1.setMinimumSize(new java.awt.Dimension(10, 20));
        jLabel1.setPreferredSize(new java.awt.Dimension(20, 20));
        jLabel1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jLabel1PropertyChange(evt);
            }
        });
        jLayeredPane1.add(jLabel1);
        jLabel1.setBounds(0, 0, 1280, 720);

        getContentPane().add(jLayeredPane1);
        jLayeredPane1.setBounds(-1, -1, 1280, 720);

        setSize(new java.awt.Dimension(1296, 759));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    //walkForward
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        core.walkForward();
        setImage(core.getFacingImageFileName(), jLabel1);
    }//GEN-LAST:event_jButton1ActionPerformed

    //turnLeft
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        core.turnLeft();
        setImage(core.getFacingImageFileName(), jLabel1);
    }//GEN-LAST:event_jButton3ActionPerformed

    //walkBackwards
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        core.walkBackwards();
        setImage(core.getFacingImageFileName(), jLabel1);
    }//GEN-LAST:event_jButton2ActionPerformed

    //turnRight
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        core.turnRight();
        setImage(core.getFacingImageFileName(), jLabel1);
    }//GEN-LAST:event_jButton4ActionPerformed

    //Salva ed esci
    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        core.save();
        this.dispose();
    }//GEN-LAST:event_jButton7ActionPerformed

    //Cambio immagine
    private void jLabel1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jLabel1PropertyChange
        try {
            switch (core.getEvent()){
                case GameController.KID_INTERACTION:
                    cl.show(jPanel1, "card6");
                    break;
                    
                case GameController.GUARD_INTERACTION:
                    cl.show(jPanel1, "card3");
                    break;
                    
                case GameController.MERCHANT_INTERACTION:
                    cl.show(jPanel1, "card2");
                    break;
                    
                default:
                    cl.show(jPanel1, "card4");
            }
        } catch (Exception e) {
            //Capire perché dà NullPointerException
            System.err.println(e);
        }
    }//GEN-LAST:event_jLabel1PropertyChange

    //jLabel dialogo
    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        if (dialogue.getSecond().hasNext()) {
            jLabel4.setText("<html>" + dialogue.getSecond().next() + "</html>");
        } else {
            jLabel4.setVisible(false);
            jLabel3.setVisible(false);
        }
    }//GEN-LAST:event_jLabel4MouseClicked

    //TODO
    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    //Mercante
    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        displayDialogue(core.loadDialogue(Dialogues.MERCHANT_FIRST));
    }//GEN-LAST:event_jButton10ActionPerformed

    //Guardia
    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        displayDialogue(core.loadDialogue(Dialogues.GUARD));
    }//GEN-LAST:event_jButton12ActionPerformed
    
    //Spada
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        addItem("Sword");
        jButton6.setVisible(false);
    }//GEN-LAST:event_jButton6ActionPerformed

    //Nuova partita
    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        //Aggiungere "Sei sicuro di voler sovrascrivere?".
        if (!jTextField1.getText().isEmpty()) {
            if (new File("saveGame.json").isFile()) {
                if (JOptionPane.showConfirmDialog(GameGUI.this, "Esiste già un altro salvataggio. Vuoi sovrascriverlo?", "Attenzione!", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    core.setPlayerName(jTextField1.getText());
                    core.save();

                    startGame();
                }
            } else {
                core.setPlayerName(jTextField1.getText());
                startGame();
            }
        } else {
            JOptionPane.showMessageDialog(GameGUI.this, "Devi inserire una lingua e un nome.", "Errore!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    //Carica partita
    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        if (new File("saveGame.json").isFile()) {
            core.load();

            core.getPlayerInventory().forEach((Object item) -> {
                jComboBox1.addItem(item.toString());
            });

            startGame();
        } else {
            JOptionPane.showMessageDialog(GameGUI.this, "Non esiste alcun file da caricare.", "Errore!", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jButton11ActionPerformed

    //Bimbo
    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        if (!core.hasHappened(Event.MINIGAME)) {
            displayDialogue(core.loadDialogue(Dialogues.KID_FIRST));
            jTextField2.setVisible(true);
            jButton14.setVisible(true);
            jButton13.setVisible(false);
            core.startMiniGame();
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    //Controllo lunghezza numero minigioco
    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        if (jTextField2.getText().length() == 4) {
            jButton14.setEnabled(true);
        } else {
            jButton14.setEnabled(false);
        }
    }//GEN-LAST:event_jTextField2KeyReleased

    //Tentativo minigioco
    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        try {
            Integer.parseInt(jTextField2.getText());
            
            core.guessGame(jTextField2.getText());
            jLabel4.setVisible(true);
            String result = core.getGameResult();
            jLabel4.setText("<html>" + result.replace(". ", ".<br>") + "</html>");

            if (result.equals(GameController.WIN) || result.equals(GameController.LOSE)) {
                jTextField2.setText("");

                if (result.equals(GameController.WIN)) {
                    jTextField2.setVisible(false);
                    jButton14.setVisible(false);
                    displayDialogue(core.loadDialogue(Dialogues.KID_WIN));
                    core.makeHappen(Event.MINIGAME);
                    addItem("Biscotto"); //Farlo fare a GameController
                } else if (result.equals(GameController.LOSE)) {
                    displayDialogue(core.loadDialogue(Dialogues.KID_LOSE));
                }

            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(jPanel1, "Errore! Inserire solo numeri.");
        }
    }//GEN-LAST:event_jButton14ActionPerformed

    //Custom methods
    private void setImage(String image, javax.swing.JLabel jLab) {
        jLab.setIcon(new ImageIcon(getClass().getResource("/" + image)));
    }

    private void startGame() {
        cl.show(jPanel1, "card4");
        jLabel1.setIcon(new ImageIcon(getClass().getResource("/" + core.getFacingImageFileName())));

        jButton7.setVisible(true);
        jComboBox1.setVisible(true);
        jButton9.setVisible(true);
        jButton5.setVisible(true);
    }

    private void displayDialogue(Couple<String, ListIterator> dl) {      
        dialogue = dl;

        if (dialogue.getSecond().hasNext()) {
            jLabel3.setText(dialogue.getFirst());
            jLabel4.setText("<html>" + dialogue.getSecond().next() + "</html>");

            if (dialogue.getFirst() != null) {
                jLabel3.setVisible(true);
            }
            jLabel4.setVisible(true);
        } else {
            jLabel3.setVisible(false);
            jLabel4.setVisible(false);
        }
    }

    private void addItem(String item) {
        core.addToInventory(item);
        jComboBox1.addItem(item);
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GameGUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameGUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameGUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameGUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex) {
            System.err.println(ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new GameGUI().setVisible(true);
            core = new GameController();
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private static javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private static javax.swing.JLabel jLabel3;
    private static javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLayeredPane jLayeredPane1;
    private static javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
