import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private int n;
    private long[][] b;

    public void answer() throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        b = new long[n + 1][2];

        // DP 계산
        b[1][0] = 0;
        b[1][1] = 1;
        for (int i = 2; i <= n; i++) {
            b[i][0] = b[i - 1][0] + b[i - 1][1];
            b[i][1] = b[i - 1][0];
        }

        System.out.println(b[n][0] + b[n][1]);
        br.close();
    }

    public static void main(String[] args) throws IOException {
        new Main().answer();
    }
}
