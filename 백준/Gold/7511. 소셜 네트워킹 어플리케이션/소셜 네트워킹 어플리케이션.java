import java.io.*;
import java.util.*;

class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int t;
    int n;
    int k;
    int m;
    StringBuilder sb = new StringBuilder();

    int[] parent;
    int[] rank;

	public static void main(String[] args) throws IOException {
        /*
        */
        new Main().solution();
	}

    public void solution() throws IOException {
        int t = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < t; tc++) {
            sb.append("Scenario ").append(tc + 1).append(":\n");
            n = Integer.parseInt(br.readLine());
            k = Integer.parseInt(br.readLine());
            parent = new int[n];
            rank = new int[n];
            
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
            for (int i = 0; i < k; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                union(a, b);
            }

            m = Integer.parseInt(br.readLine());
            for (int i = 0; i < m; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());                
                if (find(a) == find(b)) sb.append("1\n");
                else sb.append("0\n");
            }
            sb.append("\n");
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
