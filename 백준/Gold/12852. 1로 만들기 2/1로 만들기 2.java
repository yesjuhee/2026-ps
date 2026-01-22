import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n;
    int[][] dp;    
    
	public static void main(String[] args) throws IOException {
        /*
        DP로 푸는 문제였는데, 과정에 대한 기록이 필요함..!
        dp[i] : 숫자 I를 만드는데 필요한 연산의 최소 횟수
        3차원으로 해서 해당 연산을 했을 때를 기준으로?
        정답을 찾은 다음에 역으로 따라가기

        dp[i][0] : 마지막에 *3을 해서 얻을 수 있는 연산의 최소 횟수

        imod3 == 0 -> dp[i][0] = min(dp[i/3][0], dp[i/3][1], dp[i/3][2]) + 1
        */
        new Main().solution();
	}

	public void solution() throws IOException {
        // 입력
        n = Integer.parseInt(br.readLine());
        dp = new int[n + 1][3];

        // dp 테이블 채우기
        // dp[2][1] = 1;
        // dp[2][2] = 1;
        for (int i = 2; i <= n; i++) {
            if (i % 3 == 0) {
                int min = min(dp[i/3][0], dp[i/3][1], dp[i/3][2]);
                dp[i][0] = min + 1;
            }
            if (i % 2 == 0) {
                int min = min(dp[i/2][0], dp[i/2][1], dp[i/2][2]);
                dp[i][1] = min + 1;
            }
            int min = min(dp[i-1][0], dp[i-1][1], dp[i-1][2]);
            dp[i][2] = min + 1;
        }

        // 결과 값 구하기
        
        // 하나씩 거꾸로
        StringBuilder sb = new StringBuilder();
        // for (int j = 0; j < 3; j++) {
        //     for (int i = 1; i <= n; i++) {
        //         sb.append(dp[i][j]).append(" ");
        //     }
        //     sb.append("\n");
        // }
        
        int k = n;
        int idx = getMinimumIndex(k);
        sb.append(dp[n][idx]).append("\n"); // 횟수
        sb.append(k).append(" ");
        while(k != 1) { // 1 까지 가면 탈출
            if (idx == 0) { // 마지막 연산 * 3
                k /= 3;                
                idx = getMinimumIndex(k); // 다음 숫자 찾기;
            } else if (idx == 1) {
                k /= 2;
                idx = getMinimumIndex(k);
            } else {
                k--;
                idx = getMinimumIndex(k);
            }
            sb.append(k).append(" ");
        }
        System.out.println(sb);
	}

    // i 번째 수에서 가장 작은 수를 만든 연산 찾기
    public int getMinimumIndex(int k) {
        int idx = 2;
        int min = dp[k][2];
        for (int i = 0; i <= 1; i++) {
            if (dp[k][i] == 0) continue; // 불가능한 연산
            if (dp[k][i] < min) {
                idx = i;
                min = dp[k][i];
            }
        }     
        return idx;
    }

    public int min(int a, int b, int c) {
        int min = 1_000_001;
        if (a == 0 && b == 0 && c == 0) {
            return 0; // 최초 1회
        } 
        if (a != 0) {
            min = Math.min(min, a);
        }
        if (b != 0) {
            min = Math.min(min, b);
        }
        if (c != 0) {
            min = Math.min(c, min);
        }
        return min;
    }
}
