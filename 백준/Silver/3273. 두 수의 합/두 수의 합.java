import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n;
    int cnt = 0; // 결과 
    int[] freq = new int[2000000 + 5]; // 등장한 숫자 

	public static void main(String[] args) throws IOException {
        /*
        */
        new Main().solution();
	}

    public void solution() throws IOException {
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (num > x) continue;
            if (freq[x - num] == 1) cnt++;
            freq[num]++;
        }
        System.out.println(cnt);
    }
}
