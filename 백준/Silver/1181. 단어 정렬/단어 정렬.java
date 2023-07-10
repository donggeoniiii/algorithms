// 단어정렬

import java.util.*;

public class Main {

    static class Word implements Comparable<Word>{

        String item;
        int length;
        
        // 길이로 비교
        @Override
        public int compareTo(Word o) {
            // 길이가 같으면
            if (this.length == o.length){
                // String만 사전식으로 교체
                return this.item.compareTo(o.item);
            }

            return this.length - o.length;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        // 단어 개수
        int n = input.nextInt();

        // 단어를 담을 list
        List<Word> wordList = new LinkedList<>();

        // 단어 입력
        for (int i = 0; i < n; i++) {
            String item = input.next();
            int length = item.length();

            Word word = new Word();
            word.item = item;
            word.length = length;

            // list에 추가
            wordList.add(word);
        }

        // 정렬
        Collections.sort(wordList);

        // 비교를 위한 임시변수
        String temp = "";

        // 출력
        for (Word word : wordList){
            // 만약 마지막에 들어간 값과 넣으려는 값이 같으면 스킵
            if (word.item.equals(temp))
                continue;
            // 아니면 값 추가
            sb.append(word.item).append("\n");

            // 다음 값과 비교를 위해 집어넣은 값 임시변수에 저장
            temp = word.item;
        }

        System.out.println(sb);
    }
}