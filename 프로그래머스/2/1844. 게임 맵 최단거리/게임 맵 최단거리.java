import java.util.*;

/*
bfs + 최단거리
도달 불능 -1 리턴
*/

class Solution {
    
    int n;
    int m;
    int[][] dist;
    int[][] map;
    
    int[] dx = new int[]{0, 0, 1, -1};
    int[] dy = new int[]{1, -1, 0, 0};
    
    public int solution(int[][] maps) {
        // init
        map = maps;
        n = map.length;
        m = map[0].length;
        dist = new int[n][m];
        
        bfs(new Node(0, 0));
        
        if (dist[n-1][m-1] == 0) return -1;
        return dist[n-1][m-1];
    }
    
    void bfs(Node start) {
        Queue<Node> q = new ArrayDeque<>();
        q.offer(start);
        dist[start.x][start.y] = 1;
        
        while(!q.isEmpty()) {
            Node cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (map[nx][ny] == 0) continue;
                if (dist[nx][ny] > 0) continue;
                Node next = new Node(nx, ny);
                dist[next.x][next.y] = dist[cur.x][cur.y] + 1;
                q.offer(next);
            }
        }
    }
}

class Node {
    int x, y;
    
    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}