package CardGame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/* 
 * さくらサーバ上に作成したテーブルから、カードのステータスを取得するクラスを作りました。
 * コードの詳しい内容は夏休み以降に勉強するとして、必要な準備とコード内コメントをざっと読んでください。
 * 
 * 
 * ※ JDBC をインストール
 * 
 * JDBCは、Java と DB を接続するための API です。
 * MySQL の DB と接続するには、MySQL 用の JDBC ドライバが必要です。
 * 
 * ① GitHub 上のこのソースコードと同じ場所にある "mysql-connector-java-5.1.38-bin.jar" をダウンロードする。
 *    (View Raw をクリックするとダウンロードできる)。
 * ② 画像の時と同じく bin フォルダあたりに置く。
 * ③ プロジェクトファイルを右クリック → ｢プロパティ｣  → ｢Java のビルドパス｣ → ｢ライブラリ｣ タブを開く。
 * ④ ｢外部 jar 追加｣で ② の JDBC を指定する。
 * 
 * 
 * ※ カード一覧の入ったテーブルを作る
 * 
 * テーブル名:card_tbl を下記のような感じで作ってください。
 * 
 * +-------------------+-----------------+------------------+---------------+----------------------+
 * | name(VARCHAR(50)) | attack(INTEGER) | defence(INTEGER) | cost(INTEGER) | ability(VARCHAR(50)) |
 * +-------------------+-----------------+------------------+---------------+----------------------+
 * |           ドラゴン|                7|                 6|              6|                (null)|
 * +-------------------+-----------------+------------------+---------------+----------------------+
 * |         ワイバーン|                3|                 4|              4|              チャージ|
 * +-------------------+-----------------+------------------+---------------+----------------------+
 * 
 * とりあえず｢ドラゴン｣の行だけは INSERT して、残りのカードは各自で追加してください。
 * 
 * 
 * ※ MySQL サーバへの接続情報を持つ Account クラスを作り、.gitignore ファイルに追加
 * 授業内で説明します。
 * 
 * */


public class CardDAO{
    String[] playerCard = new String[5];
    Connection conn = null;
    public String[] findCardData(String cardName) {
        try {
            // JDBC ドライバを指定
            Class.forName("com.mysql.jdbc.Driver");
            // データベースへの接続を確立
            conn = DriverManager.getConnection(Account.JDBC_URL,Account.DB_USER,Account.DB_PASS);
            // 下記の 'card_tbl' が カード一覧の入ったテーブル名
            String sql = "select * from CARD_TBL where name = ?";
            // SQL 文を実行する準備
            PreparedStatement pStmt = conn.prepareStatement(sql);
            // このメソッドの引数で受け取った cardName(例:ドラゴン) を 上記のSELECT 文の中の '?' に代入
            pStmt.setString(1, cardName);
            // SQL 文を実行
            ResultSet rs = pStmt.executeQuery();
            // もし SQL 文を実行した結果が存在するなら、次の行からデータを取得
            while (rs.next()) {
                playerCard[0] = rs.getString("name");
                // 下記の 3 つは String に変換して受け取っているので、後で parseInt で int に戻す必要がある
                playerCard[1] = rs.getString("attack");
                playerCard[2] = rs.getString("defence");
                playerCard[3] = rs.getString("cost");
                playerCard[4] = rs.getString("ability");
            }
        }
        // 例外が起こるとコンソールに出力される
        catch (SQLException | ClassNotFoundException e) {
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
        return playerCard;
    }
}