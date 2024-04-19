// 빈도 정렬

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		HashMap<Integer, Integer> numCnt = new LinkedHashMap<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			int num = Integer.parseInt(st.nextToken());
			numCnt.put(num, numCnt.getOrDefault(num, 0) + 1);
		}

		// 수 리스트 저장하고 등장순서 순으로 정렬
		List<Integer> nums = new ArrayList<>(numCnt.keySet());
		nums.sort(Comparator.comparingInt(numCnt::get).reversed());

		StringBuilder sb = new StringBuilder();
		for (int num : nums) {
			for (int i = 0; i < numCnt.get(num); i++) {
				sb.append(num).append(" ");
			}
		}
		System.out.println(sb);
	}
}