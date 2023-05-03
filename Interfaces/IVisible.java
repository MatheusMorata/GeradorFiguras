package Interfaces;
import java.awt.*;

public interface IVisible{
  void paint(Graphics g,Boolean focused);
  boolean clicked (int x,int y);
}
