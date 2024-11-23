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
public class Reine extends Piece {
    
    public Reine(int posx, int posy, boolean estBlanc,boolean bouger, String nom, LinkedList<Piece> piece) {
        super(posx, posy, estBlanc,bouger, nom, piece);
    }
    @Override
     public void bouger(Piece piece, int startX, int startY,int posx,int posy)
     {
         
         //System.out.println((this.estBlanc?"blanc" :"noir")+" "+this.posx+" "+this.posy);
          if (Debordement(posx,posy) && MouvReine( this,  startX,  startY,  posx, posy))
          {
              super.bouger(this,startX,startY,posx, posy);
              //System.out.println((this.estBlanc?"blanc" :"noir")+" "+this.nom+" bouge");
              System.out.println((this.estBlanc?"blanc" :"noir")+" "+this.posx+" "+this.posy);
          }
     
     }
      private boolean MouvReine(Reine reine, int startX, int startY, int endX, int endY) {
    // Vérifie que le mouvement est bien soit horizontal, vertical ou diagonal
    int deltaX = Math.abs(endX - startX);
    int deltaY = Math.abs(endY - startY);
    
    if (deltaX != deltaY && startX != endX && startY != endY) {
        return false;
    }

    int xDirection = Integer.signum(endX - startX);
    int yDirection = Integer.signum(endY - startY);

    int x = startX + xDirection;
    int y = startY + yDirection;

    // Vérifie que toutes les cases sur le chemin sont vides
    while (x != endX || y != endY) {
        if (Echiquier.getPiece(x*64, y*64) != null) {
            return false;
        }
        x += xDirection;
        y += yDirection;
    }

    // Vérifie la case de destination
    Piece destinationPion = Echiquier.getPiece(endX*64, endY*64);
    if (destinationPion == null || destinationPion.estBlanc != reine.estBlanc) {
        return true;
    }

    return false;
}
      public JPanel cheminReine(Reine reine, int startX, int startY) {
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

            // Diagonales (comme le fou)
            // Diagonale Haut-Gauche
            for (int i = 1; startx - i >= 0 && starty - i >= 0; i++) {
                if (Echiquier.getPiece((startx - i) * 64, (starty - i) * 64) == null) {
                    g2d.drawRect((startx - i) * 64, (starty - i) * 64, 64, 64);
                } else {
                    if (Echiquier.getPiece((startx - i) * 64, (starty - i) * 64).estBlanc != reine.estBlanc) {
                        g2d.setColor(Color.YELLOW);
                        g2d.drawRect((startx - i) * 64, (starty - i) * 64, 64, 64);
                    }
                    break;
                }
            }

            // Diagonale Haut-Droite
            g2d.setColor(Color.DARK_GRAY);
            for (int i = 1; startx + i <= 7 && starty - i >= 0; i++) {
                if (Echiquier.getPiece((startx + i) * 64, (starty - i) * 64) == null) {
                    g2d.drawRect((startx + i) * 64, (starty - i) * 64, 64, 64);
                } else {
                    if (Echiquier.getPiece((startx + i) * 64, (starty - i) * 64).estBlanc != reine.estBlanc) {
                        g2d.setColor(Color.YELLOW);
                        g2d.drawRect((startx + i) * 64, (starty - i) * 64, 64, 64);
                    }
                    break;
                }
            }

            // Diagonale Bas-Gauche
            g2d.setColor(Color.DARK_GRAY);
            for (int i = 1; startx - i >= 0 && starty + i <= 7; i++) {
                if (Echiquier.getPiece((startx - i) * 64, (starty + i) * 64) == null) {
                    g2d.drawRect((startx - i) * 64, (starty + i) * 64, 64, 64);
                } else {
                    if (Echiquier.getPiece((startx - i) * 64, (starty + i) * 64).estBlanc != reine.estBlanc) {
                        g2d.setColor(Color.YELLOW);
                        g2d.drawRect((startx - i) * 64, (starty + i) * 64, 64, 64);
                    }
                    break;
                }
            }

            // Diagonale Bas-Droite
            g2d.setColor(Color.DARK_GRAY);
            for (int i = 1; startx + i <= 7 && starty + i <= 7; i++) {
                if (Echiquier.getPiece((startx + i) * 64, (starty + i) * 64) == null) {
                    g2d.drawRect((startx + i) * 64, (starty + i) * 64, 64, 64);
                } else {
                    if (Echiquier.getPiece((startx + i) * 64, (starty + i) * 64).estBlanc != reine.estBlanc) {
                        g2d.setColor(Color.YELLOW);
                        g2d.drawRect((startx + i) * 64, (starty + i) * 64, 64, 64);
                    }
                    break;
                }
            }

            // Horizontales et Verticales (comme la tour)
            // Ligne en haut
            g2d.setColor(Color.DARK_GRAY);
            for (int i = 1; starty - i >= 0; i++) {
                if (Echiquier.getPiece(startx * 64, (starty - i) * 64) == null) {
                    g2d.drawRect(startx * 64, (starty - i) * 64, 64, 64);
                } else {
                    if (Echiquier.getPiece(startx * 64, (starty - i) * 64).estBlanc != reine.estBlanc) {
                        g2d.setColor(Color.YELLOW);
                        g2d.drawRect(startx * 64, (starty - i) * 64, 64, 64);
                    }
                    break;
                }
            }

            // Ligne en bas
            g2d.setColor(Color.DARK_GRAY);
            for (int i = 1; starty + i <= 7; i++) {
                if (Echiquier.getPiece(startx * 64, (starty + i) * 64) == null) {
                    g2d.drawRect(startx * 64, (starty + i) * 64, 64, 64);
                } else {
                    if (Echiquier.getPiece(startx * 64, (starty + i) * 64).estBlanc != reine.estBlanc) {
                        g2d.setColor(Color.YELLOW);
                        g2d.drawRect(startx * 64, (starty + i) * 64, 64, 64);
                    }
                    break;
                }
            }

            // Ligne à gauche
            g2d.setColor(Color.DARK_GRAY);

            for (int i = 1; startx - i >= 0; i++) {
                if (Echiquier.getPiece((startx - i) * 64, starty * 64) == null) {
                    g2d.drawRect((startx - i) * 64, starty * 64, 64, 64);
                } else {
                    if (Echiquier.getPiece((startx - i) * 64, starty * 64).estBlanc != reine.estBlanc) {
                        g2d.setColor(Color.YELLOW);
                        g2d.drawRect((startx - i) * 64, starty * 64, 64, 64);
                    }
                    break;
                }
            }

            // Ligne à droite
            g2d.setColor(Color.DARK_GRAY);
            for (int i = 1; startx + i <= 7; i++) {
                if (Echiquier.getPiece((startx + i) * 64, starty * 64) == null) {
                    g2d.drawRect((startx + i) * 64, starty * 64, 64, 64);
                } else {
                    if (Echiquier.getPiece((startx + i) * 64, starty * 64).estBlanc != reine.estBlanc) {
                        g2d.setColor(Color.YELLOW);
                        g2d.drawRect((startx + i) * 64, starty * 64, 64, 64);
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
