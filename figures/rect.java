package figures;

import java.awt.*;

public class rect extends Figure {
    public Color cor_borda, cor_fundo;


    public rect (int pos_x, int pos_y, int largura, int altura, Color cor_borda, Color cor_fundo) {
        super(pos_x,pos_y, largura, altura);
        this.cor_borda = cor_borda;
        this.cor_fundo = cor_fundo;

    }
    
    public boolean clicked(int x,int y){
        if(x>=this.pos_x && x<=this.pos_x+largura){
            if(y>=this.pos_y && y<=this.pos_y+altura){
                return true;
            }
            return false;
        }
        return false;
    }

    public void paintFocus(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(cor_borda);
        g2d.drawRect(this.pos_x,this.pos_y, this.largura,this.altura);
    }

    public void paint (Graphics g,Boolean focused) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(cor_fundo);
        g2d.fillRect(this.pos_x,this.pos_y, this.largura,this.altura);
        g2d.setColor(cor_borda);
        g2d.drawRect(this.pos_x,this.pos_y, this.largura,this.altura);
    }
}
