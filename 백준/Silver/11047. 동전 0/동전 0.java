import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n, k;
    int[] coins;
    
	public static void main(String[] args) throws IOException {
        new Main().solution();
	}

	public void solution() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        coins = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        int cnt = 0;
        for (int i = n - 1; i >= 0; i--) {
            int coin = coins[i];
            cnt += k / coin;
            k %= coin;
        }

        System.out.println(cnt);
	}  
}
