import java.util.*;

public class Main {

    // 정점 N개, 간선 M개
    static int N, M;

    // 대표값이 누구인지 기억하는 배열
    static int[] parent;

    // 대표값 갱신 메소드
    static int findParent(int x){
        // 만약 자기자신이 그룹의 대표값이면 그대로 자기 출력
        if (parent[x] == x)
            return x;

        // 아니면 대표값 찾으러 그 위로 이동
        else
            return parent[x] = findParent(parent[x]);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // 정점 N개, 간선 M개
        N = input.nextInt();
        M = input.nextInt();

        // 대표값 저장 배열 만들기
        parent = new int[N+1];

        // 처음엔 자기 자신으로 초기화
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        // 간선 정보 입력
        for (int edge = 1; edge <= M; edge++) {
            int src = input.nextInt();
            int dest = input.nextInt();

            // 둘의 대표값이 같지 않으면 하나로 합침(union)
            if (findParent(src) != findParent(dest))
                // 얘(parent[dest])를 대표값으로 가지는 모든 애들이 대표값이 바뀌어야 하니까
                parent[findParent(dest)] = findParent(src);
        }

        // 서로 다른 대표값의 개수를 구하기 위해 hashset 사용
        HashSet<Integer> set = new HashSet<>();

        // 대표값 넣기
        for (int i = 1; i <= N; i++){
            set.add(findParent(i));
        }

        // 정답 출력
        System.out.println(set.size());
    }
}