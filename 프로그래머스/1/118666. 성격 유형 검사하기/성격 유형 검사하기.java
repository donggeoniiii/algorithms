import java.util.*;

class Solution {
    public String solution(String[] survey, int[] choices) {
        int n = survey.length;
        
        String answer = "";
        String[][] types = {{"R", "T"}, {"C", "F"}, {"J", "M"}, {"A", "N"}};
        
        int[] dt = {-3, -2, -1, 0, 1, 2, 3}; // -이면 R, C, J, A +면 T, F, M, N
        int[] inclination = new int[4];
        
        for (int i = 0; i < n; i++) {
            String s = survey[i]; 
            int choice = choices[i] - 1;
            
            switch (s) {
                case "RT":
                    inclination[0] -= dt[choice];
                    break;
                case "TR":
                    inclination[0] += dt[choice];
                    break;
                case "CF":
                    inclination[1] -= dt[choice];
                    break;
                case "FC":
                    inclination[1] += dt[choice];
                    break;
                case "JM":
                    inclination[2] -= dt[choice];
                    break;
                case "MJ":
                    inclination[2] += dt[choice];
                    break;
                case "AN":
                    inclination[3] -= dt[choice];
                    break;
                default: // case "NA"
                    inclination[3] += dt[choice];
                    break;
            }
        }
        
        for (int i = 0; i < 4; i++) {
            if (inclination[i] >= 0) {
                answer += types[i][0];
            } else {
                answer += types[i][1];
            }
        }
       
        return answer;
    }
}