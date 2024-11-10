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
public class Chevalier extends Piece {
    
    public Chevalier(int posx, int posy, boolean estBlanc, String nom, LinkedList<Piece> piece) {
        super(posx, posy, estBlanc, nom, piece);
    }
    @Override
     public void bouger(Piece piece, int startX, int startY,int posx,int posy)
     {
        
         //System.out.println((this.estBlanc?"blanc" :"noir")+" "+this.posx+" "+this.posy);
          if (Debordement(posx,posy) && MouvChevalier( this,  startX,  startY,  posx, posy))
          {
              super.bouger(this,startX,startY,posx, posy);
              //System.out.println((this.estBlanc?"blanc" :"noir")+" "+this.nom+" bouge");
              //System.out.println((this.estBlanc?"blanc" :"noir")+" "+this.posx+" "+this.posy);
          }
     
     }
      private boolean MouvChevalier(Chevalier chevalier, int startX, int startY, int endX, int endY) {
    // Définir les mouvements possibles en "L" du chevalier
        int[][] mouvements = {
            {2, 1}, {2, -1}, {-2, 1}, {-2, -1},
            {1, 2}, {1, -2}, {-1, 2}, {-1, -2}
        };

        // Vérifier si le mouvement correspond à l'un des mouvements possibles
        for (int[] mouvement : mouvements) {
            int newX = startX + mouvement[0];
            int newY = startY + mouvement[1];
            if (newX == endX && newY == endY) {
                Piece destinationPion = Echiquier.getPiece(endX*64, endY*64);
                // Vérifier si la case de destination est vide ou contient une pièce d'une couleur opposée
                if (destinationPion == null || destinationPion.estBlanc != chevalier.estBlanc) {
                    return true;
                }
            }
        }

        return false;
    }
   
}
