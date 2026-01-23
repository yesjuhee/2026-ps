import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
	public static void main(String[] args) throws IOException {
        /*
        dp[i] = dp[i-1] + dp[i-2] * 2 (i >= 3)
        */
        new Main().solution();
	}

	public void solution() throws IOException {
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n + 1];
        if (n == 1) {
            System.out.println(1);
            return;
        }
        dp[1] = 1;
        dp[2] = 3;
        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2] * 2) % 10_007;
        }
        System.out.println(dp[n]);
	}
}
