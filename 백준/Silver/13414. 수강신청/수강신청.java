import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int k, l;

	public static void main(String[] args) throws IOException {
        new Main().solution();
	}

    public void solution() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        k = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        Set<String> s = new LinkedHashSet<>();
        for (int i = 0; i < l; i++) {
            String id = br.readLine();
            if (s.contains(id)) s.remove(id);
            s.add(id);
        }
        StringBuilder sb = new StringBuilder();

        int i = 0;
        for (String id: s) {
            if (i == k) break;
            sb.append(id).append("\n");
            i++;
        }

        System.out.println(sb);
    }
}
