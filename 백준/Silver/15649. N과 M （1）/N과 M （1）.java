import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	StringBuilder sb = new StringBuilder();

    int n, m;
    int[] result; // m
    boolean[] issued; // n + 1

	public static void main(String[] args) throws IOException {
		new Main().solution();
	}

	public void solution() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        result = new int[m];
        issued = new boolean[n + 1];
        
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

        for (int i = 1; i < n + 1; i++) {
            if (issued[i]) continue;
            result[k] = i;
            issued[i] = true;
            func(k + 1);
            issued[i] = false;
        }
    }
}
