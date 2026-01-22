import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
	public static void main(String[] args) throws IOException {
        /*
        구: 모든 집을 칠하는 비용의 최솟값
        
        연속되서 같은 색 사용 X
        빨/초/파 비용
        dp[i][0-2] : i번째 집을 색깔(0-2)로 칠했을 때 누적 최소값
        
        dp[i][0] = min(dp[i-1][1], dp[i-1][2]) + v[i][0]
        */
        new Main().solution();
	}

	public void solution() throws IOException {
        int n;
        int[][] dp;
        int[][] v;

        // 입력
        n = Integer.parseInt(br.readLine());
        dp = new int[n][3];
        v = new int[n][3];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());    
            v[i][0] = Integer.parseInt(st.nextToken());
            v[i][1] = Integer.parseInt(st.nextToken());
            v[i][2] = Integer.parseInt(st.nextToken());
        }

        // dp 초기화
        dp[0][0] = v[0][0];
        dp[0][1] = v[0][1];
        dp[0][2] = v[0][2];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + v[i][0];
            dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + v[i][1];
            dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + v[i][2];
        }

        // 최소값 출력
        int min = Math.min(dp[n-1][0], dp[n-1][1]);
        min = Math.min(min, dp[n-1][2]);
        System.out.println(min);
	}
}
