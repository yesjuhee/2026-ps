import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n, m;
    int[] arr;
    StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
        /*
        int[] arr // 길이 m + 1 (1 ~ m 사용)
        
        백트래킹
        func(int i, int k) // i 번 위치에 k를 추가한 다음의 연산
         그 다음 k 보다 큰 것만 추가

        i == m -> 출력하고 반환
        */
		new Main().solution();
	}

	public void solution() throws IOException {
        // 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[m + 1];

        func(0, 0);

        // 출력
        System.out.println(sb);
	}

    void func(int pos, int k) { // pos 길이 까지, 제일 마지막에 k를 추가한 경우
        if (pos == m) { // m 번째 까지 다 추가 함
            for (int i = 1; i <= m; i++) {
                sb.append(arr[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        // 다음 숫자 추가
        for (int i = k + 1; i <= n; i++) { // 1, 2, 3
            arr[pos + 1] = i;
            func(pos + 1, i);
        }
    }
}
