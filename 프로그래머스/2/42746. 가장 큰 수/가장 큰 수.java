import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        String[] strs = Arrays.stream(numbers)
                            .mapToObj(String::valueOf)
                            .sorted((a, b) -> (b + a).compareTo(a + b)) // 앞뒤를 붙였을때 최댓값 -> 전체의 최대로 이어짐
                            .toArray(String[]::new);
        
        // 숫자가 전부 0이면 0 반환
        if (strs[0].equals("0") && strs[strs.length - 1].equals("0")) return "0";
        
        return String.join("", strs);
    }
}