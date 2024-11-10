/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess;

import java.util.LinkedList;

/**
 *
 * @author Yazid Soumanou
 */
public class Tour extends Piece {
    
    public Tour(int posx, int posy, boolean estBlanc, String nom, LinkedList<Piece> piece) {
        super(posx, posy, estBlanc, nom, piece);
    }
    @Override
     public void bouger(Piece piece, int startX, int startY,int posx,int posy)
     {
        
         
          if (Debordement(posx,posy) && MouvTour( this,  startX,  startY,  posx, posy))
          {
               super.bouger(this,startX,startY,posx, posy);
              //System.out.println((this.estBlanc?"blanc" :"noir")+" "+this.nom+" bouge");
              //System.out.println((this.estBlanc?"blanc" :"noir")+" "+this.posx+" "+this.posy);
          }
     
     }
      private boolean MouvTour(Tour tour, int startX, int startY, int endX, int endY) {
        // Vérifie que le mouvement est bien soit horizontal soit vertical
        if (startX != endX && startY != endY) {
            return false;
        }

        int xDirection = startX == endX ? 0 : (endX > startX ? 1*Math.abs(endX - startX) : -1*Math.abs(endX - startX));
        int yDirection = startY == endY ? 0 : (endY > startY ? 1*Math.abs(endY - startY) : -1*Math.abs(endY - startY));

        int x = startX + (xDirection != 0 ? xDirection/Math.abs(endX - startX): xDirection );
        int y = startY + (yDirection != 0 ? yDirection/Math.abs(endY - startY): yDirection );
         
        // Vérifie que toutes les cases sur le chemin sont vides
        while (x != endX || y != endY) {
            if (Echiquier.getPiece(x*64, y*64) != null) {
                return false;
            }
             x += startX == endX ? 0 : (endX > startX ? 1 : -1);
             y += startY == endY ? 0 : (endY > startY ? 1 : -1);
        }

        // Vérifie la case de destination
        Piece destinationPion = Echiquier.getPiece(endX*64, endY*64);
        if (destinationPion == null || destinationPion.estBlanc != tour.estBlanc) {
            return true;
        }

        return false;
    }
   
}