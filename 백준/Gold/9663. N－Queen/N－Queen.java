import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n;
    int count = 0;
    boolean isused1[]; // i 번째 열이 사용중인지
    boolean isused2[]; // 오른쪽 아래 방향 대각선이 사용 중인지 : x + y
    boolean isused3[]; // 왼쪽 아래 방향 대각선이 사용중인지 : (x - y) + (n - 1)

	public static void main(String[] args) throws IOException {
		new Main().solution();
	}

	public void solution() throws IOException {
        n = Integer.parseInt(br.readLine());
        isused1 = new boolean[n];
        isused2 = new boolean[n * 2 - 1];
        isused3 = new boolean[n * 2 - 1];
        
        func(0);
        System.out.println(count);
	}

    public void func(int k) { // k 번째 열의 퀸 위치 확인
        if (k == n) {
            count++;
            return;
        }

        // k 열에서 가능한 행 하나씩 확인
        for (int j = 0; j < n; j++) {
            // System.out.printf("(k, j): (%d, %d)\n", k, j);
            if (isused1[j] // 열 사용중
                || isused2[k + j] // 오른쪽 아래 대각선 사용중
                || isused3[k - j + n - 1] // 왼쪽 아래 대각선 사용중
               ) continue;
            
            // 가능한 배열임
            isused1[j] = true;
            isused2[j + k] = true;
            isused3[k - j + n - 1] = true;
            func(k + 1);
            isused1[j] = false;
            isused2[j + k] = false;
            isused3[k - j + n - 1] = false;
        }
    }
}
