// 나이순 정렬

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	private static class Member {
		int age;
		String name;

		Member (int age, String name) {
			this.age = age;
			this.name = name;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		Member[] member = new Member[n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int age = Integer.parseInt(st.nextToken());
			String name = st.nextToken();
			member[i] = new Member(age, name);
		}

		Arrays.sort(member, Comparator.comparingInt(m -> m.age));

		StringBuilder sb = new StringBuilder();
		for (Member m : member) {
			sb.append(m.age).append(" ").append(m.name).append("\n");
		}
		System.out.println(sb);
	}
}