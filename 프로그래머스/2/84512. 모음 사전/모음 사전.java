import java.util.*;

class Solution {
    static String[] aeiou = {"A", "E", "I", "O", "U"};
    static List<String> wordPool;
    
    public int solution(String word) {
        wordPool = new ArrayList<>();
        
        findWord(0, "", word);
        
        return wordPool.indexOf(word);
    }
    
    void findWord(int count, String cur, String word) {
        wordPool.add(cur);
        if (cur.equals(word)) {
            return;
        }
        
        if (count >= 5) {
            return;
        }
        
        for (int i = 0; i < 5; i++) {
            findWord(count + 1, cur + aeiou[i], word);
        }
    }
}