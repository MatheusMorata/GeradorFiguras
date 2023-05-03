package figures;
import Interfaces.IVisible;
import java.awt.*;
import java.io.Serializable;
public abstract class Figure implements IVisible,Serializable {
    public int pos_x, pos_y, largura, altura;
    public int previousX = -1;
    public int previousY = -1;

    public Figure (int pos_x, int pos_y, int largura, int altura) {
        this.pos_x = pos_x;
        this.pos_y = pos_y;
        this.largura = largura;
        this.altura = altura;

    }

    public void setFocus(int mouseX,int mouseY){
        this.previousX = mouseX;
        this.previousY = mouseY;
        
    }
    public void mover(int x,int y){
        int dx = x - this.previousX;
        int dy = y - this.previousY;

        this.pos_x = this.pos_x+dx;
        this.pos_y = this.pos_y+dy;

        previousX = x;
        previousY = y;

  };
   
    public abstract void paint(Graphics g,Boolean focused);
    public abstract boolean clicked(int x,int y);
}
