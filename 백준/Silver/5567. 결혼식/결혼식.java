import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n, m;
    List<Integer>[] graph;
    int cnt = 0;
    boolean[] visited;
    int[] distance;

	public static void main(String[] args) throws IOException {
        new Main().solution();
	}

    public void solution() throws IOException {
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        graph = new List[n + 1];
        visited = new boolean[n + 1];
        distance = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        // BFS 로 거리 2 인 친구 까지만 조사 + cnt
        bfs(1);

        System.out.println(cnt);
    }

    void bfs(int start) {
        Deque<Integer> q = new ArrayDeque<>();
        q.addLast(start);
        visited[start] = true;

        while(!q.isEmpty()) {
            int cur = q.removeFirst();
            for (int next: graph[cur]) {
                if (visited[next]) continue;
                int nextDistance = distance[cur] + 1;
                if (nextDistance > 2) continue;
                q.addLast(next);
                distance[next] = nextDistance;
                visited[next] = true;
                cnt++;
            }
        }
    }
}
