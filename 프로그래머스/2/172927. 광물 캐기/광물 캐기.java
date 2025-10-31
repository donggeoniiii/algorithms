import java.util.*;

class Solution {
    final int DURABILITY = 5;
    final int[][] damages = {{1, 1, 1}, {5, 1, 1}, {25, 5, 1}};
    int[] chosenPicks = {0, 0, 0};
    int answer = Integer.MAX_VALUE;
    
    // picks[0]: 다이아, picks[1]: 철, picks[2]: 돌
    public int solution(int[] picks, String[] minerals) {
        // 미네랄을 다 캘 수 있을 만큼 곡괭이 고르기
        int req = (minerals.length % DURABILITY == 0) ? minerals.length / DURABILITY : (minerals.length / DURABILITY) + 1;
        
        System.out.println("req: " + req);
        
        choosePicks(0, req, "", picks, minerals);
        
        return answer;
    }
    
    void choosePicks(int count, int req, String cur, int[] picks, String[] minerals) {
        // 필요한 만큼 뽑았거나 더 고를 곡괭이가 없거나
        if (count >= req || allSelected(picks)) {
            String[] curPicks = cur.split("");
            answer = Math.min(calculate(curPicks, minerals), answer);
            return;
        }
        
        for (int i = 0; i < 3; i++) {
            if (chosenPicks[i] == picks[i]) continue;
            
            chosenPicks[i]++;
            choosePicks(count + 1, req, cur + i, picks, minerals);
            chosenPicks[i]--;
        }
    }
    
    boolean allSelected(int[] picks) {
        return Arrays.equals(chosenPicks, picks);
    }
    
    int calculate(String[] curPicks, String[] minerals) {
        int total = 0;
        
        int pdx = 0; // pick index;
        int remains = 5;
        for (String mineral : minerals) {
            int p = Integer.parseInt(curPicks[pdx]);
            
            int m;
            switch (mineral) {            
                case "diamond":
                    m = 0;
                    break;
                case "iron":
                    m = 1;
                    break;
                default: // case "stone"
                    m = 2;
                    break;
            }
            
            total += damages[p][m];
            remains--;
            
            if (remains == 0) {
                pdx++;
                remains = 5;
            }
            
            if (pdx >= curPicks.length) break; 
        }
        
        return total;
    }
}