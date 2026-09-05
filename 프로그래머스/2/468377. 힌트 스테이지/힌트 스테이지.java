import java.util.*;

/*
구: 모든 스테이지를 해결하는데 필요한 최소 비용

2 <= n <= 16
각 스테이지의 힌트 개수 (k) <= 20

각 스테이지에서 힌트번들을 사냐마냐에 따라 최소 비용이 달라짐
완전 탐색 -> 2^n -> 가능

for (int case = 0; case < 2<<n; case++) {
    1. case 가 1인 경우 힌트 구매 -> 각 스테이지의 힌트 개수 기록
    2. 각 스테이지에서 힌트 개수에 따른 구매 가격 보관
    min 으로 계산
}

예제1)
n : 5
hint -> 4가지 -> 2^4 경우의 수. 2^(n-1)

*/

class Solution {
    public int solution(int[][] cost, int[][] hint) {
        int min = Integer.MAX_VALUE;
        int n = cost.length;
        
        for (int c = 0; c < (2<<(n-1)); c++) {
            int costSum = 0;
            int[] hintCounts = new int[n];
            for (int i = 0; i < n - 1; i++) {
                if ((c & (1<<i)) == 0) { 
                    // i 번째가 0인 경우. i번째 스테이지 힌트권 구매함
                    int[] hintBundle = hint[i];
                    costSum += hint[i][0];
                    for (int j = 1; j < hint[i].length; j++) {
                        hintCounts[hint[i][j] - 1]++;
                    }
                }
            }
            // hint 구매한 것 바탕으로 스테이지 해결 비용 계산
            for (int i = 0; i < n; i++) {
                if (hintCounts[i] > n - 1) {
                    costSum += cost[i][n-1];
                } else {
                    costSum += cost[i][hintCounts[i]];    
                }
            }
            min = Math.min(min, costSum);
        }
        
        return min;
    }
}