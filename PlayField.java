package CardGame;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;

public class PlayField extends Applet implements MouseListener, MouseMotionListener {
	Image back;
	Graphics buffer;
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
	List<Card> d;
	CardDao cd = new CardDao();
	DeckDao dd = new DeckDao();
	Card[] playerField = new Card[5];
	Card[] OplayerField = new Card[5];
	Card[] playerHand = new Card[7];
	Card[] OplayerHand = new Card[7];
	String msg1 = "";
	String msg2 = "";
	String msg3 = "";
	String msg4 = "";
	int HP = 20;
	int MP = 10;
	int MAX_MP = 10;
	int tefuda = 0;
	int boti = 0;
	int deck = 40;
	// 相手
	int HPy = 20;
	int MPy = 0;
	int MAX_MPy = 0;
	int tefuday = 0;
	int botiy = 0;
	int decky = 40;
	String sura = "-";
	Card card;
	int count;

	public void init() {
		addMouseListener(this);
		addMouseMotionListener(this);
		image = getImage(getCodeBase(), "");
		enemy = getImage(getCodeBase(),
				"http://cdn-ak.f.st-hatena.com/images/fotolife/c/chemi_mizuki/20160711/20160711090921.png");
		player = getImage(getCodeBase(),
				"http://theanimesocialnetwork.com/wp-content/uploads/2016/07/cute-girls-from-vns-anime-manga-e38381e3838ee383ace383a0e8a1a8e7b499e7b5b5-by-e3828fe3818de4b889e697a5e79baee69db1e3839fefbc93efbc96ab-1-1024x1024.jpg");
		Dimension size = getSize();
		back = createImage(size.width, size.height);
		buffer = back.getGraphics();

	}

	public void drawStringyou(Graphics g) {
		// ターン時のMPの表示
		Font fo1 = new Font("SansSerif", Font.PLAIN, 80);
		Graphics2D g2 = (Graphics2D) g;
		g.setColor(Color.black);
		g2.setFont(fo1);
		g2.drawString(sura, 330, 97);
		g2.drawString(sura, 340, 97);
		g2.drawString(sura, 360, 97);
		g2.drawString("" + MP, 335, 70);
		g2.drawString("" + MAX_MP, 335, 140);
		Font fo2 = new Font("SansSerif", Font.PLAIN, 200);
		Graphics2D g3 = (Graphics2D) g;
		// HPの表示
		g3.setColor(Color.black);
		g3.setFont(fo2);
		g3.drawString("" + HP, 1550, 200);
	}

	public void drawStringme(Graphics g) {
		// ターン時のMPの表示
		Font fo1 = new Font("SansSerif", Font.PLAIN, 80);
		Graphics2D g2 = (Graphics2D) g;
		g.setColor(Color.black);
		g2.setFont(fo1);
		g2.drawString(sura, 1425, 940);
		g2.drawString(sura, 1435, 940);
		g2.drawString(sura, 1455, 940);
		g2.drawString("" + MP, 1430, 913);
		g2.drawString("" + MAX_MP, 1430, 983);
		Font fo2 = new Font("SansSerif", Font.PLAIN, 200);
		Graphics2D g3 = (Graphics2D) g;
		// HPの表示
		g3.setColor(Color.black);
		g3.setFont(fo2);
		g3.drawString("" + HP, 50, 925);
	}

	public void paint(Graphics g) {
		buffer.setColor(Color.WHITE);
		buffer.fillRect(0, 0, 1800, 1800);
		drowBoard(buffer);
		cardpaint(buffer);
		cardpaintOpponent(buffer);
		handCard(buffer);
		handCardOpponent(buffer);
		dplayer.drawStringme(buffer);
		dplayer.drawStringyou(buffer);
		Font fo1 = new Font("SansSerif", Font.PLAIN, 20);
		buffer.setFont(fo1);
		buffer.drawString(msg1, 1520, 800);
		buffer.drawString(msg2, 1520, 825);
		buffer.drawString(msg3, 1520, 850);
		buffer.drawString(msg4, 1520, 875);
		// デッキ、墓地の枚数表示
		if (mouseOnDeck == true) {
			buffer.drawString(deck2, 1585, 635);
			buffer.drawString("" + dplayer.deck, 1695, 635);
			buffer.drawString(boti2, 1585, 655);
			buffer.drawString("" + dplayer.boti, 1695, 655);
		}
		// 相手のデッキ、墓地の枚数表示
		if (mouseOnDecky == true) {
			buffer.drawString(deck2, 85, 345);
			buffer.drawString("" + dplayer.decky, 195, 345);
			buffer.drawString(boti2, 85, 365);
			buffer.drawString("" + dplayer.botiy, 195, 365);
		}
		Deck deck = dd.findDeckList(1);
		if (!T) {
			d = cd.findCardListData(deck);
			T = true;
		}
		Card card = d.get(0);
		buffer.setColor(Color.black);
		Font fo2 = new Font("SansSerif", Font.PLAIN, 20);
		buffer.setFont(fo2);
		// buffer.drawString(card.name, 1310, 580);
		Font fo3 = new Font("SansSerif", Font.PLAIN, 40);
		buffer.setFont(fo3);
		// buffer.drawString("" + card.attack, 1280, 740);
		// buffer.drawString("" + card.defence, 1400, 740);
		// buffer.drawString("" + card.cost, 1280, 580);
		g.drawImage(back, 0, 0, this);
	}

	public void update(Graphics g) {
		paint(g);
	}

	public void drowBoard(Graphics g) {
		g.setColor(Color.black);
		g.drawRoundRect(0, 0, 1800, 1800, 100, 100);
		g.drawLine(0, 500, 1800, 500);
		g.drawLine(300, 000, 300, 1200);
		g.drawLine(1500, 0, 1500, 1200);
		g.setColor(Color.lightGray);
		g.fillRoundRect(1575, 545, 150, 200, 10, 10);
		g.fillRoundRect(75, 255, 150, 200, 10, 10);
		g.setColor(Color.blue);
		g.fillRoundRect(1800, 440, 120, 120, 20, 20);
		g.drawImage(enemy, 1525, 255, 250, 200, this);
		g.drawImage(player, 25, 545, 250, 200, this);
		g.drawImage(image, 0, 0, this);
		// 反転させたい時はこんな感じ
		g.drawImage(image, 1050, 1250, -800, -650, this);
	}

	public void cardpaint(Graphics g) {
		g.setColor(Color.black);
		playerField[0] = cd.findCardData("a");
		playerField[1] = cd.findCardData("b");
		playerField[2] = null;
		playerField[3] = cd.findCardData("d");
		playerField[4] = cd.findCardData("e");
		for (int i = 0; i < 5; i++) {
			if (playerField[i] != null) {
				g.drawRoundRect(1275 - 225 * i, 545, 150, 200, 10, 10);
				g.drawString(playerField[i].name, 1300 - 225 * i, 580);
				g.drawString("攻:" + playerField[i].attack, 1300 - 225 * i, 640);
				g.drawString("防:" + playerField[i].defence, 1300 - 225 * i, 700);
			}
		}
	}

	public void cardpaintOpponent(Graphics g) {
		g.setColor(Color.black);
		OplayerField[0] = cd.findCardData("a");
		OplayerField[1] = cd.findCardData("b");
		OplayerField[2] = null;
		OplayerField[3] = cd.findCardData("d");
		OplayerField[4] = cd.findCardData("e");
		for (int i = 0; i < 5; i++) {
			if (OplayerField[i] != null) {
				g.drawRoundRect(375 + 225 * i, 245, 150, 200, 10, 10);
				g.drawString(OplayerField[i].name, 400 + 225 * i, 280);
				g.drawString("攻:" + OplayerField[i].attack, 400 + 225 * i, 340);
				g.drawString("防:" + OplayerField[i].defence, 400 + 225 * i, 400);
			}
		}
	}

	public void handCard(Graphics g) {
		Font fo3 = new Font("SansSerif", Font.PLAIN, 30);
		buffer.setFont(fo3);
		playerHand[0] = cd.findCardData("f");
		playerHand[1] = cd.findCardData("g");
		playerHand[2] = cd.findCardData("e");
		playerHand[3] = cd.findCardData("h");
		playerHand[4] = cd.findCardData("i");
		playerHand[5] = cd.findCardData("a");
		playerHand[6] = cd.findCardData("b");
		for (int i = 0; i < 7; i++) {
			if (playerHand[i] != null && i % 2 == 0) {
				g.drawRoundRect(825 + 45 * i, 800, 150, 200, 10, 10);
				g.drawString("" + playerHand[i].name, 830 + 45 * i, 835);
				g.drawString("攻:" + playerHand[i].attack, 830 + 45 * i, 895);
				g.drawString("防:" + playerHand[i].defence, 830 + 45 * i, 955);
			} else {
				g.drawRoundRect(780 - 45 * i, 800, 150, 200, 10, 10);
				g.drawString("" + playerHand[i].name, 785 - 45 * i, 835);
				g.drawString("攻:" + playerHand[i].attack, 785 - 45 * i, 895);
				g.drawString("防:" + playerHand[i].defence, 785 - 45 * i, 955);
			}
		}
	}

	public void handCardOpponent(Graphics g) {
		Font fo3 = new Font("SansSerif", Font.PLAIN, 30);
		buffer.setFont(fo3);
		OplayerHand[0] = cd.findCardData("f");
		OplayerHand[1] = cd.findCardData("g");
		OplayerHand[2] = cd.findCardData("e");
		OplayerHand[3] = cd.findCardData("h");
		OplayerHand[4] = cd.findCardData("i");
		OplayerHand[5] = cd.findCardData("a");
		OplayerHand[6] = cd.findCardData("b");
		for (int i = 0; i < 7; i++) {
			if (OplayerHand[i] != null && !(i % 2 == 0)) {
				g.drawRoundRect(870 + 45 * i, 15, 150, 200, 10, 10);
				g.drawString("" + OplayerHand[i].cost, 875 + 45 * i, 50);
				g.drawString("攻:" + OplayerHand[i].attack, 875 + 45 * i, 110);
				g.drawString("防:" + OplayerHand[i].defence, 875 + 45 * i, 170);
			} else {
				g.drawRoundRect(825 - 45 * i, 15, 150, 200, 10, 10);
				g.drawString("" + OplayerHand[i].cost, 830 - 45 * i, 50);
				g.drawString("攻:" + OplayerHand[i].attack, 830 - 45 * i, 110);
				g.drawString("防:" + OplayerHand[i].defence, 830 - 45 * i, 170);
			}
		}
	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {

		int x = e.getX();
		int y = e.getY();
		System.out.println("クリックした座標=(" + x + "," + y + ")");
		if (555 < x && x < 645) {

		} else if (645 < x && x < 735) {

		} else if (735 < x && x < 825) {

		} else if (825 < x && x < 915) {
			// playerHand[0]
			// ワンクリック時のデータ表示
		} else if (915 < x && x < 1005) {

		} else if (1005 < x && x < 1095) {

		} else if (1095 < x && x < 1185) {

		}

		if (e.getClickCount() >= 2) {
			// ダブルクリック
			System.out.println("ダブルクリックしたよ");
			/*
			 * クリックした座標=(735,15) クリックした座標=(825,17) クリックした座標=(916,17) 555~1095
			 * 90ごと！！
			 */

			for (int i = 0; i < 7; i++) {
				if (555 <= x && x <= 1245 && 800 <= y && y <= 1000) {
					card = playerHand[i];
					
					if (card == null) {
						// 何もしない
					} else if (playerField[1] != null && playerField[2] != null && playerField[3] != null
							&& playerField[4] != null && playerField[5] != null) {
						msg1 = "フィールド上に";
						msg2 = "空きがありません";
						msg3 = "";
						repaint();
					} else if (MP < card.cost) {
						msg1 = "MP が不足しています";
						msg2 = "";
						msg3 = "";
						repaint();
					} else if (card.cost <= MP) {
						msg1 = "場所の設定";
						msg2 = "";
						msg3 = "";
						repaint();
						count = i;
						
					}
					/*
					 * if (555 < x && x < 645) {
					 * 
					 * } else if (645 < x && x < 735) {
					 * 
					 * } else if (735 < x && x < 825) {
					 * 
					 * } else if (825 < x && x < 915) {
					 * 
					 * } else if (915 < x && x < 1005) {
					 * 
					 * } else if (1005 < x && x < 1095) {
					 * 
					 * } else if (1095 < x && x < 1185) {
					 * 
					 * }
					 */

				}
			}
			for (int i = 0; i < 5; i++) {
				
				if (x >= 1275 - 225 * i && x <= 1275 - 225 * i + 150 && y >= 545 && y <= 745) {
					if (playerField[i] == null) {
						playerField[i] = cd.findCardData(playerHand[count].name);
						MP -= playerHand[count].cost;
						msg1 = playerHand[count].name + "を召喚しました";
						msg2 = "";
						msg3 = "";
						repaint();
						playerHand[count]= null;
					}
				}
			}
		}
	}

	public void mouseReleased(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	public void mouseEntered(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	public void mouseExited(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	public void mouseDragged(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	public void mouseMoved(MouseEvent e) {
		// 自分のデッキ、墓地の枚数
		boolean needRepaint = false;
		int x = e.getX();
		int y = e.getY();
		if (1575 <= x && x <= (1575 + 150) && 545 <= y && y <= 545 + 200) {
			if (mouseOnDeck == false) {
				needRepaint = true;
			}
			mouseOnDeck = true;
		} else {
			if (mouseOnDeck == true) {
				needRepaint = true;
			}
			mouseOnDeck = false;
		}
		if (needRepaint == true) {
			repaint();
		}
		// 相手のデッキ、墓地の枚数
		boolean needRepainty = false;
		int A = e.getX();
		int B = e.getY();
		if (75 <= A && A <= (75 + 150) && 255 <= B && B <= 255 + 200) {
			if (mouseOnDecky == false) {
				needRepainty = true;
			}
			mouseOnDecky = true;
		} else {
			if (mouseOnDecky == true) {
				needRepainty = true;
			}
			mouseOnDecky = false;
		}
		if (needRepaint == true || needRepainty == true) {
			repaint();

		}
	}
}