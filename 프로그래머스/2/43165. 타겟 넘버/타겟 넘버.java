import java.util.*;

/*
타겟 넘버를 만드는 방법의 수

2 <= n <= 20
모든 경우의 수 -> 2^n

전체 탐색 하면 됨 -> 백트래킹?
아니면 이진수 사용?

for (i: 0~2^n) {
앞에서부터 0/1 판단
0이면 +
1이면 -
누적합 계산
target이랑 같으면 카운드
}

n = 3?
1 << 3 -> 1000 -> 8 
for (t: 0~7)
*/

class Solution {
    public int solution(int[] numbers, int target) {
        int n = numbers.length;
        int result = 0;
        
        for (int t = 0; t < (1<<n); t++) {
            // 모든 자리수 판단하기
            int tmp = t;
            int sum = 0;
            for (int i = 0; i < n; i++) {
                int binary = (tmp&1);
                
                if (binary == 0) {
                    sum += numbers[i];
                } else {
                    sum -= numbers[i];
                }
                
                tmp>>=1;
            }
            
            if (sum == target) result++;
        }
        
        return result;
    }
}