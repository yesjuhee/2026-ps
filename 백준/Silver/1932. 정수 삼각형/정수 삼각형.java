import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
	public static void main(String[] args) throws IOException {
        /*
        합이 최대가 되는 경로에 있는 수의 합
        n개 높이
        모든 경로의 최대 값 구하기

        dp[i][j] : (i, j)를 선택했을 때 최대값 경로의 합
        dp[i][j] = max(dp[i-1][j-1], dp[i-1][j]) + v[i][j]
        j의 범위 주의

        답: dp[n-1][0:n-1] 의 최대값
        */
        new Main().solution();
	}

	public void solution() throws IOException {
        int n;
        int dp[][];
        int v[][];
        // 입력
        n = Integer.parseInt(br.readLine());
        dp = new int[n][n];
        v = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j <= i; j++) {
                v[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // dp
        dp[0][0] = v[0][0];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    dp[i][j] = dp[i-1][j] + v[i][j];
                } else if (j == i) {
                    dp[i][j] = dp[i-1][j-1] + v[i][j];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]) + v[i][j];
                }
            }
        }

        // 최대값 출력
        int max = 0;
        for (int j = 0; j < n; j++) {
            max = Math.max(max, dp[n-1][j]);
        }
        System.out.println(max);
	}
}
