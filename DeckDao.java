package CardGame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/* 
 * さくらサーバ上に作成したテーブルから、デッキレシピを取得するクラスを作りました。
 * 
 * 準備
 * 
 * ※ デッキレシピの入ったテーブルを作る
 * 
 * テーブル名:deck_list を下記のような感じで作ってください。
 * 
 * +-------------------+-----------------+----------------+
 * | name(VARCHAR(50)) | number(INTEGER) | owner(INTEGER) |
 * +-------------------+-----------------+----------------+
 * |           ドラゴン|                3|               1|
 * +-------------------+-----------------+----------------+
 * |         ワイバーン|                2|               1|
 * +-------------------+-----------------+----------------+
 * |           ゴースト|                3|               1|
 * +-------------------+-----------------+----------------+
 * 
 * name:カード名
 * number:カードの枚数
 * owner:カードの所持者
 * 
 * デッキレシピを複数作征できるようになっていますが。
 * とりあえず最初は '1' がプレイヤーのデッキ内容で '2' が CPU のデッキ内容、という分け方で作ってください。
 *       
 *       
 * ※ Deck クラスを作る
 * 授業内で説明します
 * 
 * */

public class DeckDao {
    public Deck findDeckList(int owner) {
        Connection conn = null;
        Deck deck = new Deck();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(Account.JDBC_URL,Account.DB_USER,Account.DB_PASS);
            String sql = "select * from deck_list where owner = ?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, owner);
            ResultSet rs = pStmt.executeQuery();
            int count = 0;
            while (rs.next()) {
                String name = rs.getString("name");
                int number = rs.getInt("number");
                for(int i=count;i<number+count;i++){
                    deck.deckList[i] = name;
                }
                count = number + count;
            }
            return deck;
        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
