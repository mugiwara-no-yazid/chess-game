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
public class Pion extends Piece {
    //private final static int MOUVEMENT ={8};
    
    public Pion(int posx, int posy, boolean estBlanc, String nom, LinkedList<Piece> piece) {
        super(posx, posy, estBlanc, nom, piece);
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
           if (Echiquier.getPiece(startX*64, (startY + direction)*64    ) == null && destinationPion == null) {
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

}
