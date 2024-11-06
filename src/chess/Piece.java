/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess;

import java.util.*;

/**
 *
 * @author Yazid Soumanou
 */
public class Piece {
    int posx;
    int posy;
    int piecex;
    int piecey;
    String nom;
    boolean estBlanc;
    LinkedList <Piece> piece;

    public Piece(int posx, int posy, boolean estBlanc, String nom, LinkedList<Piece> piece) {
        this.posx = posx;
        this.posy = posy;
        this.piecex = posx*64;
        this.piecey = posy*64; 
        this.estBlanc = estBlanc;
        this.nom = nom;
        this.piece = piece;
        piece.add(this);
    }
    
    public void bouger(int posx,int posy){
        if(Echiquier.getPiece(posx*64, posy*64)!=null){
            if(Echiquier.getPiece(posx*64, posy*64).estBlanc!=estBlanc)
            {
                Echiquier.getPiece(posx*64, posy*64).tuerpiece();   
            }else
            {
                this.piecex=this.posx*64;
                this.piecey=this.posy*64;
                return;
            } }
        this.posx=posx;
        this.posy=posy;
        this.piecex=posx*64;
        this.piecey=posy*64;
    }
    public void tuerpiece()
    {
        piece.remove(this);
    }
}
