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
public class Reine extends Piece {
    
    public Reine(int posx, int posy, boolean estBlanc, String nom, LinkedList<Piece> piece) {
        super(posx, posy, estBlanc, nom, piece);
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

}
