import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 시간 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        int inputHour = Integer.parseInt(st.nextToken());
        int inputMinute = Integer.parseInt(st.nextToken());

        // 요리하는데 걸리는 시간
        int cookingTime = Integer.parseInt(br.readLine());

        // 시 -> 분
        int min = 60 * inputHour + inputMinute;
        min += cookingTime;

        // 시간 계산
        int hour = (min / 60) % 24;
        int minute = min % 60;

        // 출력
        StringBuilder sb = new StringBuilder();
        sb.append(hour).append(" ").append(minute);
        System.out.println(sb);
    }
}