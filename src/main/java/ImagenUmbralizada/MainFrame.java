/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ImagenUmbralizada;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

/**
 *
 * @author jorge
 */
public class MainFrame extends javax.swing.JFrame {

    private JFileChooser fc;
    private File fichero;
    private  boolean resize = true;
    private FileNameExtensionFilter filter;
    private String wantToExit;
    private String exit;

    Mat imageVanilla = null;
   
    enum iteration {
        PREVIOUS, NEXT;
    }

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();

        fc = new JFileChooser();
        fc.addChoosableFileFilter(new FileNameExtensionFilter("Imágenes", "jpg", "jpeg", "png"));
        thresholdMenuItem.setEnabled(false);
        saveImageMenu.setEnabled(false);
        initialConfig();
    }

    private void initialConfig() {
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        fc = new JFileChooser();
        thresholdMenuItem.setEnabled(false);
        saveImageMenu.setEnabled(false);
        setInfo();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        escritorio = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openImageMenu = new javax.swing.JMenuItem();
        saveImageMenu = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        thresholdMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        aboutMenuItem = new javax.swing.JMenuItem();
        shortcutsMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        escritorio.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentRemoved(java.awt.event.ContainerEvent evt) {
                escritorioComponentRemoved(evt);
            }
        });
        escritorio.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                escritorioComponentResized(evt);
            }
        });

        jMenuBar1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                jMenuBar1ComponentResized(evt);
            }
        });

        fileMenu.setText("File");

        openImageMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        openImageMenu.setText("Open image");
        openImageMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openImageMenuActionPerformed(evt);
            }
        });
        fileMenu.add(openImageMenu);

        saveImageMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        saveImageMenu.setText("Save image");
        saveImageMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveImageMenuActionPerformed(evt);
            }
        });
        fileMenu.add(saveImageMenu);

        jMenuBar1.add(fileMenu);

        editMenu.setMnemonic('E');
        editMenu.setText("Edición");

        thresholdMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        thresholdMenuItem.setText("Modificar umbral");
        thresholdMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thresholdMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(thresholdMenuItem);

        jMenuBar1.add(editMenu);

        helpMenu.setMnemonic('H');
        helpMenu.setText("Ayuda");

        aboutMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, 0));
        aboutMenuItem.setText("Acerca de ...");
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(aboutMenuItem);

        shortcutsMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, 0));
        shortcutsMenuItem.setText("Atajos");
        shortcutsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shortcutsMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(shortcutsMenuItem);

        jMenuBar1.add(helpMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(escritorio, javax.swing.GroupLayout.DEFAULT_SIZE, 687, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(escritorio, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void openImageMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openImageMenuActionPerformed
        setFileChooser();
        int res = fc.showOpenDialog(null);
        if (res == JFileChooser.APPROVE_OPTION) {
            openImageActions(fc.getSelectedFile());
        }
    }//GEN-LAST:event_openImageMenuActionPerformed

    private void setInfo() {
        fileMenu.setText("Archivo");
        openImageMenu.setText("Abrir imagen");
        saveImageMenu.setText("Guardar imagen");
        editMenu.setText("Editar");
        thresholdMenuItem.setText("Umbralización");
        helpMenu.setText("Ayuda");
        wantToExit = "¿Desea salir?";
        exit = "Salir";

    }

    private void saveImageMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveImageMenuActionPerformed
        int option = fc.showSaveDialog(null);
        switch (option) {
            case JFileChooser.APPROVE_OPTION:
                File file = fc.getSelectedFile();
                BufferedImage image = lienzo.getImage();
                try {
                    ImageIO.write(image, "png", file);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(this, "Error al guardar la imagen");
                }
                break;

            case JFileChooser.CANCEL_OPTION:
                break;
            default:
                throw new AssertionError();
        }
    }//GEN-LAST:event_saveImageMenuActionPerformed

    private void escritorioComponentRemoved(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_escritorioComponentRemoved
        if (escritorio.getAllFrames().length < 1) {
            thresholdMenuItem.setEnabled(false);
            saveImageMenu.setEnabled(false);
        }
    }//GEN-LAST:event_escritorioComponentRemoved

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        int result = JOptionPane.showConfirmDialog(this, wantToExit, exit, JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        } else if (result == JOptionPane.NO_OPTION) {
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }

    }//GEN-LAST:event_formWindowClosing

    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
        new AboutFrame().setVisible(true);
    }//GEN-LAST:event_aboutMenuItemActionPerformed

    private void shortcutsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shortcutsMenuItemActionPerformed
        new ShortcutFrame().setVisible(true);
    }//GEN-LAST:event_shortcutsMenuItemActionPerformed

    private void thresholdMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thresholdMenuItemActionPerformed
        String res;
        int threshold = 0;
        boolean wrongFormat;
        boolean firstLoop = true;
        do {
            if (!firstLoop) {
                JOptionPane.showMessageDialog(this, "Por favor introduzca un umbral correcto");
            } else {
                firstLoop = false;
            }

            res = JOptionPane.showInputDialog(this, "Introduzca un umbral (número entre 0 y 255):");
            //If EXIT or CANCEL
            if (res == null) {
                return;
            }

            wrongFormat = false;
            try {
                threshold = Integer.parseInt(res);
            } catch (Exception e) {
                wrongFormat = true;
            }
        } while (threshold < 0 || 255 < threshold || wrongFormat);
        VentanaInterna vInterna = new VentanaInterna();
        escritorio.add(vInterna);
        vInterna.setTitle("Thresholdized (" + threshold + ") " + fichero.getName());
        vInterna.setVisible(true);
        lienzo = vInterna.getLienzo();
        lienzo.applyThreshold(threshold, imageVanilla, lienzo);

    }//GEN-LAST:event_thresholdMenuItemActionPerformed

    private void jMenuBar1ComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jMenuBar1ComponentResized
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuBar1ComponentResized

    private void escritorioComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_escritorioComponentResized
        JInternalFrame[] frames= escritorio.getAllFrames();
        for(JInternalFrame ventana: frames){
            if(escritorio.getWidth()<ventana.getX()|| escritorio.getHeight()<ventana.getY()){
                ventana.setLocation(0,0);
            }
        }
    }//GEN-LAST:event_escritorioComponentResized

    private void setFileChooser() {
        fc.setAcceptAllFileFilterUsed(false);
        filter = new FileNameExtensionFilter("Imágenes (png, jpg, jpeg, bmp)", "png", "jpg", "jpeg", "bmp");
        fc.addChoosableFileFilter(filter);
    }


    private void openImageActions(File file) {
        fichero = file;
        nu.pattern.OpenCV.loadShared();
        imageVanilla= Imgcodecs.imread(fichero.getAbsolutePath()); 
        if (escritorio.getAllFrames() != null) {
            escritorio.removeAll();
            escritorio.repaint();
        }
        VentanaInterna vInterna = new VentanaInterna();
        vInterna.setTitle(file.getName());
        escritorio.add(vInterna);
        Dimension dimension = lienzo.openImage(file, resize, vInterna.getLienzo());
        vInterna.setSize(dimension);
        lienzo = vInterna.getLienzo();
        saveImageMenu.setEnabled(true);
        vInterna.setVisible(true);
        thresholdMenuItem.setEnabled(true);
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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
    private ImagenUmbralizada.Lienzo lienzo;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenu editMenu;
    private javax.swing.JDesktopPane escritorio;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem openImageMenu;
    private javax.swing.JMenuItem saveImageMenu;
    private javax.swing.JMenuItem shortcutsMenuItem;
    private javax.swing.JMenuItem thresholdMenuItem;
    // End of variables declaration//GEN-END:variables

}
