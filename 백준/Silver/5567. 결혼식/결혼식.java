import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    // 친구, 인적 네트워크 수
    static int n, m;

    // 연결리스트
    static LinkedList<Integer>[] adj;

    // dfs를 위한 방문 배열
    static boolean[] visited;

    // dfs
    static void dfs(int cur, int cnt) {
        // base case: 만약 재귀 카운트가 2이면 종료
        if (cnt >= 2){
            return;
        }

        // recursive case
        for (int next : adj[cur]){

            // 이어지는 애들 초대(중복해도 일단 세라고 하자)
            visited[next] = true;

            // 재귀 진행
            dfs(next, cnt+1);
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // 친구 수, 인적 네트워크 수
        n = input.nextInt();
        m = input.nextInt();

        // 인접리스트 생성
        adj = new LinkedList[n+1];

        // 리스트 초기화
        for (int person = 1; person <= n; person++){
            adj[person] = new LinkedList<>();
        }

        // 네트워크 정보 추가
        for (int edge = 1; edge <= m; edge++){
            int a = input.nextInt();
            int b = input.nextInt();

            // 서로 알아야 하니까 둘다 추가
            adj[a].add(b);
            adj[b].add(a);
        }

        // dfs를 위한 방문배열 초기화
        visited = new boolean[n+1];

        // 자기 자신을 세지 않도록 체크
        visited[1] = true;

        // dfs on
        dfs(1, 0);

        // 초대해야 하는 사람 수 세기 -> 방문배열에서 true인 애들 수
        int invitedFriendCnt = 0;
        for (int person = 2; person <= n; person++){
            // 본인을 제외한 친구들 중에서 몇명이 방문예정인지 세기
            if (visited[person])
                invitedFriendCnt++;
        }
        System.out.println(invitedFriendCnt);

        input.close();
    }
}