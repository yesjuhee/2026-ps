import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n, m;
    int[] tmp; // 출력할 배열 저장
    StringBuilder sb = new StringBuilder();
    
	public static void main(String[] args) throws IOException {
        /*
        N개 중 M개 중복 수열
        사전 순 증가

        void func(int k) : k 번째에 추가할 차례
        그냥 다음 숫자 순서대로 넣어주기
        k == m 이면 추가
        */
        new Main().solution();
	}

	public void solution() throws IOException {
        // 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        tmp = new int[m];

        func(0);

        System.out.println(sb);
	}

    public void func(int k) {
        if (k == m) {
            for (int i = 0; i < m; i++) {
                sb.append(tmp[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 1; i <= n; i++) {
            tmp[k] = i;
            func(k + 1);
        }
    }
}
