import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n, m;
    StringBuilder sb = new StringBuilder();
    int[] arr; // 크기 m
    
	public static void main(String[] args) throws IOException {
        /*
        비 내림차순, 중복 가능
        */
        new Main().solution();
	}

	public void solution() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[m];

        func(0);

        System.out.println(sb);
	}

    /*
    k에 추가
    직전 것 보다 크게
    */
    public void func(int k) {
        if (k == m) {
            for (int i = 0; i < m; i++) {
                sb.append(arr[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        // 0 ~ k-1 까진 추가가 됨
        if (k != 0) {
            // 직전 것 확인
            int prev = arr[k - 1];
            for (int i = prev; i <= n; i++) {
                arr[k] = i;
                func(k + 1);
            }
        } else {
            //k == 0
            for (int i = 1; i <= n; i++) {
                arr[k] = i;
                func(k + 1);
            }            
        }
    }
}
