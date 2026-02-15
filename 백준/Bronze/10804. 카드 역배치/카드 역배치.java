import java.io.*;
import java.util.*;

class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int[] arr = new int[20];
    
	public static void main(String[] args) throws IOException {
        new Main().solution();
	}

    public void solution() throws IOException {
        for (int i = 0; i < 20; i++) {
            arr[i] = i + 1;
        }

        for (int i = 0; i < 10; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            reverse(a, b);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            sb.append(arr[i]).append(" ");
        }
        System.out.println(sb);
    }

    // a = 9, b = 13
    // b - a + 1 : 5
    
    public void reverse(int a, int b) {
        // 뒤집을 구간
        int[] arr2 = new int[b - a + 1];

        // 복사 
        for (int i = 0; i < b - a + 1; i++) {
            arr2[i] = arr[i + a - 1];
        }

        // 뒤집기 
        for (int i = 0; i < b - a + 1; i++) {
            arr[b - 1 - i] = arr2[i];
        }
    }
}
