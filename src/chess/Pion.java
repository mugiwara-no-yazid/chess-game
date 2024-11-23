/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Yazid Soumanou
 */
public class Pion extends Piece {
    //private final static int MOUVEMENT ={8};
    
    public Pion(int posx, int posy, boolean estBlanc,boolean bouger, String nom, LinkedList<Piece> piece) {
        super(posx, posy, estBlanc,bouger, nom, piece);
    }
    @Override
     public void bouger(Piece piece, int startX, int startY,int posx,int posy)
     {
         
         
         //System.out.println((this.estBlanc?"blanc" :"noir")+" "+this.posx+" "+this.posy);
          if (Debordement(posx,posy) && MouvPion( this,  startX,  startY,  posx, posy))
          {
              super.bouger(this,startX,startY,posx, posy);
              //System.out.println((this.estBlanc?"blanc" :"noir")+" "+this.nom+" bouge");
              //System.out.println((this.estBlanc?"blanc" :"noir")+" "+this.posx+" "+this.posy);
          }
     
     }
      private boolean MouvPion(Pion pion, int startX, int startY, int endX, int endY) {
        int direction = pion.estBlanc == true ? -1 : 1;
        Piece destinationPion = Echiquier.getPiece(endX*64, endY*64);
       
        // Mouvement d'une case en avant
        if (endY == startY + direction && endX == startX && destinationPion == null) {
            return true;
        }
        

        // Mouvement de deux cases en avant depuis la position initiale
        if (startY == (pion.estBlanc == true ? 6 : 1) && endY == startY + 2 * direction && endX == startX) {
           if (Echiquier.getPiece(startX*64, (startY + direction)*64) == null && destinationPion == null) {
            return true;
        }

        }

        // Capture en diagonale
        if (endY == startY + direction && Math.abs(endX - startX) == 1 ) {
           
        // Vérifier si la case contient un pion d'une couleur opposée
        if (destinationPion != null && destinationPion.estBlanc != pion.estBlanc) {
            return true;
        }
        }
        
        return false;
    }   
    
    
    public JPanel cheminPion(Pion pion, int startX, int startY) {
    JPanel pn = new JPanel();
    int startx=startX/64;
    int starty=startY/64;
   
    int direction = pion.estBlanc == true ? -1 : 1;
         pn = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(Color.DARK_GRAY);
                
                if (startY / 64 == (pion.estBlanc == true ? 6 : 1) &&(Echiquier.getPiece(startx*64, (starty + direction)*64) == null && Echiquier.getPiece(startx*64, (starty + direction*2)*64) == null)) {
                    g2d.setStroke(new BasicStroke(2));
                    g2d.drawRect((startx*64), (starty*64), 64, 64);
                    g2d.drawRect((startx*64), (starty*64)+direction*64, 64, 64);
                    g2d.drawRect((startx*64), (starty*64)+direction*64*2, 64, 64);                   
            }else if(Echiquier.getPiece(startx*64, (starty + direction)*64) == null)
            {
                g2d.setStroke(new BasicStroke(2));
                g2d.drawRect((startx*64), (starty*64), 64, 64);
                g2d.drawRect((startx*64), (starty*64)+direction*64, 64, 64);
            }
            if(Echiquier.getPiece((startx + direction)*64, (starty + direction)*64) != null && (pion.estBlanc != Echiquier.getPiece((startx + direction)*64, (starty + direction)*64).estBlanc))
            {
                g2d.setColor(Color.yellow);
                 g2d.setStroke(new BasicStroke(2));
                g2d.drawRect((startx*64), (starty*64), 64, 64);           
                g2d.drawRect((startx + direction)*64, (starty*64)+direction*64, 64, 64);
            }
            if(Echiquier.getPiece((startx - direction)*64, (starty + direction)*64) != null && (pion.estBlanc != Echiquier.getPiece((startx - direction)*64, (starty + direction)*64).estBlanc))
            {
                g2d.setColor(Color.yellow);
                g2d.setStroke(new BasicStroke(2));
                g2d.drawRect((startx*64), (starty*64), 64, 64);           
                g2d.drawRect((startx - direction)*64, (starty*64)+direction*64, 64, 64);
            }
            else
            {
                g2d.drawRect((startx*64), (starty*64), 64, 64);
            }
            }
        };

        pn.setBounds(0, 0, 512, 512); // Ajustez cette taille en fonction de votre échiquier
        pn.setOpaque(false);
    return pn;
}
    
 }
