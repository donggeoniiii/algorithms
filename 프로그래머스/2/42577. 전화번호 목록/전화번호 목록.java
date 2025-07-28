import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        int n = phone_book.length;
        
        /*
        번호를 받을 때마다 자료구조에 입력
        한 자리마다 부분적으로 자료구조 내에 일치하는 친구가 있는지 봐야 함
        사전순, 혹은 길이순으로 추가해야 빼먹지 않고 체크 가능
        해시가 가장 빠름
        */
        Arrays.sort(phone_book);
        
        HashSet<String> phoneNumbers = new HashSet<>();
        
        for (int i = 0; i < n; i++) {
            String cur = phone_book[i];
            for (int j = 0; j < cur.length(); j++) {
                String sub = cur.substring(0, j+1);
                
                if (phoneNumbers.contains(sub)) {
                    return false;
                }
            }
            
            phoneNumbers.add(cur);
        }
        
        return true;
    }
}