package CardGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Duelist {
	int HP = 20;
	 int MP = 0;
	 int MAX_MP = 0;
	int tefuda = 0;
	int boti = 0;
	int deck = 40;
    String sura = "-";
	public void drawString(Graphics g){
		Font fo1 = new Font("SansSerif",Font.PLAIN,80);
		Graphics2D g2 = (Graphics2D)g;
		g.setColor(Color.black);
		g2.setFont(fo1);
		g2.drawString(sura, 330, 97);
		g2.drawString(sura, 340, 97);
		g2.drawString(sura, 360, 97);
		g2.drawString(""+MP, 335,70);
		g2.drawString(""+MAX_MP, 335,140);
		g2.drawString(sura, 1425, 950);
		g2.drawString(sura, 1435, 950);
		g2.drawString(sura, 1455, 950);
		g2.drawString(""+MP, 1430,923);
		g2.drawString(""+MAX_MP, 1430,993);
		Font fo2 = new Font("SansSerif",Font.PLAIN,200);
		Graphics2D g3 = (Graphics2D)g;
		g3.setColor(Color.black);
		g3.setFont(fo2);
		g3.drawString(""+HP, 1550, 200);
		g3.drawString(""+HP, 50, 925);
	}
}