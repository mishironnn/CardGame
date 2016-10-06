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
import java.util.Arrays;
import java.util.Collections;
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
	Deck mydeck = dd.findDeckList(1);
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
	int Hns = -1;// 手札番号記憶
	int Fns = -1;
	int canAttack0 = 0;
	int canAttack1 = 0;
	int canAttack2 = 1;
	int canAttack3 = 0;
	int canAttack4 = 0;
	boolean turnEnd;
	boolean OturnEnd;
	int state = -1;
	int deckcount = 3;
	int[] sarray = new int[40];
	String[] array = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17",
			"18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35",
			"36", "37", "38", "39" };

	// 0は何も選択されていない状態
	// 1は手札のカードが選択された状態
	// 2は場のカードが選択された状態

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

		List<String> list = Arrays.asList(array);
		Collections.shuffle(list);
		String[] array2 = (String[]) list.toArray(new String[list.size()]);
		for (int i = 0; i < array2.length; i++) {
			sarray[i] = Integer.parseInt(array2[i]);
		}
		playerHand[0] = cd.findCardData(mydeck.deckList[sarray[0]]);
		playerHand[1] = cd.findCardData(mydeck.deckList[sarray[1]]);
		playerHand[2] = cd.findCardData(mydeck.deckList[sarray[2]]);
		deck -= 3;

		OplayerHand[0] = cd.findCardData("F");
		OplayerHand[1] = cd.findCardData("G");
		OplayerHand[2] = cd.findCardData("E");
		OplayerHand[3] = cd.findCardData("H");
		OplayerHand[4] = cd.findCardData("I");
		OplayerHand[5] = cd.findCardData("A");
		OplayerHand[6] = cd.findCardData("B");

		playerField[2] = cd.findCardData(mydeck.deckList[sarray[0]]);
		OplayerField[2] = cd.findCardData("B");
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
		g2.drawString("" + MPy, 335, 70);
		g2.drawString("" + MAX_MPy, 335, 140);
		Font fo2 = new Font("SansSerif", Font.PLAIN, 200);
		Graphics2D g3 = (Graphics2D) g;
		// HPの表示
		g3.setColor(Color.black);
		g3.setFont(fo2);
		g3.drawString("" + HPy, 1550, 200);
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
		drawStringme(buffer);
		drawStringyou(buffer);
		Font fo1 = new Font("SansSerif", Font.PLAIN, 20);
		buffer.setFont(fo1);
		buffer.drawString(msg1, 1520, 800);
		buffer.drawString(msg2, 1520, 825);
		buffer.drawString(msg3, 1520, 850);
		buffer.drawString(msg4, 1520, 875);

		// デッキ、墓地の枚数表示
		if (mouseOnDeck == true) {
			buffer.drawString(deck2, 1585, 635);
			buffer.drawString("" + deck, 1695, 635);
			buffer.drawString(boti2, 1585, 655);
			buffer.drawString("" + boti, 1695, 655);
		}
		// 相手のデッキ、墓地の枚数表示
		if (mouseOnDecky == true) {
			buffer.drawString(deck2, 85, 345);
			buffer.drawString("" + decky, 195, 345);
			buffer.drawString(boti2, 85, 365);
			buffer.drawString("" + botiy, 195, 365);
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

		for (int i = 0; i < 7; i++) {
			if (playerHand[i] != null) {
				if (i % 2 == 0) {
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
	}

	public void handCardOpponent(Graphics g) {
		Font fo3 = new Font("SansSerif", Font.PLAIN, 30);
		buffer.setFont(fo3);

		for (int i = 0; i < 7; i++) {
			if (OplayerHand[i] != null) {
				if (!(i % 2 == 0)) {
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
	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {
		boolean repaint = false;
		int x = e.getX();
		int y = e.getY();
		System.out.println("クリックした座標=(" + x + "," + y + ")");
		if (state == -1) {
			if (canAttack0 == 1) {
				canAttack0 += 1;
			} else if (canAttack1 == 1) {
				canAttack1 += 1;
			} else if (canAttack2 == 1) {
				canAttack2 += 1;
			} else if (canAttack3 == 1) {
				canAttack3 += 1;
			} else if (canAttack4 == 1) {
				canAttack4 += 1;
			}
			if (playerHand[0] == null) {
				playerHand[0] = cd.findCardData(mydeck.deckList[sarray[deckcount]]);
				deckcount += 1;
				deck -= 1;
				repaint();
			} else if (playerHand[1] == null) {
				playerHand[1] = cd.findCardData(mydeck.deckList[sarray[deckcount]]);
				deckcount += 1;
				deck -= 1;
				repaint();
			} else if (playerHand[2] == null) {
				playerHand[2] = cd.findCardData(mydeck.deckList[sarray[deckcount]]);
				deckcount += 1;
				deck -= 1;
				repaint();
			} else if (playerHand[3] == null) {
				playerHand[3] = cd.findCardData(mydeck.deckList[sarray[deckcount]]);
				deckcount += 1;
				deck -= 1;
				repaint();
			} else if (playerHand[4] == null) {
				playerHand[4] = cd.findCardData(mydeck.deckList[sarray[deckcount]]);
				deckcount += 1;
				deck -= 1;
				repaint();
			} else if (playerHand[5] == null) {
				playerHand[5] = cd.findCardData(mydeck.deckList[sarray[deckcount]]);
				deckcount += 1;
				deck -= 1;
				repaint();
			} else if (playerHand[6] == null) {
				playerHand[6] = cd.findCardData(mydeck.deckList[sarray[deckcount]]);
				deckcount += 1;
				deck -= 1;
				repaint();
			} else {
				boti += 1;
				deck -= 1;
				repaint();
			}
			state = 0;
		}

		if (e.getClickCount() >= 2) {
			// ダブルクリック
			System.out.println("ダブルクリックしたよ");
			/*
			 * クリックした座標=(735,15) クリックした座標=(825,17) クリックした座標=(916,17) 555~1095
			 * 90ごと！！
			 */
			// 召喚するモンスターを手札から選択
			if (state == 0) {
				if (1800 < x && x < 1920 && 440 < y && y < 560) {
					msg1 = "相手のターンです";
					msg2 = "";
					msg3 = "";
					repaint = true;
					state = 4;
				}
				for (int i = 0; i < 7; i++) {
					if (800 < y && y < 1000) {
						if (555 < x && x < 645) {
							Hns = 5;
						} else if (645 < x && x < 735) {
							Hns = 3;
						} else if (735 < x && x < 825) {
							Hns = 1;
						} else if (825 < x && x < 915) {
							Hns = 0;
						} else if (915 < x && x < 1005) {
							Hns = 2;
						} else if (1005 < x && x < 1095) {
							Hns = 4;
						} else if (1095 < x && x < 1185) {
							Hns = 6;
						}
						card = playerHand[Hns];
						if (card == null) {
							// 何もしない
						} else if (playerField[0] != null && playerField[1] != null && playerField[2] != null
								&& playerField[3] != null && playerField[4] != null) {
							msg1 = "フィールド上に";
							msg2 = "空きがありません";
							msg3 = "";
							repaint = true;
						} else if (MP < card.cost) {
							msg1 = "MP が不足しています";
							msg2 = "";
							msg3 = "";
							repaint = true;
						} else if (card.cost <= MP) {
							msg1 = "場所の設定";
							msg2 = "";
							msg3 = "";
							repaint = true;
							state = 1;
						}
					}
				}
				for (int i = 0; i < 5; i++) {
					if (x >= 1275 - 225 * i && x <= 1275 - 225 * i + 150 && y >= 545 && y <= 745) {
						if (playerField[i] != null) {
							if (canAttack0 == 2 || canAttack1 == 2 || canAttack2 == 2 || canAttack3 == 2
									|| canAttack4 == 2) {
								msg1 = "";
								msg2 = "攻撃対象を";
								msg3 = "選択してください";
								repaint = true;
								state = 2;
								Fns = i;
							} else {
								msg1 = "攻撃できるモンスターがいません";
								msg2 = "";
								msg3 = "";
								repaint = true;
								state = 0;
							}
						}
					}
				}
			} else
			// 場にモンスターを召喚
			if (state == 1) {
				if (Hns != -1) {
					for (int i = 0; i < 5; i++) {
						if (x >= 1275 - 225 * i && x <= 1275 - 225 * i + 150 && y >= 545 && y <= 745) {
							if (playerField[i] != null) {
							} else {
								// state=0の時に保存していたものを使用
								playerField[i] = playerHand[Hns];
								MP -= playerHand[Hns].cost;
								msg1 = playerHand[Hns].name + "を召喚しました";
								msg2 = "";
								msg3 = "";
								if (playerField[i] == playerField[0]) {
									canAttack0 = 1;
								} else if (playerField[i] == playerField[1]) {
									canAttack1 = 1;
								} else if (playerField[i] == playerField[2]) {
									canAttack2 = 1;
								} else if (playerField[i] == playerField[3]) {
									canAttack3 = 1;
								} else if (playerField[i] == playerField[4]) {
									canAttack4 = 1;
								}
								repaint = true;
								playerHand[Hns] = null;
								Hns = -1;
								state = 0;
							}
						}
					}
				}
			} else
			// 攻撃
			if (state == 2) {
				for (int l = 0; l < 5; l++) {
					if (375 + 225 * l <= x && x <= 375 + 225 * l + 150 && 245 <= y && y <= 445) {
						if (!(canAttack0 == 2 || canAttack1 == 2 || canAttack2 == 2 || canAttack3 == 2
								|| canAttack4 == 2)) {
							msg1 = "このモンスターは";
							msg2 = "次のターンまで";
							msg3 = "攻撃できません";
							repaint = true;
						} else {
							Card card = OplayerField[l];
							msg1 = playerField[Fns].name + "は" + card.name + "に";
							msg2 = "攻撃しました !";
							msg3 = "";
							repaint = true;
							card.defence -= playerField[Fns].attack;
							playerField[Fns].defence -= card.attack;
							if (card.defence <= 0) {
								OplayerField[l] = null;
							}
							if (playerField[Fns].defence <= 0) {
								playerField[Fns] = null;
							}
							if (l == 0) {
								canAttack0 = 1;
							} else if (l == 1) {
								canAttack1 = 1;
							} else if (l == 2) {
								canAttack2 = 1;
							} else if (l == 3) {
								canAttack3 = 1;
							} else if (l == 4) {
								canAttack4 = 1;
							}
							state = 0;
							break;
						}
					}
					if (1525 < x && x < 1775 && 200 < y && y < 255) {
						if (!(canAttack0 == 2 || canAttack1 == 2 || canAttack2 == 2 || canAttack3 == 2
								|| canAttack4 == 2)) {
							msg1 = "このモンスターは";
							msg2 = "次のターンまで";
							msg3 = "攻撃できません";
							repaint = true;
						} else {
							msg1 = playerField[Fns].name + "はプレイヤーに";
							msg2 = "攻撃しました !";
							msg3 = "";
							HPy -= playerField[Fns].attack;
							repaint = true;
							if (Fns == 0) {
								canAttack0 = 1;
							} else if (Fns == 1) {
								canAttack1 = 1;
							} else if (Fns == 2) {
								canAttack2 = 1;
							} else if (Fns == 3) {
								canAttack3 = 1;
							} else if (Fns == 4) {
								canAttack4 = 1;
							}
						}
						state = 0;
						break;
					}
				}
			}
		}
		if(playerField[0]==null){
			canAttack0 = 0;
		}else if(playerField[1]==null){
			canAttack1 = 0;
		}else if(playerField[2]==null){
			canAttack2 = 0;
		}else if(playerField[3]==null){
			canAttack3 = 0;
		}else if(playerField[4]==null){
			canAttack4 = 0;
		}
		if (repaint == true) {
			repaint();
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