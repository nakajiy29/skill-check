package q004;

/**
 * Q004 ソートアルゴリズム
 *
 * ListManagerクラスをnewして、小さい順に並び変えた上でcheckResult()を呼び出してください。
 *
 * 実装イメージ:
 * ListManager data = new ListManager();
 * // TODO 並び換え
 * data.checkResult();
 *
 * - ListManagerクラスを修正してはいけません
 * - ListManagerクラスの dataList を直接変更してはいけません
 * - ListManagerクラスの比較 compare と入れ替え exchange を使って実現してください
 */
public class Q004 {
    public static void main(String[] args) {
        ListManager data = new ListManager();
        Q004.sort(data);

        data.checkResult();
    }

    /**
     * ListManagerクラスのデータをソートする
     * @param data　ListManagerクラス
     */
    public static void sort(ListManager data) {
        int size = data.size();
        // 最後の要素を除いて比較
        for (int i = 0; i < size - 1; i++) {
           for (int j = size - 1; j > i; j--) {
               // 最後から順に比較
               if (data.compare(j-1, j) == 1) {
                   // 下のデータが小さい場合後ろのデータを上に
                   data.exchange(j-1, j);
               }
           }
        }
    }
}
// 完成までの時間: 00時間 27分