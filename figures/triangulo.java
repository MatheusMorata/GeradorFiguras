package figures;
import java.awt.*;

public class triangulo extends Figure {

  private int tam = 40;
  private int mov = 20;
  public Color cor_borda, cor_fundo;
  public triangulo (int pos_x, int pos_y, int largura,int altura,Color cor_borda,Color cor_fundo) {
    super(pos_x,pos_y, largura, altura);
    this.cor_borda = cor_borda;
    this.cor_fundo = cor_fundo;
  }

  public boolean clicked(int x,int y){
      if(x>this.pos_x-1 && x<this.pos_x+this.tam+1){
        int dif = x - this.pos_x;

        if((dif==this.tam/2) && (y<this.pos_y+1) && (y>this.pos_y-this.tam-1)) return true;

        if((dif<this.tam/2) && (y<this.pos_y+1) && (y>this.pos_y-dif-1)) return true;

        if(dif>this.tam/2){
          int midDif = x - (this.pos_x+this.tam/2);
          if((midDif<this.tam+1) && (y<this.pos_y+1) && (y>((this.pos_y-tam/2)+midDif+1))) return true;
        }
      }
      return false;
  }


   public void paint (Graphics g, Boolean focused) {
    
    Graphics2D g2d = (Graphics2D) g;

    int xv[] = {this.pos_x,this.pos_x+this.tam/2,this.pos_x+this.tam};
    int yv[] = {this.pos_y,this.pos_y-this.tam/2,this.pos_y};

    g2d.setPaint(this.cor_fundo);
    g2d.fillPolygon(xv,yv,3);
    g2d.setPaint(this.cor_borda);
    g2d.drawPolygon(xv,yv,3);
  }
}
