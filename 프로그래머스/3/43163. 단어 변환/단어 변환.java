import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        
        if (contains(target, words)) {
            answer = solve(begin, target, words);
        }
        
        return answer;
    }
    
    static boolean contains(String a, String[] words) {
        for (String s : words) {
            if (a.equals(s)) return true;
        }
        
        return false;
    }
    
    static int solve(String begin, String target, String[] words) {
        int answer = 0;
        int n = words.length;
        
        // 최단거리 탐색을 위해 선입선출 구조의 queue 활용
        Queue<String> queue = new ArrayDeque<>();
        queue.offer(begin);
        
        // 한번 거친 단어는 다시 볼 필요 없음
        HashSet<String> notVisited = new HashSet<>(Arrays.asList(words));
        
        while (!queue.isEmpty()) {
            for (int i = 0; i < queue.size(); i++) {   
                String cur = queue.poll();

                // 정답에 도달했으면
                if (cur.equals(target)) {
                    // 현재 변환횟수 반환
                    return answer;
                }

                for (String next : notVisited.toArray(new String[notVisited.size()])) {
                    if (!isAdjacent(cur, next)) continue;

                    queue.offer(next);
                    notVisited.remove(next);
                }
            }
            
            // 순회를 마칠 때마다 변환횟수 카운트
            answer++;   
        }
        
        
        return answer;
    }
     
    static boolean isAdjacent(String cur, String next) {
        int diffCount = 0;
        
        for (int idx = 0; idx < cur.length(); idx++) {
            if (cur.charAt(idx) != next.charAt(idx)) {
                diffCount++;
            }
        }

        return diffCount == 1;
    }
}