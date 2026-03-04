import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n, m;
    Map<String, String> map = new HashMap<>();

	public static void main(String[] args) throws IOException {
        /*
        */
        new Main().solution();
	}

    public void solution() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            map.put(st.nextToken(), st.nextToken());
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            String key = br.readLine();
            sb.append(map.get(key)).append("\n");
        }
        System.out.println(sb);
    }
}
