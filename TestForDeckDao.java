package CardGame;

public class TestForDeckDao {
/*
 * 実行してデッキのカード名がコンソールに出力されるのを確認してください。
 * 
 * */
    public static void main(String[] args) {
        DeckDao dao = new DeckDao();
        Deck deck = dao.findDeckList(1);
        for(int i=0;i<deck.deckList.length;i++){
            System.out.println(deck.deckList[i]);
        }
    }
}