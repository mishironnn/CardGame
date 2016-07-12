package CardGame;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Game extends Applet{
	Font f = new Font("Dialog",Font.PLAIN,33);
	public void paint(Graphics g){
		
		
		
		g.setColor(Color.black);
		g.setFont(f);
		for(int i = 495;i<506;i++){
			g.drawLine(0, i, 900, i);
		}
		g.setColor(Color.BLUE);
		g.fillRoundRect(820,410, 180, 180, 180, 180);
		g.setColor(Color.black);
		g.drawString("ターン終了", 830, 510);
		
	}
}
