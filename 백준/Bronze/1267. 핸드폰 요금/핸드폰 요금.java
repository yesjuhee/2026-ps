import java.io.*;
import java.util.*;

class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {
        new Main().solution();
	}

    public void solution() throws IOException {
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        // 영식 요금제 계산
        int y = 0;
        for (int i = 0; i < n; i++) {
            y += (arr[i] / 30 + 1) * 10;
        }
        int m = 0;
        for (int i = 0; i < n; i++) {
            m += (arr[i] / 60 + 1) * 15;
        }
        if (m == y) {
            System.out.printf("Y M %d\n", m);
        } else if (y < m) {
            System.out.printf("Y %d\n", y);
        } else {
            System.out.printf("M %d\n", m);            
        }
    }
}
