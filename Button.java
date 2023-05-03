import Interfaces.IVisible;
import figures.Figure;
import java.awt.*;

public class Button implements IVisible {
    public  int    idx;
    private Figure fig;
    static int SPC = 10;
    static int DIM = 40;
    static int PAD = 4;

    public Button (int idx, Figure fig) {
        this.idx = idx;
        this.fig = fig;
        this.fig.pos_x = PAD+SPC;
        this.fig.pos_y = PAD+SPC + idx*DIM;
        this.fig.largura = DIM-PAD*2;
        this.fig.altura = DIM-PAD*2;
    }
    public boolean clicked(int x,int y){
    	if(x>=this.fig.pos_x && x<=this.fig.pos_x+this.fig.largura){
            if(y>=this.fig.pos_y && y<=this.fig.pos_y+this.fig.altura){
                return true;
            }
            return false;
        }
        return false;
    }

    public void paint (Graphics g,Boolean focused) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.GRAY);
        g2d.fillRect(SPC, SPC+this.idx*DIM, DIM, DIM);

        g2d.setColor(Color.BLACK);
        g2d.drawRect(SPC, SPC+this.idx*DIM, DIM, DIM);

        this.fig.paint(g,false);
    }
}
