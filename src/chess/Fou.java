/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;
import javax.swing.JPanel;

/**
 *
 * @author Yazid Soumanou
 */
public class Fou extends Piece {
    
    public Fou(int posx, int posy, boolean estBlanc,boolean bouger, String nom, LinkedList<Piece> piece) {
        super(posx, posy, estBlanc,bouger, nom, piece);
    }
    @Override
     public void bouger(Piece piece, int startX, int startY,int posx,int posy)
     {
       
         //System.out.println((this.estBlanc?"blanc" :"noir")+" "+this.posx+" "+this.posy);
          if (Debordement(posx,posy) && MouvFou(this,  startX,  startY,  posx, posy))
          {
              super.bouger(this,startX,startY,posx, posy);
              
          }
     
     }
    private boolean MouvFou(Fou fou, int startX, int startY, int endX, int endY) {
        
        // Vérifie que le mouvement est bien en diagonale
        if (Math.abs(endX - startX) != Math.abs(endY - startY)) {
            return false;
        }

        int xDirection = (endX - startX) > 0 ? 1*Math.abs(endX - startX) : -1*Math.abs(endX - startX);
        int yDirection = (endY - startY) > 0 ? 1*Math.abs(endY - startY) : -1*Math.abs(endY - startY); 

        int x = startX + ((endX - startX) > 0 ? 1 : -1);
        int y = startY + ((endY - startY) > 0 ? 1 : -1);
       
        // Vérifie que toutes les cases sur le chemin sont vides
        while (x != endX && y != endY) {
 
            if (Echiquier.getPiece(x*64, y*64) != null) {
                
                return false;
            }
            x += ((endX - startX) > 0 ? 1 : -1);
            y += ((endY - startY) > 0 ? 1 : -1);
            
        }

        // Vérifie la case de destination
        Piece destinationPion = Echiquier.getPiece(endX*64, endY*64);
        if (destinationPion == null || destinationPion.estBlanc != fou.estBlanc) {
            return true;
        }
        return false;
}
public JPanel cheminFou(Fou fou, int startX, int startY) {
    JPanel pn = new JPanel();
    int startx = startX / 64;
    int starty = startY / 64;

    pn = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.DARK_GRAY);
            g2d.setStroke(new BasicStroke(2));
            
            // Diagonale Haut-Gauche
            for (int i = 1; startx - i >= 0 && starty - i >= 0; i++) {
                if (Echiquier.getPiece((startx - i) * 64, (starty - i) * 64) == null) {
                    g2d.drawRect((startx - i) * 64, (starty - i) * 64, 64, 64);
                } else {
                    if (Echiquier.getPiece((startx - i) * 64, (starty - i) * 64).estBlanc != fou.estBlanc) {
                        g2d.setColor(Color.YELLOW);
                        g2d.drawRect((startx - i) * 64, (starty - i) * 64, 64, 64);
                    }
                    break;
                }
            }
             g2d.setColor(Color.DARK_GRAY);
            // Diagonale Haut-Droite
            for (int i = 1; startx + i <= 7 && starty - i >= 0; i++) {
                if (Echiquier.getPiece((startx + i) * 64, (starty - i) * 64) == null) {
                    g2d.drawRect((startx + i) * 64, (starty - i) * 64, 64, 64);
                } else {
                    if (Echiquier.getPiece((startx + i) * 64, (starty - i) * 64).estBlanc != fou.estBlanc) {
                        g2d.setColor(Color.YELLOW);
                        g2d.drawRect((startx + i) * 64, (starty - i) * 64, 64, 64);
                    }
                    break;
                }
            }
             g2d.setColor(Color.DARK_GRAY);
            // Diagonale Bas-Gauche
            for (int i = 1; startx - i >= 0 && starty + i <= 7; i++) {
                if (Echiquier.getPiece((startx - i) * 64, (starty + i) * 64) == null) {
                    g2d.drawRect((startx - i) * 64, (starty + i) * 64, 64, 64);
                } else {
                    if (Echiquier.getPiece((startx - i) * 64, (starty + i) * 64).estBlanc != fou.estBlanc) {
                        g2d.setColor(Color.YELLOW);
                        g2d.drawRect((startx - i) * 64, (starty + i) * 64, 64, 64);
                    }
                    break;
                }
            }
             g2d.setColor(Color.DARK_GRAY);
            // Diagonale Bas-Droite
            for (int i = 1; startx + i <= 7 && starty + i <= 7; i++) {
                if (Echiquier.getPiece((startx + i) * 64, (starty + i) * 64) == null) {
                    g2d.drawRect((startx + i) * 64, (starty + i) * 64, 64, 64);
                } else {
                    if (Echiquier.getPiece((startx + i) * 64, (starty + i) * 64).estBlanc != fou.estBlanc) {
                        g2d.setColor(Color.YELLOW);
                        g2d.drawRect((startx + i) * 64, (starty + i) * 64, 64, 64);
                    }
                    break;
                }
            }
        }
    };

    pn.setBounds(0, 0, 512, 512); // Ajustez cette taille en fonction de votre échiquier
    pn.setOpaque(false);
    return pn;
}

  
}
