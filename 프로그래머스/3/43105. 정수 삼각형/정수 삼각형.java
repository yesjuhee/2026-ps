import java.util.*;

/*
거쳐 간 숫자의 최대값을 return

h <= 500
모든 경우의 수는?
1 * 2 * * ... 2 -> 2^h

각 줄에서 최대 값만 유지하기

dp[i] = i 번째 줄에서의 최대값
dp[i] = max(dp[i-1] + v[0], dp[i-1] + v[1] + ...)

아 잘못생각함. 경로로 갈 수가 없음!!
dp 2차원 배열이여야 할 듯?

dp[i][j] -> (i, j)까지 왔을 때의 최대값

00
10 11
20 21 22
30 31 32 34
40 41 42 43 44

dp[i][j] = max(dp[i-1][j-1], dp[i-1][j]) + triangle[i][j]
단 i>=1, j>=1

dp[0][0] = triangle[0][0]

*/

class Solution {
    public int solution(int[][] triangle) {
        int h = triangle.length;
        int[][] dp = new int[h][h];
        dp[0][0] = triangle[0][0];
        
        for (int i = 1; i < h; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    dp[i][j] = dp[i-1][j] + triangle[i][j];
                } else if (j == i) {
                    dp[i][j] = dp[i-1][j-1] + triangle[i][j];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]) + triangle[i][j];
                }
            }
        }
        
        int max = -1;
        for (int i = 0; i < h; i++) {
            max = Math.max(max, dp[h-1][i]);
        }
        return max;
    }
}