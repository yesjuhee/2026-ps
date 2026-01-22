import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1. N, M 입력
 * 2. N개의 수 입력 받으면서 부분합 배열 만들기
 * 3. M번 구간 입력 받으면서 출력값 구하기
 */
public class Main {
    private int n, m;
    private int[] arr; // 부분합 배열

    public void answer() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // N, M 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // N개의 수 입력 받으면서 부분합 배열 만들기
        st = new StringTokenizer(br.readLine());
        arr = new int[n + 1];
        arr[0] = 0;
        for (int i = 1; i <= n; i++) {
            arr[i] = arr[i - 1] + Integer.parseInt(st.nextToken());
        }

        // M번 구간 입력받으면서 출력 값 구하기
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int startIdx = Integer.parseInt(st.nextToken());
            int endIdx = Integer.parseInt(st.nextToken());
            int sum = arr[endIdx] - arr[startIdx - 1];
            sb.append(sum).append('\n');
        }

        System.out.print(sb);
        br.close();
    }

    public static void main(String[] args) throws IOException {
        new Main().answer();
    }
}
