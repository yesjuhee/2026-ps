import java.util.*;

/*
1. 1, 2, 3 각자 맞은 개수 구하기
2. max 값 구하기 + 맞은 사람 수
3. max 랑 같은 사람 배열에 추가
*/

class Solution {
    public int[] solution(int[] answers) {
        int n = answers.length;
        int[] count = new int[3]; // 1~3 번 맞은 수
        
        // count 구하기
        int[][] results = new int[][]{
            {1,2,3,4,5},
            {2,1,2,3,2,4,2,5},
            {3,3,1,1,2,2,4,4,5,5},
        };
        int[] idxs = new int[]{0, 0, 0};
        for (int i = 0; i < n; i++) {
            int answer = answers[i];
            for (int j = 0; j < 3; j++) {
                if (answer == results[j][idxs[j]]) {
                    count[j]++;
                }
                idxs[j] = (idxs[j] + 1) % results[j].length;
            }
        }
        // System.out.println(Arrays.toString(count));
        
        // max 구하기
        int max = -1;
        for (int i = 0; i < 3; i++) {
            max = Math.max(max, count[i]);
        }
        
        // max랑 일치하는 수 구하기
        int winner = 0;
        for (int i = 0; i < 3; i++) {
            if (count[i] == max) winner++;
        }
        
        int[] answer = new int[winner];
        int idx = 0;
        for (int i = 0; i < 3; i++) {
            if (count[i] == max) answer[idx++] = i + 1;
        }
        return answer;
    }
}