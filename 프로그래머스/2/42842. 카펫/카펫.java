import java.util.*;

/*
1. yellow 소인수 분해 -> a, b -> brown 감싸기 가능한지 확인
2. 답: (b+2, a+2)
*/

class Solution {
    public int[] solution(int brown, int yellow) {
        // yellow 소인수 분해
        int a, b = 0;
        for (a = 1; a * a <= yellow; a++) {
            if (yellow % a != 0) continue;
            b = yellow / a;
            
            // brown 감싸기 가능한지 확인 
            if ((a + b + 2) * 2 == brown) break;
        }
        
        int[] answer = {b + 2, a + 2};
        return answer;
    }
}