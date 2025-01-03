/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;

/**
 *
 * @author Yazid Soumanou
 */
public class Echiquier {
    public static final int TAILLE_GRILLE = 8;
    public static LinkedList <Piece> piece=new  LinkedList();
    public static Piece pieceSelectionner = null;
    public static boolean peutBouger =true;
    private int startX =0;
    private int startY=0;
    private JLayeredPane layeredPane;

    public void dessiner() throws IOException
    {
            JFrame fenetre = new JFrame("Echec et mat");
            fenetre.setBounds(50,50,512,512);
            fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            fenetre.setUndecorated(true);
            layeredPane = new JLayeredPane();
            layeredPane.setPreferredSize(new Dimension(512, 512));
            fenetre.add(layeredPane);
        
            BufferedImage tous=ImageIO.read(new File("C:\\Users\\Yazid Soumanou\\Desktop\\Chess\\chess.png"));
            Image imgs[]=new Image[12];
            int ind=0;
            for(int y=0;y<400;y+=200){
                for(int x=0;x<1200;x+=200){
                    imgs[ind]=tous.getSubimage(x, y, 200, 200).getScaledInstance(64, 64, BufferedImage.SCALE_SMOOTH);
                    ind++;
            }    }
            
                  initialiserPieces();
                  
            JPanel pn = new JPanel(){
                @Override
                public void paint(Graphics g) {
                    boolean blanc =true;
                    for(int x =0 ;x<TAILLE_GRILLE;x++)
                    {
                        for(int y=0;y<TAILLE_GRILLE;y++)
                        {
                            if (blanc)
                            {
                                g.setColor(Color.white.darker());
                            }
                            else
                            {
                                g.setColor(new Color(115,145,65));
                            }
                            g.fillRect(x*64, y*64, 64, 64);
                         blanc = !blanc;
                        }
                        blanc = !blanc;
                    }
                    
                    for(Piece p: piece){
                    int ind=0;
                    if(p.nom.equalsIgnoreCase("roi")){
                        ind=0;
                    }
                    if(p.nom.equalsIgnoreCase("reine")){
                        ind=1;
                    }
                    if(p.nom.equalsIgnoreCase("fou")){
                        ind=2;
                    }
                    if(p.nom.equalsIgnoreCase("chevalier")){
                        ind=3;
                    }
                    if(p.nom.equalsIgnoreCase("tour")){
                        ind=4;
                    }
                    if(p.nom.equalsIgnoreCase("pion")){
                        ind=5;
                    }
                    if(!p.estBlanc){
                        ind+=6;
                    }
                    g.drawImage(imgs[ind], p.piecex, p.piecey, this);
                }

                }
                
            };
            pn.setBounds(0, 0, 512, 512);
            layeredPane.add(pn, JLayeredPane.DEFAULT_LAYER);
            
            
            fenetre.addMouseListener( new MouseListener()
            {
                @Override
                   public void mouseClicked(MouseEvent e) {
                int x = e.getX() ;
                int y = e.getY() ;
                //System.out.println("valeur de peut bouger : "+peutBouger);
                
                if (pieceSelectionner == null) {
                    if(getPiece(x, y)!=null){
            pieceSelectionner = getPiece(x, y);
            JPanel pn2 = pieceSelectionner.chemin(pieceSelectionner, startX, startY);
            pn2.setBounds(0, 0, fenetre.getWidth(), fenetre.getHeight());
            layeredPane.add(pn2, JLayeredPane.PALETTE_LAYER);
            layeredPane.revalidate();
                    }
            //fenetre.repaint();
                        }

               /* else if(pieceSelectionner != null && (pieceSelectionner.posx!=x/64 || pieceSelectionner.posy!=y/64) )
                {
                    if (getPiece(x, y) ==null)
                    {
                        return;
                    }
                    else
                    {
                         pieceSelectionner = getPiece(x, y);
                         System.out.println("x = "+x/64+" pos x = "+pieceSelectionner.nom);
                    }
                   
                } */
                else {
                    //System.out.println("startX = "+startX/64+" startY = "+startY/64);
                    //System.out.println("X = "+x/64+" Y = "+y/64);
                   //System.out.println(pieceSelectionner.nom+" "+(pieceSelectionner.estBlanc?"blanc":"noir")+" peut bouger avant deplacement :"+pieceSelectionner.bouger);
                    deplacerPiece(pieceSelectionner,startX,startY, x, y);
                   //System.out.println(pieceSelectionner.nom+" "+(pieceSelectionner.estBlanc?"blanc":"noir")+" peut bouger apres deplacement :"+pieceSelectionner.bouger);
                   if (pieceSelectionner.nom == "pion")
                   {
                       if(pieceSelectionner.estBlanc && pieceSelectionner.posy == 0)
                       {
                            Pion p = (Pion) pieceSelectionner;
                            piece.remove(pieceSelectionner);
                            pieceSelectionner = promotion(fenetre,pieceSelectionner);
                            piece.add(pieceSelectionner);
                           
                       }
                       else if(pieceSelectionner.estBlanc ==false && pieceSelectionner.posy == 7)
                       {
                            Pion p = (Pion) pieceSelectionner;
                            piece.remove(pieceSelectionner);
                            pieceSelectionner = promotion(fenetre,pieceSelectionner);
                            piece.add(pieceSelectionner);
                       }
                   }
                   pieceSelectionner = null;
                    viderCouchesSupplementaires();
                    fenetre.repaint();
                    
                }
                
            }


                @Override
                public void mousePressed(MouseEvent e) {
                    //System.out.println((getPiece(e.getX(),e.getY()).estBlanc?"Blanc ":"Noir ") + getPiece(e.getX(),e.getY()).nom);
                    //pieceSelectionner=getPiece(e.getX(),e.getY());
                    if (pieceSelectionner == null) {
                    startX =e.getX();
                    startY=e.getY();
                }
                    
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                   //pieceSelectionner.bouger(e.getX()/64,e.getY()/64);
                   //fenetre.repaint();
                  
                }

                @Override
                public void mouseEntered(MouseEvent e) {
               }

                @Override
                public void mouseExited(MouseEvent e) {
               }
            }
            );
            
            fenetre.addMouseMotionListener(new MouseMotionListener()
            {
                @Override
                public void mouseDragged(MouseEvent e) {}

                @Override
                public void mouseMoved(MouseEvent e) {
                }
            }
            );
            fenetre.setVisible(true);
    }
    
    public static Piece getPiece(int x, int y)
    {
        
        int posx=x/64; 
        int posy=y/64;

        for (Piece p:piece)
        {
            if(posx == p.posx && p.posy == posy)
            {
                return p;
            }
        }
        return null;
    }
     
       public static void deplacerPiece(Piece piece,int startX,int startY, int x, int y) {
        // Ajoutez ici la logique pour vérifier si le mouvement est valide pour le type de pièce
        piece.bouger(piece,startX/64,startY/64,x/64, y/64);
        //bouger(Piece piece, int startX, int startY,int posx,int posy)
    }
     private void initialiserPieces() {
         Tour nTour=new Tour(0, 0, false,!peutBouger, "tour", piece);
            Chevalier nChevalier=new Chevalier(1, 0, false,!peutBouger, "chevalier", piece);
            Fou nFou=new Fou(2, 0, false,!peutBouger, "fou", piece);
            Reine nReine=new Reine(3, 0, false,!peutBouger, "reine", piece);
            Roi nRoi=new Roi(4, 0, false,!peutBouger, "roi", piece);
            Fou nFou2=new Fou(5, 0, false,!peutBouger, "fou", piece);
            Chevalier nChevalier2= new Chevalier(6, 0, false,!peutBouger, "chevalier", piece);
            Tour nTour2=new Tour(7, 0, false,!peutBouger, "tour", piece);
            Pion nPion1=new Pion(1, 1, false, !peutBouger,"pion", piece);
            Pion nPion2=new Pion(2, 1, false, !peutBouger,"pion", piece);
            Pion nPion3=new Pion(3, 1, false,!peutBouger, "pion", piece);
            Pion nPion4=new Pion(4, 1, false,!peutBouger, "pion", piece);
            Pion nPion5=new Pion(5, 1, false,!peutBouger, "pion", piece);
            Pion nPion6=new Pion(6, 1, false,!peutBouger, "pion", piece);
            Pion nPion7=new Pion(7, 1, false,!peutBouger, "pion", piece);
            Pion nPion8=new Pion(0, 1, false,!peutBouger,"pion", piece);

            
            Tour bTour=new Tour(0, 7, true, peutBouger,"tour", piece);
            Chevalier bChevalier=new Chevalier(1, 7, true, peutBouger,"chevalier", piece);
            Fou bFou=new Fou(2, 7, true,peutBouger, "fou", piece);
            Reine bReine=new Reine(3, 7, true, peutBouger,"reine", piece);
            Roi bRoi=new Roi(4, 7, true,peutBouger, "roi", piece);
            Fou bFou2=new Fou(5, 7, true,peutBouger, "fou", piece);
            Chevalier bChevalier2=new Chevalier(6, 7, true, peutBouger,"chevalier", piece);
            Tour bTour2=new Tour(7, 7, true,peutBouger, "tour", piece);
            Pion bPion1=new Pion(1, 6, true,peutBouger, "pion", piece);
            Pion bPion2=new Pion(2, 6, true,peutBouger, "pion", piece);
            Pion bPion3=new Pion(3, 6, true, peutBouger,"pion", piece);
            Pion bPion4=new Pion(4, 6, true, peutBouger,"pion", piece);
            Pion bPion5=new Pion(5, 6, true, peutBouger,"pion", piece);
            Pion bPion6=new Pion(6, 6, true, peutBouger,"pion", piece);
            Pion bPion7=new Pion(7, 6, true, peutBouger,"pion", piece);
            Pion bPion8=new Pion(0, 6, true,peutBouger, "pion", piece);
            
    }
      private void viderCouchesSupplementaires() {
        for (Component comp : layeredPane.getComponentsInLayer(JLayeredPane.PALETTE_LAYER)) {
            layeredPane.remove(comp);
        }
        layeredPane.revalidate();
        layeredPane.repaint();
    }
public static void mettreAJourPeutBouger() {
        for (Piece p : piece) {
            if(p.bouger)
            {
                p.bouger =false;
            }
            else
            {
            p.bouger = true;
            }
            
        }
    }

private Piece promotion(JFrame parent,Piece p) {
        JDialog choixDialog = new JDialog(parent, "Choix de Promotion", true);
        //choixDialog.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
        choixDialog.setSize(300, 200);
        choixDialog.setLayout(new FlowLayout());
        final Piece[] piece = new Piece[1];
        JLabel label = new JLabel("Choisissez une pièce pour la promotion:");
        choixDialog.add(label);

        JButton boutonReine = new JButton("Reine");
        boutonReine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logique pour la promotion en Reine
                System.out.println("Promotion en Reine choisie");
                piece[0] = new Reine (p.posx,p.posy,p.estBlanc,p.bouger,"reine",p.piece);
                choixDialog.dispose(); // Fermer le dialogue après choix
            }
        });
        choixDialog.add(boutonReine);

        JButton boutonTour = new JButton("Tour");
        boutonTour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logique pour la promotion en Tour
                System.out.println("Promotion en Tour choisie");
                piece[0] = new Tour (p.posx,p.posy,p.estBlanc,p.bouger,"tour",p.piece);
                choixDialog.dispose(); // Fermer le dialogue après choix
            }
        });
        choixDialog.add(boutonTour);

        JButton boutonFou = new JButton("Fou");
        boutonFou.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logique pour la promotion en Fou
                System.out.println("Promotion en Fou choisie");
                piece[0] = new Fou (p.posx,p.posy,p.estBlanc,p.bouger,"fou",p.piece);
                choixDialog.dispose(); // Fermer le dialogue après choix
            }
        });
        choixDialog.add(boutonFou);

        JButton boutonCavalier = new JButton("Cavalier");
        boutonCavalier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logique pour la promotion en Cavalier
                System.out.println("Promotion en Cavalier choisie");
                piece[0] = new Chevalier (p.posx,p.posy,p.estBlanc,p.bouger,"chevalier",p.piece);
                choixDialog.dispose(); // Fermer le dialogue après choix
            }
        });
        choixDialog.add(boutonCavalier);

        choixDialog.setLocationRelativeTo(parent); // Centrer par rapport à la fenêtre parent
        choixDialog.setVisible(true);
        return piece[0];
    }
}
