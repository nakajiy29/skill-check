package q003;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;

/**
 * Q003 集計と並べ替え
 *
 * 以下のデータファイルを読み込んで、出現する単語ごとに数をカウントし、アルファベット辞書順に並び変えて出力してください。
 * resources/q003/data.txt
 * 単語の条件は以下となります
 * - "I"以外は全て小文字で扱う（"My"と"my"は同じく"my"として扱う）
 * - 単数形と複数形のように少しでも文字列が異れば別単語として扱う（"dream"と"dreams"は別単語）
 * - アポストロフィーやハイフン付の単語は1単語として扱う（"isn't"や"dead-end"）
 *
 * 出力形式:単語=数
 *
[出力イメージ]
（省略）
highest=1
I=3
if=2
ignorance=1
（省略）

 * 参考
 * http://eikaiwa.dmm.com/blog/4690/
 */
public class Q003 {
    public final static String path = "/Applications/Eclipse_2020-03.app/Contents/workspace/skill-check/src/main/resources/q003/data.txt";

    public static void main(String[] args) throws IOException {
        String text = Q003.openDataFile();
        String textFormat = text.replaceAll("[.,;]", "");
        String br = System.getProperty("line.separator");
        textFormat = textFormat.replaceAll(br, " ");
        String[] textArray = textFormat.split(" ");
        Arrays.sort(textArray, String.CASE_INSENSITIVE_ORDER);
        String beforeWord = "";
        int index = 0;
        for (String word : textArray) {
            if (!beforeWord.isEmpty() && !Objects.equals(beforeWord, word)) {
                System.out.println(beforeWord + "=" + index);
                index = 0;
            }
            beforeWord = word;
            index++;
        }
    }
    /**
     * データファイルを開く
     * resources/q003/data.txt
     * @throws IOException
     */
    private static String openDataFile() throws IOException {
        Path file = Paths.get(path);
        String text = Files.readString(file);
        return text;
    }

}
// 完成までの時間: 約2時間
