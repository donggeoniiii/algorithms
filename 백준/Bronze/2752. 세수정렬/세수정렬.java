// 세수정렬

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int[] nums;

    static String[] line;

    public static void main(String[] args) throws IOException {
        init();
        simulation();
        print();
    }

    private static void init() throws IOException {
        nums = new int[3];
        line = br.readLine().split(" ");
    }

    private static void simulation() {
        for (int i = 0; i < 3; i++) {
            nums[i] = Integer.parseInt(line[i]);
        }

        Arrays.sort(nums);
    }

    private static void print() {
        StringBuilder sb = new StringBuilder();
        for (int num : nums) {
            sb.append(num).append(" ");
        }

        System.out.println(sb);
    }
}