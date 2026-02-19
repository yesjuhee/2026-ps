import java.io.*;
import java.util.*;

public class Main {

	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private int n;
	private int m;
	private List<Integer>[] A;
	private boolean[] visited;
	private int count = 0;

	public static void main(String[] args) throws IOException {
		new Main().solution();
	}

	public void solution() throws IOException {
		// 입력
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());

		visited = new boolean[n + 1];
		A = new ArrayList[n + 1];
		for (int i = 1; i < n + 1; i++) {
			A[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int u = Integer.parseInt(st.nextToken());
			A[v].add(u);
			A[u].add(v);
		}

		dfs(1);
		System.out.println(count - 1);
	}

	public void dfs(int cur) {
		if (visited[cur]) return;
		visited[cur] = true;
		count++;

		for (int next: A[cur]) {
			dfs(next);
		}
	}
}
