import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n;
    int[] dp;
    int[] prev;
    
	public static void main(String[] args) throws IOException {
        /*
        DP로 푸는 문제였는데, 과정에 대한 기록이 필요함..!
        dp[i] : 숫자 I를 만드는데 필요한 연산의 최소 횟수

        이전 값을 따로 기록하는 배열 만들기
        prev[i] : 숫자 I를 최소값으로 올 수 있게 하는 이전 값
        */
        new Main().solution();
	}

	public void solution() throws IOException {
        // 입력
        n = Integer.parseInt(br.readLine());
        dp = new int[n + 1];
        prev = new int[n + 1];

        // dp 테이블 채우기
        for (int i = 2; i <= n; i++) {
            int min = dp[i-1] + 1;
            if (i % 3 == 0) {
                min = Math.min(min, dp[i/3] + 1);
            }
            if (i % 2 == 0) {
                min = Math.min(min, dp[i/2] + 1);
            }
            dp[i] = min;
            
            if (min == dp[i-1] + 1) {
                prev[i] = i-1;
            } else if (i%2==0 && min==dp[i/2] + 1) {
                prev[i] = i/2;
            } else if (i%3 == 0 && min == dp[i/3] + 1) {
                prev[i] = i/3;
            }
        }

        // 결과 값 구하기
        StringBuilder sb = new StringBuilder();
        // for (int i = 1; i <= n; i++) {
        //     sb.append(prev[i]).append(" ");
        // }
        // sb.append("\n");
        sb.append(dp[n]).append("\n");        
        int k = n;
        sb.append(k).append(" ");
        while(k != 1) {
            k = prev[k];
            sb.append(k).append(" ");
        }
        System.out.println(sb);
	}
}
