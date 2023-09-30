// 수강신청

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 수강신청 treemap
        LinkedHashSet<String> classRegist = new LinkedHashSet<>();

        // 과목 수강인원, 대기목록의 길이
        StringTokenizer st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        // treemap에 순서대로 입력
        for (int i = 1; i <= l; i++) {
            // 이번에 접속한 학생
            String student = br.readLine();

            // 이미 있으면 뺴고 다시 넣기, 있으면 그냥 추가
            classRegist.remove(student);
            classRegist.add(student);
        }

        // 처음 보는 k개 출력
        int idx = 0;
        StringBuilder sb = new StringBuilder();
        Iterator<String> student = classRegist.iterator();
        while (student.hasNext() && idx < k) {
            sb.append(student.next()).append("\n");
            idx++;
        }

        System.out.println(sb);
    }
}