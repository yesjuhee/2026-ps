import java.util.*;

/*
1 <= n <= 200

네트워크의 개수를 return

그래프 탐색

for (i: 1 ~ n) {
    탐색했는지 여부 체크
    bfs 해서 전체 탐색 체크
    count++
}
*/

class Solution {
    public int solution(int n, int[][] computers) {
        int count = 0;
        boolean[] visited = new boolean[n];
        
        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;
            bfs(i, visited, computers);
            count++;
            // System.out.printf(Arrays.toString(visited));
        }
        
        return count;
    }
    
    void bfs(int start, boolean[] visited, int[][] graph) {
        Queue<Integer> q = new ArrayDeque<>();
        int cur = start;
        q.offer(cur);
        visited[cur] = true;
        
        while(!q.isEmpty()) {
            cur = q.poll();
            int[] adjs = graph[cur];
            
            for (int i = 0; i < adjs.length; i++) {
                if (i == cur) continue;
                if (visited[i]) continue;
                boolean isConnected = adjs[i] == 1 ? true : false;
                if (!isConnected) continue;
                q.offer(i);
                visited[i] = true;
            }
        }
    }
}