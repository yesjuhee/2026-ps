import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int[] v;
    int[] dp;
    int n;
    
	public static void main(String[] args) throws IOException {
        /*
        dp[i] : i 번째 원소를 마지막으로 쓴 가장 큰 증가하는 부분 수열
        답 -> Max(dp)

        dp[i] => v[0:i] 중 v[i] 보다 작은 값을 가지는 애들 중 가장 큰 값 + 자기자신
        dp[0] => v[0]

        시간복잡도?
        dp 테이블 채우기 -> O(N * N)
        최대값 찾기 -> O(N)
        */
        new Main().solution();
	}

    public void solution() throws IOException {
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = new int[n];
        dp = new int[n];
        for (int i = 0; i < n; i++) {
            v[i] = Integer.parseInt(st.nextToken());
        }

        dp[0] = v[0]; 
        for (int i = 1; i < n; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (v[j] < v[i]) {
                    max = Math.max(max, dp[j]);
                }
            }
            dp[i] = max + v[i];
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            result = Math.max(result, dp[i]);
        }
        System.out.println(result);
    }
}
