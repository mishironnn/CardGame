package CardGame;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PlayField extends Applet implements MouseListener{
    Image image;
    Image enemy;
    Image player;
    int cord;
    public void init(){
    	addMouseListener(this);
        image = getImage(getCodeBase(), "");
        enemy = getImage(getCodeBase(), "http://rr.img.naver.jp/mig?src=http%3A%2F%2Fimgcc.naver.jp%2Fkaze%2Fmission%2FUSER%2F20160420%2F67%2F6426277%2F15%2F570x807x7cca6b9d432c8c398737dd53.jpg%2F300%2F600&twidth=300&theight=0&qlt=80&res_format=jpg&op=r");
        player = getImage(getCodeBase(), "img/IMG_0700.JPG");
    }
    public void paint(Graphics g){
        drowBoard(g);
        cordpaint(g);
    }
    public void drowBoard(Graphics g){
        g.setColor(Color.black);
        g.drawRoundRect(0,0,1200,1200,100,100);
        g.drawLine(0,600,1200,600);
        g.drawLine(240,600,240,1200);
        g.drawLine(960,0,960,600);
        g.setColor(Color.lightGray);
        g.fillRoundRect(1000,900,150,200,10,10);
        g.fillRoundRect(50,100,150,200,10,10);
        g.setColor(Color.blue);
        g.fillRoundRect(960,480,240,240,20,20);
        g.drawImage(enemy,1000,240,150,200,this);
        g.drawImage(player,40,640,150,200,this);
        g.drawImage(image,0,0,this);
        // 反転させたい時はこんな感じ
        g.drawImage(image,1050,1250,-800,-650,this);
    }
    public void cordpaint(Graphics g){
    	g.setColor(Color.black);
    	g.fillRoundRect(720,360,150,200,10,10);
    	g.fillRoundRect(560,360,150,200,10,10);
    	g.fillRoundRect(400,360,150,200,10,10);
    	g.fillRoundRect(240,360,150,200,10,10);
    	g.fillRoundRect(80,360,150,200,10,10);
    	g.fillRoundRect(320,640,150,200,10,10);
    	g.fillRoundRect(480,640,150,200,10,10);
    	g.fillRoundRect(640,640,150,200,10,10);
    	g.fillRoundRect(800,640,150,200,10,10);
    	g.fillRoundRect(960,640,150,200,10,10);
    }
	public void mouseClicked(MouseEvent e) {
		
		CardDAO c = new CardDAO();
		c.findCardData("ドラゴンガード");
		System.out.println(c.playerCard[0]);
		System.out.println(c.playerCard[1]);
		System.out.println(c.playerCard[2]);
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
}