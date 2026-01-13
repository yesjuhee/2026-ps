import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n;
    int s;
    int[] arr;
    
    boolean[] isused;
    int sum = 0;
    int result = 0;

	public static void main(String[] args) throws IOException {
		new Main().solution();
	}

	public void solution() throws IOException {
        // 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        arr = new int[n];
        isused = new boolean[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        func(0);

        // 출력
        if (s == 0) result--;
        System.out.println(result);
	}

    void func(int k) { // k 번째 수를 넣을지 말지 판단
        if (k == n) { // 끝 까지 경우의 수 카운트
            if (sum == s) result++;
            return;
        }

        // k 번째 수를 안 더하는 경우
        func(k + 1);
        // k 번째 수를 더하는 경우
        sum += arr[k];
        func(k + 1);
        sum -= arr[k];
    }
}
