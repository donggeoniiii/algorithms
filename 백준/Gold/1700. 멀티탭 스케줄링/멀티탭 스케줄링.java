// 멀티탭 스케줄링

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    // 멀티탭 구멍 개수
    static int n;

    // 사용 횟수
    static int k;

    // 사용 순서를 저장할 배열
    static List<Integer> usingSequence;

    // 총 플러그 뽑은 개수
    static int answer;

    public static void main(String[] args) throws IOException {
        init();
        solution();
        answer();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        usingSequence = new ArrayList<>();
        answer = 0;

        // 큐에 사용 순서 입력하면서 몇 번 사용하는지 저장하기
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            usingSequence.add(Integer.parseInt(st.nextToken()));
        }
    }

    private static void solution() {
        // 큐가 빌 때까지 하나씩 단자에 꽂기
        List<Integer> plug = new ArrayList<>();

        // 하나씩 차례대로 실행
        while (!usingSequence.isEmpty()){
            // 이번에 사용할 제품
            int curUsingDevice = usingSequence.remove(0);

            // 이미 꽂혀있으면 패스
            if (plug.contains(curUsingDevice)){
                continue;
            }

            // 멀티탭에 꽂으려는데 자리가 있으면 바로 꽂고 사용
            if (plug.size() < n) {
                plug.add(curUsingDevice);
            }
            // 만약 자리가 없으면, 꽂혀 있는 제품 중 뺄 우선순위 판별
            // 앞으로 사용하지 않는 제품 > 제일 나중에 사용하는 제품 > ... > 바로 다음에 사용할 제품 순
            else {
                boolean isPlugged = false;

                // 꽂혀있는 제품 중 뺄 수 있는 플러그의 위치 저장을 위한 변수
                int cdx = -1; // 현재 비교하는 제품의 플러그 위치
                int ndx = -1; // 다음에 뺄 플러그의 위치

                for (int i = 0; i < plug.size(); i++) {
                    int device = plug.get(i);
                    // 만약 다음에 사용하지 않는 제품이면 즉시 플러그 뽑고 이번 제품 사용
                    if (!usingSequence.contains(device)) {
                        plug.remove(i);
                        plug.add(curUsingDevice);
                        isPlugged = true;
                        break;
                    }
                    // 다음에 사용하는 제품이면 모든 제품이 다시 사용되는 경우에 대비해 우선순위 판별(indexOf 메소드 이용)
                    if (usingSequence.indexOf(plug.get(i)) > cdx) {
                         cdx = usingSequence.indexOf(plug.get(i));
                         ndx = i;
                    }
                }

                // 만약 모든 제품이 다음에 사용되면 가장 마지막에 사용되는 제품 플러그 뽑고 새거 끼기
                if (!isPlugged) {
                    plug.remove(ndx);
                    plug.add(curUsingDevice);
                }

                // 한번 빼는 과정을 거쳤으니까 카운트 증가
                answer++;
            }

        }

    }

    private static void answer() {
        System.out.println(answer);
    }
}