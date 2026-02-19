import java.io.*;
import java.util.*;

public class Main {

    private static List<Integer>[] A;
    private static boolean[] visited;
    private static int count = 0;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // 인접 리스트 초기화
        A = new ArrayList[n + 1];
        visited = new boolean[n + 1];
        for (int i = 1; i < n + 1; i++) {
            A[i] = new ArrayList<Integer>();
        }

        // 간선 입력
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            A[u].add(v);
            A[v].add(u);
        }

        // 모든 노드에 대해 DFS
        for (int i = 1; i < n + 1; i++) {
            if (visited[i]) continue;
            count++;
            dfs(i);
        }

        System.out.println(count);
    }

    private void dfs(int currentNode) {
        if (visited[currentNode]) return;

        visited[currentNode] = true;
        for (int nextNode: A[currentNode]) {
            if(visited[nextNode]) continue;
            dfs(nextNode);
        }
    }
}
