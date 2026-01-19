import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n, m;
    int[] num; // 입력 (N)
    // boolean[] isused; // N
    int[] arr; // 인덱스 (M)

    StringBuilder sb = new StringBuilder();
    
	public static void main(String[] args) throws IOException {
        /*
        비 내림차순, 중복 가능
        */
        new Main().solution();
	}

	public void solution() throws IOException {
        // 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        num = new int[n];
        // isused = new boolean[n];
        arr = new int[m];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        // 정렬
        Arrays.sort(num);

        // 재귀 출력
        func(0);

        System.out.println(sb);
	}

    // k 번째 까지 선택할 차례
    public void func(int k) {
        if (k == m) {
            for (int i = 0; i < m; i++) {
                int idx = arr[i];
                sb.append(num[idx]).append(" ");
            }
            sb.append("\n");
            return;
        }

        // 중복 안됨 & 증가 순
        // 몇 번째 숫자 까지 썼는지 확인
        int st = 0; // k == 0
        if (k != 0) {
            st = arr[k - 1] + 1; // 바로 직전에 쓰인 인덱스
        }
        for(int i = st; i < n; i++) {
            arr[k] = i;
            func(k + 1);
        }
    }
}
