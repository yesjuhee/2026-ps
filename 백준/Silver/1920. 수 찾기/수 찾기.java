import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    private int n;
    private Set<Long> a = new HashSet<>();
    private int m;

    public void answer() throws IOException {
        // 1. 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a.add(Long.parseLong(st.nextToken()));
        }
        m = Integer.parseInt(br.readLine());
        // 2. m개의 수 읽어서 포함 여부 판단
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            long key = Long.parseLong(st.nextToken());
            if (a.contains(key)) {
                sb.append(1).append('\n');
            } else {
                sb.append(0).append('\n');
            }
        }
        // 3. 출력
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        new Main().answer();
    }
}
