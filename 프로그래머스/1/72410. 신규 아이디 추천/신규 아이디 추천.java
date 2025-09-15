import java.util.*;

class Solution {
    public String solution(String new_id) {
        String s1 = level1(new_id);
        
        String s2 = level2(s1);
        
        String s3 = level3(s2);
        
        String s4 = level4(s3);
        
        String s5 = level5(s4);
        
        String s6 = level6(s5);
        
        return level7(s6);
    }
    
    // 대 -> 소 치환
    String level1(String id) {
        
        return id.toLowerCase();
    }
    
    // - _ . 제외 특수문자 제거
    String level2(String id) {
        StringBuilder sb = new StringBuilder();
        
        for (char c : id.toCharArray()) {
            if ('0' <= c && c <= '9') sb.append(c);
            else if ('a' <= c && c <= 'z') sb.append(c);
            else if (c == '-' || c == '_' || c == '.') sb.append(c);
        }
        
        return sb.toString();
    }
    
    // 마침표 줄이기
    String level3(String id) {
        StringBuilder sb = new StringBuilder();
        
        char prev = id.charAt(0);
        sb.append(prev);
        for (int i = 1; i < id.length(); i++) {
            char cur = id.charAt(i);
            if (prev == '.' && prev == cur) continue;
            else {
                sb.append(cur);
                prev = cur;
            }
        }
        
        return sb.toString();
    }
    
    // 마침표 제거
    String level4(String id) {
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < id.length(); i++) {
            char cur = id.charAt(i);
            
            if (i == 0 || i == id.length() - 1) {
                if (cur == '.') continue;
            }
            
            sb.append(cur);
        }
        
        return sb.toString();
    }
    
    // 빈 문자열 처리
    String level5(String id) {
        
        if (id.isEmpty()) {
            return "aaa";
        }
        
        return id;
    }
    
    // 16자 이상일 경우 뒤에는 떼기
    String level6(String id) {
        
        if (id.length() >= 16) {
            // 15번째 문자가 마침표인 경우 처리하기
            id = level4(id.substring(0, 15));
        }
        
        return id;
    }
    
    // 2자 이하일 경우 길이가 3이 될때까지 마지막 문자 반복
    String level7(String id) {
        StringBuilder sb = new StringBuilder(id);
        
        if (id.length() <= 2) {
            char c = id.charAt(id.length() - 1);
            
            while (sb.length() < 3) sb.append(c);
        }

        return sb.toString();
    }
}