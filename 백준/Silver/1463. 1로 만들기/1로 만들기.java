import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n; // 시작 숫자
    int[] cnt;
    
	public static void main(String[] args) throws IOException {
        /*
        연산 횟수의 최소값
        10 9 3 1
        백트래킹으로 풀 수 있을듯? -> 시간 초과

        1부터 테이블 만들어서 카운트 최소값
        1 2 3 4 5 6 7 8 9 10
        0 1 1 2 3 2 3 3 2 3
        */
        new Main().solution();
	}

	public void solution() throws IOException {
        n = Integer.parseInt(br.readLine());

        cnt = new int[n + 1];
        // cnt[1] = 0;
        for (int i = 2; i <= n; i++) {
            // StringBuilder sb = new StringBuilder();
            // for (int k = 1; k <= n; k++) {
            //     sb.append(cnt[k]).append(" ");
            // }
            // System.out.println(sb);
            
            // 가능한 경우의 수 중 최소 값 찾기
            int min = 1000000;
            if (i % 3 == 0) {
                min = Math.min(min, cnt[i/3] + 1);
            }
            if (i % 2 == 0) {
                min = Math.min(min, cnt[i/2] + 1);
            }
            min = Math.min(min, cnt[i-1] + 1);
            
            cnt[i] = min;
        }

        System.out.println(cnt[n]);
	}
}
