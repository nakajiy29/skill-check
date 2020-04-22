package q005;

import java.util.HashMap;
import java.util.Map;

/**
 * 作業時間管理クラス 自由に修正してかまいません
 */
public class WorkData {
    /** 社員番号 */
    private Map<String, Integer> numberMap = new HashMap<>();

    /** 役職 */
    private Map<String, Integer> positionMap = new HashMap<>();

    /** Pコード */
    private Map<String, Integer> pCodeMap = new HashMap<>();

    /**
     * @param workTime2
     * @param number
     *
     */
    public void setNumberWorkData(String number, Integer workTime) {
        if (numberMap.containsKey(number)) {
            int workTimeSum = numberMap.get(number) + workTime;
            numberMap.put(number, workTimeSum);
        } else {
            numberMap.put(number, workTime);
        }
    }

    /**
     * @param workTime2
     * @param position2
     *
     */
    public void setPositionWorkData(String position, Integer workTime) {
        if (positionMap.containsKey(position)) {
            int workTimeSum = positionMap.get(position) + workTime;
            positionMap.put(position, workTimeSum);
        } else {
            positionMap.put(position, workTime);
        }
    }

    /**
     * @param workTime2
     * @param position2
     *
     */
    public void setPCodeWorkData(String pcode, Integer workTime) {
        if (pCodeMap.containsKey(pcode)) {
            int workTimeSum = pCodeMap.get(pcode) + workTime;
            pCodeMap.put(pcode, workTimeSum);
        } else {
            pCodeMap.put(pcode, workTime);
        }
    }

    /**
     * @return
     *
     */
    public Map<String, Integer> getNumberWorkData() {
        return numberMap;
    }

    /**
     * @return
     */
    public Map<String, Integer> getPCodeWorkData() {
        return pCodeMap;
    }

    /**
     * @return
     * @return
     *
     */
    public Map<String, Integer> getPositionWorkData() {
        return positionMap;
    }
}
