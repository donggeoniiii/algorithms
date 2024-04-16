import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(stringTokenizer.nextToken());
        int M = Integer.parseInt(stringTokenizer.nextToken());

        int[] snakeAndladder = new int[101];
        boolean[] isVisited = new boolean[101];
        int[] count = new int[101];

        for(int i = 0 ; i < N+M ; i++){
            stringTokenizer  = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(stringTokenizer.nextToken());
            int v = Integer.parseInt(stringTokenizer.nextToken());

            snakeAndladder[u] = v;
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        count[1] = 0;
        isVisited[1] = true;

        while(!queue.isEmpty()){
            int now = queue.poll();

            if(now == 100){
                System.out.println(count[now]);
                break;
            }

            for(int i = 1; i <= 6; i++){
                int next = now + i;

                if(100 < next || isVisited[next])  continue;

                if(snakeAndladder[next] != 0){
                    if(!isVisited[snakeAndladder[next]]){
                        isVisited[snakeAndladder[next]] = true;
                        count[snakeAndladder[next]] = count[now] + 1;
                        queue.offer(snakeAndladder[next]);
                    }
                }
                else{
                    isVisited[next] = true;
                    count[next] = count[now] + 1;
                    queue.offer(next);
                }
            }
        }
    }
}