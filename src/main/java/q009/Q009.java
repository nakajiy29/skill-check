package q009;

import java.util.Scanner;

/**
 * Q009 重い処理を別スレッドで実行
 *
 * 標準入力から整数を受け取り、別スレッドで素因数分解して、その整数を構成する2以上の素数を求めるプログラムを記述してください。 -
 * 素因数分解は重い処理であるため、別スレッドで実行してください - 標準入力から整数を受け取った後は、再度標準入力に戻ります -
 * 空文字が入力された場合は、素因数分解を実行中の状態を表示します（「実行中」あるいは処理結果を表示） -
 * 素因数分解の効率的なアルゴリズムを求めるのが問題ではないため、あえて単純なアルゴリズムで良い（遅いほどよい）
 * （例えば、2および3以上の奇数で割り切れるかを全部チェックする方法でも良い） - BigIntegerなどを使って、大きな数値も扱えるようにしてください
 * [実行イメージ] 入力) 65536 入力) 65536: 実行中 <-- もし65536の素因数分解が実行中だった場合はこのように表示する 入力)
 * 12345 入力) 65536: 2 <--
 * 実行が終わっていたら結果を表示する。2の16乗だが、16乗の部分の表示は不要（複数溜まっていた場合の順番は問わない） 12345: 実行中 入力)
 * 12345: 3,5,823
 */
public class Q009 {
    private static int value;
    private static Thread myTask;

    /**
     * main method
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        System.out.println("入力) ");
        Scanner s = new Scanner(System.in);
        boolean isfirst = true;
        try {
            while (s.hasNext()) {
                value = Integer.valueOf(s.next());
                if (isfirst) {
                    myTask = new Thread(new MyTask());
                    myTask.start();
                    isfirst = false;
                } else {
                    if (myTask.isAlive()) {
                        System.out.println(value + ": 実行中");
                        Thread.sleep(500L);
                    } else {
                        myTask = new Thread(new MyTask());
                        myTask.start();
                    }
                }
                System.out.println("入力) ");

            }
        } finally {
            s.close();
        }
    }

    /**
     * 入力された値を取得
     * @return int 入力された値
     */
    public static int getValue() {
        return value;
    }
}
// 完成までの時間: 2時間 00分