// 비밀번호 찾기

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 전체 사이트 개수, 문제 수
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // 비밀번호 저장하는 해시맵
        HashMap<String, String> passwords = new HashMap<>();

        // 비밀번호 저장
        for (int i = 0; i < n; i++) {
            // 사이트 이름, 아이디
            st = new StringTokenizer(br.readLine());
            String site = st.nextToken();
            String id = st.nextToken();

            passwords.put(site, id);
        }

        // 정답 출력
        for (int i = 0; i < m; i++) {
            String site = br.readLine();
            bw.write(passwords.get(site));
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }
}