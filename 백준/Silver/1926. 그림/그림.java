import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		int sero = Integer.parseInt(st.nextToken());
		int garo = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[sero][garo];
		
		for(int i=0; i<sero; i++) {
			st = new StringTokenizer(bf.readLine());
			for(int j=0; j<garo; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		
		Queue<int[]> queue = new ArrayDeque<>();
		
		// 그림의 개수
		int cnt = 0; 
		// 그림의 크기
		int max_size = 0;
		
		for(int x=0; x<sero; x++) {
			for(int y=0; y<garo; y++) {
				if(map[x][y] == 1) {
					cnt++;
					int size = 1;
					map[x][y] = 0;
					
					int[] qArr = new int[2];
					qArr[0] = x; qArr[1] = y;
					queue.offer(qArr);
					
					// queue의 값으로 사방탐색
					while(!queue.isEmpty()) {
						int[] now = queue.poll();
						int cx = now[0]; int cy = now[1];
						
						for(int i=0; i<4; i++) {
							// 아래는 x
							if(cx+dx[i] < 0 || cx+dx[i] >= sero 
									|| cy+dy[i] < 0 || cy+dy[i] >= garo) continue;
							if(map[cx+dx[i]][cy+dy[i]] == 1) {
								map[cx+dx[i]][cy+dy[i]] = 0;
								size++;
								//
								now = new int[2];
								now[0] = cx+dx[i];
								now[1] = cy+dy[i];
								queue.offer(now);
							}
						}
					}
					if(size > max_size) { max_size = size; }
				}
			}
		}
		if(cnt != 0) System.out.println(cnt + "\n" + max_size);
		else System.out.println(0 + "\n" + 0);
		
		bw.close();
	}
}
