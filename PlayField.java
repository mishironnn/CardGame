package CardGame;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;

public class PlayField extends Applet implements MouseListener,MouseMotionListener{
	Image back ;
    Graphics buffer ;
    Image image;
    Image enemy;
    Image player;
    int cord;
    String deck2 = "デッキ枚数:";
    String boti2 = "墓地の枚数:";
    boolean mouseOnDeck;
    boolean mouseOnDecky;
    Duelist dplayer = new Duelist();
    boolean T = false;
    List<Card> d ;
    CardDao cd = new CardDao();
    DeckDao dd = new DeckDao();
    public void init(){
    	addMouseListener(this);
    	addMouseMotionListener(this);
        image = getImage(getCodeBase(), "");
        enemy = getImage(getCodeBase(), "http://rr.img.naver.jp/mig?src=http%3A%2F%2Fimgcc.naver.jp%2Fkaze%2Fmission%2FUSER%2F20160420%2F67%2F6426277%2F15%2F570x807x7cca6b9d432c8c398737dd53.jpg%2F300%2F600&twidth=300&theight=0&qlt=80&res_format=jpg&op=r");
        player = getImage(getCodeBase(), "img/IMG_0700.JPG");
        Dimension size = getSize();
        back = createImage(size.width,size.height);
        buffer = back.getGraphics();

    }
    public void paint(Graphics g){
    	buffer.setColor(Color.WHITE);
    	buffer.fillRect(0,0,1800,1800);
        drowBoard(buffer);
        cardpaint(buffer);
       dplayer.drawStringme(buffer);
       dplayer.drawStringyou(buffer);
        Font fo1 = new Font("SansSerif",Font.PLAIN,20);
        buffer.setFont(fo1);
        //デッキ、墓地の枚数表示
        if(mouseOnDeck==true){
        	buffer.drawString(deck2,1585,635);
        	buffer.drawString(""+dplayer.deck, 1695, 635);
        	buffer.drawString(boti2,1585,655);
        	buffer.drawString(""+dplayer.boti, 1695, 655);
        }
        //相手のデッキ、墓地の枚数表示
        if(mouseOnDecky==true){
        	buffer.drawString(deck2,85,345);
        	buffer.drawString(""+dplayer.decky, 195, 345);
        	buffer.drawString(boti2,85,365);
        	buffer.drawString(""+dplayer.botiy, 195, 365);
        }
        Deck deck = dd.findDeckList(1);
        if(!T){
        	d = cd.findCardListData(deck);
        	T=true;
        }
    	Card card = d.get(0);
        buffer.setColor(Color.black);
        Font fo2 = new Font("SansSerif",Font.PLAIN,20);
        buffer.setFont(fo2);
    	buffer.drawString(card.name, 860, 580);
    	Font fo3 = new Font("SansSerif",Font.PLAIN,40);
        buffer.setFont(fo3);
    	buffer.drawString(""+card.attack, 830, 740);
    	buffer.drawString(""+card.defence, 950, 740);
    	buffer.drawString(""+card.cost, 830, 580);
        g.drawImage(back, 0, 0, this);
    }
    public void update(Graphics g){
    	paint(g);
    }
    public void drowBoard(Graphics g){
        g.setColor(Color.black);
        g.drawRoundRect(0,0,1800,1800,100,100);
        g.drawLine(0,500,1800,500);
        g.drawLine(300,000,300,1200);
        g.drawLine(1500,0,1500,1200);
        g.setColor(Color.lightGray);
        g.fillRoundRect(1575,545,150,200,10,10);
        g.fillRoundRect(75,255,150,200,10,10);
        g.setColor(Color.blue);
        g.fillRoundRect(1800,440,120,120,20,20);
        g.drawImage(enemy,1575,255,150,200,this);
        g.drawImage(player,75,545,150,200,this);
        g.drawImage(image,0,0,this);
        // 反転させたい時はこんな感じ
        g.drawImage(image,1050,1250,-800,-650,this);
    }
    public void cardpaint(Graphics g){
    	g.setColor(Color.black);
    	Card [] playerField = new Card[5];
    	Card card = cd.findCardData("ドラゴンガード");
    	playerField[0] =card;
    	for(int i = 0;i<5;i++){
    		if(playerField[i]==null){

    		}else{
    		g.drawRoundRect(1275-225*i,545,150,200,10,10);
    		}
    	}
    	for(int i = 0;i<5;i++){
        g.drawRoundRect(1275-225*i,255,150,200,10,10);
        }
    }
	public void mouseClicked(MouseEvent e) {

		/*CardDAO c = new CardDAO();
		Card card = c.findCardData("ドラゴンガード");
		System.out.println(Card.name);
		System.out.println(Card.attack);
		System.out.println(Card.defence);*/

	}
	@Override
	public void mousePressed(MouseEvent e) {

		int x = e.getX();
		int y = e.getY();
		System.out.println("クリックした座標=("+x+","+y+")");

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
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}
	public void mouseMoved(MouseEvent e) {
		//自分のデッキ、墓地の枚数
		boolean needRepaint = false;
		int x = e.getX();
		int y = e.getY();
		if(1575 <= x && x<= (1575 + 150) && 545<= y && y<= 545 + 200  ){
			if(mouseOnDeck==false){
				needRepaint=true;
			}
			mouseOnDeck=true;
		}else{
			if(mouseOnDeck==true){
				needRepaint=true;
			}
			mouseOnDeck=false;
		}
		if(needRepaint==true){
			repaint();
		//相手のデッキ、墓地の枚数
			boolean needRepainty = false;
			int A = e.getX();
			int B = e.getY();
		if(75 <= A && A<= (75 + 150) && 255<= B && B<= 255 + 200  ){
			if(mouseOnDecky==false){
				needRepainty=true;
			}
			mouseOnDecky=true;
		}else{
			if(mouseOnDecky==true){
				needRepainty=true;
			}
			mouseOnDecky=false;
		}
		if(needRepainty==true){
			repaint();
		}
		}
	}
}