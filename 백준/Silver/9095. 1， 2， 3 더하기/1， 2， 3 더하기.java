import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int t;
    int[] nums;
    int max = 0; // 입력 값 중 최대값
    StringBuilder sb = new StringBuilder();
    
	public static void main(String[] args) throws IOException {
        /*
        v(k) = v(k-1) + v(k-2) + v(k-3)
        v(1) = 1 (1)
        v(2) = 1 1, 2 (2)
        v(3) = 1 1 1, 2 1, 1 2, 3 (4)
        */
        new Main().solution();
	}

	public void solution() throws IOException {
        // 입력
        t = Integer.parseInt(br.readLine());
        nums = new int[t];
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            nums[i] = n;
            max = Math.max(max, n);
        }
        // 최대값 사이즈의 dp 테이블
        int[] dp = new int[max + 1];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        for (int i = 4; i <= max; i++) {
            dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
        }

        // 출력
        for (int i = 0; i < t; i++) {
            sb.append(dp[nums[i]]).append("\n");
        }
        System.out.println(sb);
	}
}
