import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n;
    int[] numbers;
    int[] ops; // 덧셈, 뺄셈, 곱셈, 나눗셈 입력 저장
    
    int max = Integer.MIN_VALUE;
    int min = Integer.MAX_VALUE;
    boolean[] used;
    int[] result; // 선택된 애들 저장
    
	public static void main(String[] args) throws IOException {
        /*
        만들 수 있는 식의 결과가 최대인 것과 최소인 것
        모든 경우의 수 -> 최대/최소
        백트래킹 & 완탐

        2 1 1 1
        n개의 연산자를 나열해 놓고 쓰기
        */
        new Main().solution();
	}

    public void solution() throws IOException {
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        numbers = new int[n];
        ops = new int[n - 1];
        used = new boolean[n - 1];
        result = new int[n - 1];
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        int idx = 0;
        for (int i = 0; i < 4; i++) {
            int opsCnt = Integer.parseInt(st.nextToken());
            for (int j = 0; j < opsCnt; j++) {
                ops[idx++] = i;
            }
        }

        // 백트래킹으로 완탐
        func(0);

        System.out.println(max);
        System.out.println(min);
    }

    // k 번째 선택
    void func(int k) {
        if (k == n - 1) {
            int answer = numbers[0];
            for (int i = 0; i < n - 1; i++) {
                if (result[i] == 0) {
                    answer += numbers[i + 1];
                } else if (result[i] == 1) {
                    answer -= numbers[i + 1];
                } else if (result[i] == 2) {
                    answer *= numbers[i + 1];
                } else {
                    answer /= numbers[i + 1];
                }
            }
            min = Math.min(min, answer);
            max = Math.max(max, answer);
            return;
        }

        for (int i = 0; i < n - 1; i++) {
            if (used[i]) continue;
            result[k] = ops[i];
            used[i] = true;
            func(k + 1);
            used[i] = false;
        }
    }
}
