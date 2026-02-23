import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n, e;
    List<Edge>[] graph;
    int a, b;

	public static void main(String[] args) throws IOException {
        /*
        2개의 경로 비교하기
        s -> a -> b -> e
        s -> b -> a -> e 

        dijkstra(s), dijkstra(a), dijkstra(b) : 3번 
        */
        new Main().solution();
	}

    public void solution() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        graph = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[a].add(new Edge(b, cost));
            graph[b].add(new Edge(a, cost));
        }
        st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        int[] fromS = dijkstra(1);
        int[] fromA = dijkstra(a);
        int[] fromB = dijkstra(b);
        // s -> a -> b -> e
        // s -> b -> a -> e
        int route1 = getRoute(fromS[a], fromA[b], fromB[n]);
        int route2 = getRoute(fromS[b], fromB[a], fromA[n]);
        if (route1 == Integer.MAX_VALUE && route2 == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(Math.min(route1, route2));
        }
    }

    int getRoute(int a, int b, int c) {
        if (a == Integer.MAX_VALUE || b == Integer.MAX_VALUE || c == Integer.MAX_VALUE) return Integer.MAX_VALUE;
        return a + b + c;
        
    }

    int[] dijkstra(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> e1.cost - e2.cost);
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        pq.add(new Edge(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (dist[cur.dest] != cur.cost) continue;

            for (Edge next: graph[cur.dest]) {
                int oldCost = dist[next.dest];
                int newCost = dist[cur.dest] + next.cost;
                if (newCost < oldCost) {
                    dist[next.dest] = newCost;
                    pq.offer(new Edge(next.dest, newCost));
                }
            }
        }

        return dist;
    }

    class Edge {
        int dest, cost;
        public Edge(int dest, int cost) {
            this.cost = cost;
            this.dest = dest;
        }
    }
}
