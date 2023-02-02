import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	// line을 전역변수로 만들어 main과 기능 메소드 모두에서 참조할 수 있게 함
	public static String[] line;
	
	/*
	 * start로 시작점, 전체 size로 어디까지 지워야 할 지 확인
	 * 3등분 전 size값이 1이면 더 이상 재귀를 할 필요가 없으므로 return; 으로 재귀 종료
	 * 나머지 양쪽으로 재귀 진행
	 */
	public static void cantor(int start, int size) {
		
		if (size == 1) return;
		
		int idx = size / 3; 
		
		for (int i = start + idx; i < start + 2*idx; i++) {
			line[i] = " ";
		}
		
		cantor(start, idx);
		cantor(start + 2*idx, idx);
	}
	
	/*
	 * while문과 reader에 null 조건을 이용해 입력이 더이상 없을 때까지 진행
	 * line을 매 입력마다 새롭게 길이 조절
	 * 모든 index를 -으로 고치고 재귀 시작
	 * 끝난 후 line 출력
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String input;
		
		while((input = br.readLine()) != null) {
			
			int N = Integer.parseInt(input);
			int size = (int) Math.pow(3, N);
			
			line = new String[size];
			
			for (int i = 0; i < size; i++) line[i] = "-";
			
			cantor(0, size);
			
			for (int i = 0; i < size; i++) {
				bw.write(line[i]);
			}
			bw.write("\n");
			bw.flush();
		}
		bw.close();
	}
}