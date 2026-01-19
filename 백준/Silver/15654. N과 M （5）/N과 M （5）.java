import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n, m;
    int[] arr; // 입력    
    boolean[] isused;

    StringBuilder sb = new StringBuilder();
    int[] result;
    
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
        arr = new int[n];
        isused = new boolean[n];
        result = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 정렬
        Arrays.sort(arr);

        // 재귀 출력
        func(0);

        System.out.println(sb);
	}

    public void func(int k) {
        if (k == m) {
            for (int i = 0; i < m; i++) {
                sb.append(result[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < n; i++) {
            if (isused[i]) continue;
            isused[i] = true;
            result[k] = arr[i]; 
            func(k + 1);
            isused[i] = false;
        }
    }
}
