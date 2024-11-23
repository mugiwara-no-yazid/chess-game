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
public class Roi extends Piece {
    
   public Roi(int posx, int posy, boolean estBlanc,boolean bouger, String nom, LinkedList<Piece> piece) {
        super(posx, posy, estBlanc,bouger, nom, piece);
    }
    @Override
     public void bouger(Piece piece, int startX, int startY,int posx,int posy)
     {
         
         //System.out.println((this.estBlanc?"blanc" :"noir")+" "+this.posx+" "+this.posy);
          if (Debordement(posx,posy) && MouvRoi( this,  startX,  startY,  posx, posy))
          {
              super.bouger(this,startX,startY,posx, posy);
              //System.out.println((this.estBlanc?"blanc" :"noir")+" "+this.nom+" bouge");
              //System.out.println((this.estBlanc?"blanc" :"noir")+" "+this.posx+" "+this.posy);
          }
     
     }
    private boolean MouvRoi(Roi roi, int startX, int startY, int endX, int endY) {
    // Vérifier que le mouvement est d'une case dans n'importe quelle direction
    int deltaX = Math.abs(endX - startX);
    int deltaY = Math.abs(endY - startY);
    
    if (deltaX <= 1 && deltaY <= 1) {
        Piece destinationPion = Echiquier.getPiece(endX, endY);
        // Vérifier si la case de destination est vide ou contient une pièce d'une couleur opposée
        if (destinationPion == null || destinationPion.estBlanc != roi.estBlanc) {
            return true;
        }
    }
    
    return false;
}
   public JPanel cheminRoi(Roi roi, int startX, int startY) {
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

            int[][] directions = {
                {-1, -1}, {-1, 0}, {-1, 1},
                {0, -1},         {0, 1},
                {1, -1}, {1, 0}, {1, 1}
            };

            for (int[] direction : directions) {
                int newX = startx + direction[0];
                int newY = starty + direction[1];

                if (newX >= 0 && newX <= 7 && newY >= 0 && newY <= 7) {
                    Piece piece = Echiquier.getPiece(newX * 64, newY * 64);
                    if (piece == null || piece.estBlanc != roi.estBlanc) {
                        if (piece != null && piece.estBlanc != roi.estBlanc) {
                            g2d.setColor(Color.YELLOW);
                        } else {
                            g2d.setColor(Color.DARK_GRAY);
                        }
                        g2d.drawRect(newX * 64, newY * 64, 64, 64);
                    }
                }
            }
        }
    };

    pn.setBounds(0, 0, 512, 512); // Ajustez cette taille en fonction de votre échiquier
    pn.setOpaque(false);
    return pn;
}

}
