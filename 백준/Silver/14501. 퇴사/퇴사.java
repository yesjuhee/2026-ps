import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n;
    int[] dp;
    int[] time;
    int[] pay;
    
	public static void main(String[] args) throws IOException {
        /*
        최대 수익 구하기 -> dp
        */
        new Main().solution();
	}

    public void solution() throws IOException {
        n = Integer.parseInt(br.readLine());
        dp = new int[n+1];
        time = new int[n+1];
        pay = new int[n+1];
        
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            time[i + 1] = Integer.parseInt(st.nextToken());
            pay[i + 1] = Integer.parseInt(st.nextToken());
        }

        if (time[n] == 1) dp[n] = pay[n];
        else dp[n] = 0;
        for (int i = n - 1; i >= 1; i--) {
            // i 번째 넣는게 불가능할 경우
            if (i - 1 + time[i] > n) {
                dp[i] = dp[i + 1];
                continue;
            }
            if (i + time[i] > n) {
                dp[i] = Math.max(dp[i + 1], pay[i]);
            } else {
                dp[i] = Math.max(dp[i+1], pay[i] + dp[i + time[i]]);    
            }
        }

        System.out.println(dp[1]);
    }
}
