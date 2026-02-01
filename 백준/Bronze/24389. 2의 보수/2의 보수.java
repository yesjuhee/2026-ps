import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
	public static void main(String[] args) throws IOException {
        new Main().solution();
	}

	public void solution() throws IOException {
        int n = Integer.parseInt(br.readLine());
        
        // 보수 만들기
       int b = (~n) + 1;

        // 서로 다른 비트 수 출력
        int cnt = 0;
        for (int i = 0; i < 32; i++) {
            int b1 = n & 1; // 1번째 자리
            int b2 = b & 1;
            if (b1 != b2) cnt++;
            n >>= 1;
            b >>= 1;
        }

        System.out.println(cnt);
	}
}
