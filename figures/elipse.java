package figures;

import java.awt.*;


public class elipse extends Figure {
    public Color cor_borda, cor_fundo;

    public elipse (int pos_x, int pos_y, int largura, int altura, Color cor_borda,Color cor_fundo) {
        super(pos_x,pos_y, largura, altura);
        this.cor_borda = cor_borda;
        this.cor_fundo = cor_fundo;
    }

     public boolean clicked(int x,int y){
        int centerX = this.pos_x + this.largura;
        int centerY = this.pos_y + this.altura;

        int diffX = (x - centerX);
        int diffY = (y - centerY);

        diffX = diffX*diffX;
        diffY = diffY*diffY;

        float width = this.largura * this.largura;
        float heigth = this.altura*this.altura;
        float first = diffX/(width);
        float second = diffY/(heigth);
        float diffSum = first+second;

        if(diffSum<=1){
          return true;
        }

        return false;
    }

    public void paint (Graphics g, Boolean focused) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(cor_fundo);
        g2d.fillOval(this.pos_x,this.pos_y, this.largura,this.altura);
        g2d.setColor(cor_borda);
        g2d.drawOval(this.pos_x,this.pos_y, this.largura,this.altura);
    }
}
