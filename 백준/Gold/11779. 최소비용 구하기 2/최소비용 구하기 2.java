import java.io.*;
import java.util.*;

class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n, m;
    int start, end;

    // graph -> 인접 리스트
    List<Pair>[] graph; // {비용, 정점}

    // dist 배열 -> 최소 거리 저장
    long INF = Long.MAX_VALUE;
    long[] dist;
    int[] prev; // 이전에 방문한 정점

	public static void main(String[] args) throws IOException {
        new Main().solution();
	}

    public void solution() throws IOException {
        init();
        
        // 다익스트라 실행
        PriorityQueue<Pair> pq = new PriorityQueue<>((n1, n2) -> {
            if(n1.cost - n2.cost < 0) return -1;
            else return 1;
        });
        dist[start] = 0;
        prev[start] = -1;
        pq.add(new Pair(start, 0));
        
        while(!pq.isEmpty()) {
            Pair cur = pq.poll();
            int curNode = cur.v;
            long curDist = cur.cost;

            // 이미 처리된 노드는 무시
            if (curDist != dist[curNode]) continue;

            for (Pair edge: graph[cur.v]) {
                long nextDist = curDist + edge.cost;
                int nextNode = edge.v;
                if (nextDist >= dist[nextNode]) continue;
                prev[nextNode] = curNode;
                dist[nextNode] = nextDist;
                pq.add(new Pair(nextNode, nextDist));
            }
        }

        // 비용
        System.out.println(dist[end]);
        // 경로 찾기
        List<Integer> route = findRoute();
        System.out.println(route.size()); // 경로 길이
        StringBuilder sb = new StringBuilder();
        for (int i = route.size() - 1; i >= 0; i--) {
            sb.append(route.get(i)).append(" ");
        }
        System.out.println(sb);
    }

    List<Integer> findRoute() {
        List<Integer> route = new ArrayList<>();
        int cur = end;
        while(cur != -1) {
            route.add(cur);
            cur = prev[cur];
        }
        return route;
    }

    void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        // 그래프 초기화
        graph = new List[n + 1];
        for (int i = 1; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        // 간선 초기화
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Integer.parseInt(st.nextToken());
            graph[a].add(new Pair(b, c));
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        // 기타
        dist = new long[n + 1];
        prev = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            dist[i] = INF;
        }
    }

    class Pair {
        
        long cost;
        int v;

        public Pair(int v, long cost) {
            this.cost = cost;
            this.v = v;
        }
    }
}
