// 회장뽑기

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    // 회원수
    static int n;
    
    // 인접배열, 거리 저장
    static int[][] adj;

    // 초기화용 거리
    static final int INF = 99999;

    // 각 회원까지 거리합
    static int[] totalDist;
    
    // 회원 후보
    static ArrayList<Integer> candidates;
    
    // 회장 점수(최솟값)
    static int minPoint;
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        // 회원수
        n = input.nextInt();
        
        // 배열 초기화
        adj = new int[n+1][n+1];
        totalDist = new int[n+1];
        candidates = new ArrayList<>();
        minPoint = Integer.MAX_VALUE;

        // 최소 거리 계산을 위해 인접배열은 아주 큰 값으로 초기화
        for (int src = 1; src <= n; src++){
            for (int dest = 1; dest <= n; dest++){
                if (src != dest)
                    adj[src][dest] = INF;
            }
        }

        // 정보 입력
        boolean terminated = false;
        while (!terminated){
            int src = input.nextInt();
            int dest = input.nextInt();
            
            // 만약 입력값이 -1 -1 이면
            if (src == -1 && dest == -1)
                // 즉시 종료
                terminated = true;
            else {
                // 인접배열에 값 저장
                adj[src][dest] = 1;
                adj[dest][src] = 1;
            }
        }
        
        // floyd-warshall : 경유지 정해놓고 최소거리 찾기
        for (int joint = 1; joint <= n; joint++) {
            for (int src = 1; src <= n; src++) {
                for (int dest = 1; dest <= n; dest++) {
                    // 최소거리 비교해서 넣기
                    adj[src][dest] = Math.min(adj[src][dest], adj[src][joint] + adj[joint][dest]);
                }
            }
        }

        // 점수 계산하기
        for (int src = 1; src <= n; src++){
            for (int dest = 1; dest <= n; dest++){
                // 길이 없는 경우가 아닐 때만
                if (adj[src][dest] != INF)
                    totalDist[src] = Math.max(totalDist[src], adj[src][dest]);
            }
        }
        // 최솟값 찾기
        for (int person = 1; person <= n; person++){
            // 최솟값이 갱신되면
            if (totalDist[person] < minPoint){
                // 후보목록 초기화 후 해당 후보 회장후보에 추가
                candidates.clear();
                candidates.add(person);
                
                // 최솟값 갱신
                minPoint = totalDist[person];
            }
            
            // 최솟값과 같으면
            else if (totalDist[person] == minPoint)
                // 후보 추가
                candidates.add(person);
        }
        
        // 정답 형식대로 저장
        sb.append(minPoint).append(" ").append(candidates.size()).append("\n");
        for (int candidate : candidates)
            sb.append(candidate).append(" ");

        // 정답 출력
        System.out.println(sb);
    }
}