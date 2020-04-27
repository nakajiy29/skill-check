package q008;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Q008 埋め込み文字列の抽出
 *
 * 一般に、定数定義の場合を除いて、プログラム中に埋め込み文字列を記述するのは良くないとされています。
 * そのような埋め込み文字列を見つけるために、埋め込み文字列や埋め込み文字（"test"や'a'など）が
 * 記述された行を出力するプログラムを作成してください。
 *
 * - 実行ディレクトリ配下（再帰的にチェック）に存在するすべてのjavaファイルをチェックする
 * - ファイル名はディレクトリ付きでも無しでも良い
 * - 行の内容を出力する際は、先頭のインデントは削除しても残しても良い

[出力イメージ]
Q001.java(12): return "test";  // テストデータ
Q002.java(10): private static final DATA = "test"
Q002.java(11): + "aaa";
Q002.java(20): if (test == 'x') {
Q004.java(13): Pattern pattern = Pattern.compile("(\".*\")|(\'.*\')");

 */
public class Q008 {
    private final static String regex = "(\".*\")|(\'.*\')";

    public static void main(String args[]) throws IOException {
        Stream<File> listJavaFiles = listJavaFiles();
        listJavaFiles.forEach(
                fileName -> {
                    try {
                        printFile(fileName);
                    } catch (IOException e) {
                        // TODO 自動生成された catch ブロック
                        e.printStackTrace();
                    }
                }
        );

    }

    /**
     * 埋め込み文字列や埋め込み文字を出力する
     * @param File fileName ファイル名
     * @throws IOException
     */
    public static void printFile(File fileName) throws IOException {
            // BufferedReaderクラスのreadLineメソッドを使って1行ずつ読み込み表示する
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String data;
            int lineCount = 1;
            while ((data = bufferedReader.readLine()) != null) {
                Pattern p = Pattern.compile(regex);
                Matcher m = p.matcher(data);
                if (m.find()) {
                    System.out.println(fileName + "(" + lineCount + "): " + data);
                }
                lineCount++;
            }
            // 最後にファイルを閉じてリソースを開放する
            bufferedReader.close();
    }

    /**
     * JavaファイルのStreamを作成する
     *
     * @return
     * @throws IOException
     */
    private static Stream<File> listJavaFiles() throws IOException {
        return Files.walk(Paths.get(".")).map(Path::toFile).filter(f -> f.getName().endsWith(".java"));
    }
}
// 完成までの時間: 1時間
