//Não consegui fazer o redimensionamento
import figures.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.io.*;
class main {
    public static void main (String[] args) {
        Janela frame = new Janela();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}



class Janela extends JFrame{
    ArrayList<Figure> figs = new ArrayList<Figure>();
    Figure focus = null;
    ArrayList<Button> buts = new ArrayList<Button>();
    Button focus_but = null;
    public Point ponto = null;
    private Graphics dbg;
    private Image dbImage;
    private int idFigureToCreate = 0;

    Janela() {
        try {
            FileInputStream file = new FileInputStream("proj.bin");
            ObjectInputStream object = new ObjectInputStream(file);
            this.figs = (ArrayList<Figure>) object.readObject();
            object.close();
        }catch (Exception e) {
            System.out.print(e);
        }

        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing(WindowEvent windowEvent) {
                    try {
                        FileOutputStream file = new FileOutputStream("proj.bin");
                        ObjectOutputStream object = new ObjectOutputStream(file);
                        object.writeObject(figs);
                        object.flush();
                        object.close();
                    }catch (Exception e) {
                    System.out.print(e);
                }
              System.exit(0);
            }
        });
        
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );

        //Deixa um figura com foco
        this.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                Point ponto = getMousePosition(); 
                verificaFocus(e);
                for(Button but: buts){
                    if(but.clicked(e.getX(),e.getY())){
                        if(but.idx == 1){
                            figs.add(new rect(200,200,50,50,Color.BLACK,Color.YELLOW));
                        }else{
                            figs.add(new elipse(100,100,50,50,Color.BLACK,Color.YELLOW));
                        }
                        repaint();
                    }
                }
            }
        });

        //Aqui ele arrasta a figura caso, tenha alguma em foco
        this.addMouseMotionListener(new MouseMotionAdapter(){
            public void mouseDragged(MouseEvent evt){
                Point newPonto = getMousePosition(); 
                focus.mover(newPonto.x,newPonto.y);
                repaint();
            }
        });

        //Adiciona e exclui as figuras
        this.addKeyListener (
            new KeyAdapter() {
                public void keyPressed (KeyEvent evt) { 
                    if (evt.getKeyChar() == 'r') {    
                        Point ponto = getMousePosition();               
                        figs.add(new rect(ponto.x,ponto.y, 50,50,Color.BLACK,Color.YELLOW));
                    } else if (evt.getKeyChar() == 'e') {
                        Point ponto = getMousePosition();
                        figs.add(new elipse(ponto.x,ponto.y, 50,50,Color.BLACK,Color.YELLOW));
                    }else if(evt.getKeyChar() == 'l'){
                        Point ponto = getMousePosition();
                        figs.add(new linha(ponto.x,ponto.y, 50,50,ponto.x+60,ponto.y+60));
                    }else if(evt.getKeyChar() == 't'){
                        Point ponto = getMousePosition();
                        figs.add(new triangulo(ponto.x,ponto.y, 50,50,Color.BLACK,Color.YELLOW));
                    }else if(evt.getKeyChar() == 'd'){
                        figs.remove(focus);
                    }
                    repaint();
                }
            }
        );

        buts.add(new Button(1,new rect(8,8, 2,2,Color.BLACK,Color.YELLOW)));
        buts.add(new Button(2,new elipse(8,8, 2,2,Color.BLACK,Color.YELLOW)));
        this.setTitle("Projeto 2");
        this.setSize(400, 400);

    }

    public void windowClosing(WindowEvent w){
        try{
            FileOutputStream f = new FileOutputStream("proj.bin");
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(figs);
            o.flush();
            o.close();
        }catch(Exception e){
            System.out.print(e);
        }
        System.exit(0);
    } 

    //Verifica se tem alguma figura em foco
    public void verificaFocus(MouseEvent e){
        focus = null;
        for(Figure fig:this.figs){
            if(fig.clicked(e.getX(), e.getY())){
                this.focus = fig;
                focus.setFocus(e.getX(), e.getY());
                repaint();
            }
        }
    }

    public void verificaFocusButao(MouseEvent e){
        focus_but = null;
        for(Button but:this.buts){
            if(but.clicked(e.getX(), e.getY())){
                this.focus_but = but;
                focus.setFocus(e.getX(), e.getY());
                repaint();
            }
        }
    }
    public void paintComponent(Graphics g){
        super.paint(g);
        for(Button but: this.buts){
            but.paint(g,false);
        }
        for(Figure fig:this.figs){
            fig.paint(g,false);
        }
    }
    //Pinta as figuras
    public void paint (Graphics g) {
        //Aplicando Double Buffering
        dbImage = createImage(getWidth(),getHeight());
        dbg = dbImage.getGraphics();
        paintComponent(dbg);
        g.drawImage(dbImage,0,0,this);
    }
}
