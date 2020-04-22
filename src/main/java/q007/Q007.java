package q007;

import java.util.LinkedList;
import java.util.Queue;

/**
 * q007 最短経路探索
 *
 * 壁を 'X' 通路を ' ' 開始を 'S' ゴールを 'E' で表現された迷路で、最短経路を通った場合に
 * 何歩でゴールまでたどり着くかを出力するプログラムを実装してください。 もし、ゴールまで辿り着くルートが無かった場合は -1 を出力してください。
 * なお、1歩は上下左右のいずれかにしか動くことはできません（斜めはNG）。
 *
 * 迷路データは MazeInputStream から取得してください。
 * 迷路の横幅と高さは毎回異なりますが、必ず長方形（あるいは正方形）となっており、外壁は全て'X'で埋まっています。 空行が迷路データの終了です。
 *
 *
 * [迷路の例] XXXXXXXXX XSX EX X XXX X X X X X X X X XXX X X X X XXXXXXXXX
 *
 * [答え] 14
 */
public class Q007 {
    private final static String START = "S";
    private final static String EXIT = "E";
    private final static String WALL = "X";
    private static String mazeData;
    private static int[][] data;
    private static String[][] maze;
    private static int startY = -1;
    private static int startX = -1;
    private static int exitY = -1;
    private static int exitX = -1;
    //右(1,0),下(0,1),左(-1,0),上(0,-1)の順で探索。
    private static int[] dx = {1, 0, -1, 0};
    private static int[] dy = {0, 1, 0, -1};
    // 移動した後の点の座標
    private static int tempX, tempY;
    // 最大長
    private static int MaxMapHeight, MaxMapWidth;

    public static void main(String args[]) {
        MazeInputStream stream = new MazeInputStream();
        byte[] sbyte = stream.readAllBytes();
        mazeData = new String(sbyte);
        System.out.println("[迷路]");
        System.out.println(mazeData);
        // スタート、エンド座標設定
        seCoordinatet();
        // 探索を行う
        System.out.println("[答え] " + getShortestPathLength(mazeData));
    }

    /**
     * //最短経路長を導出するメソッド
     *
     * @return
     */
    private static int getShortestPathLength(String mazeData) {
        int shortestPathLength = -1;
        Point Start = new Point(startY, startX);
        Point Exit = new Point(exitY, exitX);
        //座標を格納するキュー
        Queue<Point> que = new LinkedList<Point>();
        //スタートの座標をキューに入れて、その点の距離を0にする。
        que.offer(Start);
        data[Start.Y][Start.X] = 0;

        // キューの中身が0になるまで探索
        System.out.println("Exit.Y  : " + Exit.Y );
        System.out.println("Exit.X : " + Exit.X);
        while (que.size() > 0) {
            // キューの最初の値を取得して削除
            Point point = que.poll();
            // 取り出した値とゴールが同じであれば探索をやめる
            if (point.Y == Exit.Y && point.X == Exit.X) {
                break;
            }
            // 右(1,0),下(0,1),左(-1,0),上(0,-1)の順に探索
            for (int i = 0; i < 4; i++) {
                tempY = point.Y + dy[i];
                tempX = point.X + dx[i];

                // 移動可能でまだ訪れたことがなければキューに入れる。
                if ((0 <= tempY && tempY < MaxMapHeight) && (0 <= tempX && tempX < MaxMapWidth)
                        && (maze[tempY][tempX] != WALL && data[tempY][tempX] == -5)) {

                    //要素をこのリストの末尾 (最後の要素) に追加する。
                    que.offer(new Point(tempY, tempX));

                    //自分の周囲のマスに+1した値を格納する。
                    data[tempY][tempX] = data[point.Y][point.X] + 1;

                }
            }
        }
        //最短経路長
        shortestPathLength = data[Exit.Y][Exit.X];
        return shortestPathLength;
    }

    /**
     * スタート、エンド座標設定
     */
    private static void seCoordinatet() {
        // 配列を生成
        String[] mazeDataSplit = mazeData.split("\n");
        MaxMapHeight = mazeDataSplit.length;
        MaxMapWidth = mazeDataSplit[0].length();
        // 第一階層がy軸
        // 第二階層がx軸
        data = new int[MaxMapHeight][MaxMapWidth];
        maze = new String[MaxMapHeight][MaxMapWidth];
        for (int i = 0; i < MaxMapHeight; i++) {
            // 配列を生成
            String[] mazeString = mazeDataSplit[i].split("");
            for (int j = 0; j < MaxMapWidth; j++) {
                data[i][j] = -5;
                maze[i][j] = mazeString[j];
                switch (mazeString[j]) {
                case START:
                    startY = i;
                    startX = j;
                    break;
                case EXIT:
                    exitY = i;
                    exitX = j;
                    break;
                default:
                    break;
                }
            }
        }
    }

}
// 完成までの時間: 約4時間