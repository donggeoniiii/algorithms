// 하얀 칸

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 흰칸의 말 수
		int white = 0;

		// 체스판 보기
		for (int r = 0; r < 8; r++) {
			String line = br.readLine();
			for (int c = 0; c < 8; c++) {
				// 만약 흰칸인데 말이 있으면
				if ((r + c) % 2 == 0 && line.charAt(c) == 'F') {
					// 흰칸 말 개수 증가
					white++;
				}
			}
		}

		// 결과 출력
		System.out.println(white);
	}
}