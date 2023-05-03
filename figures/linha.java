package figures;

import java.awt.*;


public class linha extends Figure {
    public int endX, endY;
    public linha (int pos_x, int pos_y, int largura, int altura,int endX, int endY) {
        super(pos_x,pos_y,largura,altura);
        this.endX = endX;
        this.endY = endY;
    }

    public boolean clicked(int x,int y){
        return ((x>this.pos_x-1 && x<this.endX+1) && (y>this.pos_y-1 && y<this.endY+1)) ? true : false;
    }

    public void paint (Graphics g, Boolean focused) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawLine(this.pos_x,this.pos_y, this.endX,this.endY);
    }
}
