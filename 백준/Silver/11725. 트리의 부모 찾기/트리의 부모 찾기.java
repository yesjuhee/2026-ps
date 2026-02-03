import java.io.*;
import java.util.*;

class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] parent; // 부모들을 기록하는 배열
    static List<Integer>[] adj; // 간접 그래프
    static int n;
    
	public static void main(String[] args) throws IOException {
        /*
        N개 노드 -> N-1개 간선
        이진트리라는 말이 없음
        배열 말고 클래스로 표현

        간접 그래프로 표현
        1부터 시작해서 BFS로 탐색
        탐색하면서 부모 배열 채우기
        */
        new Main().solution();
	}

    public void solution() throws IOException {
        // 입력 -> 간접 그래프 초기화
        init();
        // bfs로 탐색하면서 부모 배열 초기화
        bfs(1);
        // 부모 배열 출력
        print();
    }

    void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        adj = new List[n + 1];
        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a].add(b);
            adj[b].add(a);
        }
    }

    void bfs(int root) {
        Deque<Integer> q = new ArrayDeque<>();
        q.addLast(root);
        
        while(!q.isEmpty()) {
            int cur = q.removeFirst();
            List<Integer> nexts = adj[cur];
            for (int next: nexts) {
                if (next == parent[cur]) continue;
                q.addLast(next);
                parent[next] = cur;
            }
        }
    }

    void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= n; i++) {
            sb.append(parent[i]).append("\n");
        }
        System.out.println(sb);
    }
}
