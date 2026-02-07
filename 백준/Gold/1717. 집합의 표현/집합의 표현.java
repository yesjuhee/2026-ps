import java.io.*;
import java.util.*;

class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n, m;
    int[] parent;
    int[] rank;

    StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
        /*
        */
        new Main().solution();
	}

    public void solution() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];
        rank = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            if (cmd == 0) {
                union(a, b);
            } else {
                if (find(a) == find(b)) sb.append("YES").append("\n");
                else sb.append("NO").append("\n");
            }
        }

        System.out.print(sb);
    }

    void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) return;
        if (rank[a] > rank[b]) {
            parent[b] = a;
        } else if (rank[a] < rank[b]) {
            parent[a] = b;
        } else {
            parent[b] = a;
            rank[a]++;
        }
    }

    int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
}
