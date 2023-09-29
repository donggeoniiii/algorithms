import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 도감에 있는 포켓몬의 수, 문제의 개수
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // 포켓몬 도감
        HashMap<String, String> pokemon = new HashMap<>();
        HashMap<String, String> pokemonIndex = new HashMap<>();

        // 도감에 저장
        for (int idx = 1; idx <= n; idx++) {
            String name = br.readLine();
            pokemon.put(name, String.valueOf(idx));
            pokemonIndex.put(String.valueOf(idx), name);
        }

        // 문제 맞추기
        StringBuilder sb = new StringBuilder();
        for (int idx = 1; idx <= m; idx++) {
            String question = br.readLine();

            // 입력이 숫자면
            if (question.charAt(0) - '0' >= 0 && question.charAt(0) - '0' <= 9){

                // 해당하는 키가 있으면 문자 반환
                if (pokemonIndex.containsKey(question))
                    sb.append(pokemonIndex.get(question)).append("\n");
            }
            // 입력이 문자면
            else {
                // 숫자 반환
                if (pokemon.containsKey(question))
                    sb.append(pokemon.get(question)).append("\n");
            }
        }

        // 정답 출력
        System.out.println(sb);
    }
}