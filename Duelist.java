package CardGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Duelist {
	//自分
	int HP = 20;
	 int MP = 0;
	 int MAX_MP = 0;
	int tefuda = 0;
	int boti = 0;
	int deck = 40;
	//相手
	int HPy = 20;
	 int MPy = 0;
	 int MAX_MPy = 0;
	int tefuday = 0;
	int botiy = 0;
	int decky = 40;
    String sura = "-";
	public void drawStringyou(Graphics g){
		//ターン時のMPの表示
		Font fo1 = new Font("SansSerif",Font.PLAIN,80);
		Graphics2D g2 = (Graphics2D)g;
		g.setColor(Color.black);
		g2.setFont(fo1);
		g2.drawString(sura, 330, 97);
		g2.drawString(sura, 340, 97);
		g2.drawString(sura, 360, 97);
		g2.drawString(""+MP, 335,70);
		g2.drawString(""+MAX_MP, 335,140);
		Font fo2 = new Font("SansSerif",Font.PLAIN,200);
		Graphics2D g3 = (Graphics2D)g;
		//HPの表示
		g3.setColor(Color.black);
		g3.setFont(fo2);
		g3.drawString(""+HP, 1550, 200);
	}
	public void drawStringme(Graphics g){
		//ターン時のMPの表示
		Font fo1 = new Font("SansSerif",Font.PLAIN,80);
		Graphics2D g2 = (Graphics2D)g;
		g.setColor(Color.black);
		g2.setFont(fo1);
		g2.drawString(sura, 1425, 940);
		g2.drawString(sura, 1435, 940);
		g2.drawString(sura, 1455, 940);
		g2.drawString(""+MP, 1430,913);
		g2.drawString(""+MAX_MP, 1430,983);
		Font fo2 = new Font("SansSerif",Font.PLAIN,200);
		Graphics2D g3 = (Graphics2D)g;
		//HPの表示
		g3.setColor(Color.black);
		g3.setFont(fo2);
		g3.drawString(""+HP, 50, 925);
	}
}