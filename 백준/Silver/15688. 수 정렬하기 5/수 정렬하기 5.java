import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n;
    int[] counts = new int[2_000_001];
    
	public static void main(String[] args) throws IOException {
        /*
        -1,000,000 -> 0
        0 -> 1,000,000
        1,000,000 -> 2,000,000

        */
        new Main().solution();
	}

	public void solution() throws IOException {
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            int v = Integer.parseInt(br.readLine());
            counts[v + 1_000_000]++;
        }

        StringBuilder sb = new StringBuilder();
        for (int v = -1_000_000; v <= 1_000_000; v++) {
            int cnt = counts[v + 1_000_000];
            for (int c = 0; c < cnt; c++) {
                sb.append(v).append("\n");
            }
        }
        System.out.println(sb);
	}
}
