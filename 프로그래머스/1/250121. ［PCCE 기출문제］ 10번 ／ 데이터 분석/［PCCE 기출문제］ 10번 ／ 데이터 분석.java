import java.util.*;

class Solution {
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        HashMap<String, Integer> stringToInt = new HashMap<>();
        
        stringToInt.put("code", 0);
        stringToInt.put("date", 1);
        stringToInt.put("maximum", 2);
        stringToInt.put("remain", 3);

        int extIdx = stringToInt.get(ext);
        int sortIdx = stringToInt.get(sort_by);
        
        List<int[]> answer = new ArrayList<>();
        for (int[] record : data) {
            if (record[extIdx] >= val_ext) continue;
            
            answer.add(record);
        }
        
        answer.sort(Comparator.comparingInt(record -> record[sortIdx]));
        
        return answer.toArray(new int[answer.size()][]);
    }
}