package q005;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Q005 データクラスと様々な集計
 *
 * 以下のファイルを読み込んで、WorkDataクラスのインスタンスを作成してください。
 * resources/q005/data.txt
 * (先頭行はタイトルなので読み取りをスキップする)
 *
 * 読み込んだデータを以下で集計して出力してください。
 * (1) 役職別の合計作業時間
 * (2) Pコード別の合計作業時間
 * (3) 社員番号別の合計作業時間
 * 上記項目内での出力順は問いません。
 *
 * 作業時間は "xx時間xx分" の形式にしてください。
 * また、WorkDataクラスは自由に修正してください。
 *
[出力イメージ]
部長: xx時間xx分
課長: xx時間xx分
一般: xx時間xx分
Z-7-31100: xx時間xx分
I-7-31100: xx時間xx分
T-7-30002: xx時間xx分
（省略）
194033: xx時間xx分
195052: xx時間xx分
195066: xx時間xx分
（省略）
 */
public class Q005 {
    public final static String path = "/Applications/Eclipse_2020-03.app/Contents/workspace/skill-check/src/main/resources/q005/data.txt";

    public static void main(String[] args) throws IOException {
        String WorkDataCsv = Q005.openDataFile();
        String br = System.getProperty("line.separator");
        String WorkData = WorkDataCsv.replaceAll(br, ";");
        String[] WorkDataArray = WorkData.split(";");
        WorkData workdata = new WorkData();
        for (int i = 1; i < WorkDataArray.length; i++) {
            String[] personalWorkData = WorkDataArray[i].split(",");
            Integer workTime = Integer.parseInt(personalWorkData[4]);
            workdata.setNumberWorkData(personalWorkData[0], workTime);
            workdata.setPositionWorkData(personalWorkData[2], workTime);
            workdata.setPCodeWorkData(personalWorkData[3], workTime);
        }
        Q005 q005 = new Q005();
        q005.dispWorkData(workdata);

    }

    /**
     */
    private void dispWorkData(WorkData workdata) {
        Map<String, Integer> NumberMap = workdata.getNumberWorkData();
        Map<String, Integer> positionMap = workdata.getPositionWorkData();
        Map<String, Integer> pCodeMap = workdata.getPCodeWorkData();
        printWorkData(positionMap);
        printWorkData(pCodeMap);
        printWorkData(NumberMap);
    }

    /**
     */
    private void printWorkData(Map<String, Integer> positionMap) {

        for (Entry<String, Integer> wordData : positionMap.entrySet()) {
            int hour = wordData.getValue() / 60;
            int min  =  wordData.getValue() % 60;
            System.out.println(wordData.getKey() + " : " + hour + "時間" + min + "分");
        }
    }

    /**
     * データファイルを開く resources/q005/data.txt
     *
     * @throws IOException
     */
    private static String openDataFile() throws IOException {
        Path file = Paths.get(path);
        String text = Files.readString(file);
        return text;
    }
}
// 完成までの時間: 1時間 45分