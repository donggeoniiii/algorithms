import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 사람의 수
		int m = Integer.parseInt(st.nextToken()); // 파티의 수
		boolean[] v = new boolean[n];

		st = new StringTokenizer(br.readLine());
		int know = Integer.parseInt(st.nextToken());
		Queue<Integer> q = new LinkedList<>(); //bfs를 위한 큐
		for (int i = 0; i < know; i++) {
			int a = Integer.parseInt(st.nextToken())-1;
			v[a] = true;
			q.add(a);
			//진실 아는 사람 처리해주기.
		}
		
		LinkedList<Integer>[] g = new LinkedList[n]; //같은 파티인 사람끼리 연결되는 그래프
		for(int i=0;i<n;i++) {
			g[i] = new LinkedList<>();
		}
		int[][] p_info = new int[m][]; //파티의 정보를 담는 배열
		for(int i=0;i<m;i++) { //파티의 수만큼 반복
			st = new StringTokenizer(br.readLine());
			int pn = Integer.parseInt(st.nextToken());
			p_info[i] = new int[pn];
			for(int j=0;j<pn;j++) {
				p_info[i][j] = Integer.parseInt(st.nextToken())-1;
			}
			for(int j=0;j<pn;j++) {
				for(int k=j+1;k<pn;k++) { 
					//같은 파티인 사람끼리 연결해준다. 1번 파티에 2,4,5번 사람이 참가한다면 서로 양방향 연결
					if(!g[p_info[i][j]].contains(p_info[i][k])) {
						g[p_info[i][j]].add(p_info[i][k]);
						g[p_info[i][k]].add(p_info[i][j]);
					}
				}
			}
		}
		
		while(!q.isEmpty()) { // 진실을 아는 사람을 시작으로 bfs 진행. 진실 전파 !!
			int x = q.poll();
			for(int nowx:g[x]) {
				if(!v[nowx]) {
					v[nowx]= true;
					q.add(nowx);
				}
			}
		}
		
		int cnt = 0;
		for(int[] p:p_info) {
			boolean check = true;
			for(int i=0;i<p.length;i++) {
				if(v[p[i]]) {
					//진실을 아는사람이 있으면 break
					check = false;
					break;
				}
			}
			if(check) cnt++; //진실이 아는 사람이 존재하지 않는다면 cnt 증가해준다.
		}
		System.out.println(cnt);
		
	}
}