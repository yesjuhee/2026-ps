import java.util.*;

/*
이웃한 단어들 그래프 -> 단어들 탐색 -> 최단거리
그래프를 어떻게 표현할건지? 노드 + 인접 배열
전체 순환하며 그래프 만들기
*/

class Solution {
    
    // Map<Node, List<Node>> graph = new HashMap<>();
    Map<String, Node> nodes = new HashMap<>();
    
    public int solution(String begin, String target, String[] words) {
        int n = words.length;
        
        // early return
        boolean haveTarget = false;
        for (int i = 0; i < n; i++) {
            if (words[i].equals(target)) haveTarget = true;
            Node node = new Node(words[i]);
            nodes.put(words[i], node);
        }
        if (!haveTarget) return 0;
        nodes.put(begin, new Node(begin));
        
        // graph init
        for (Node node: nodes.values()) {
            for (int j = 0; j < n; j++) {
                String candidate = words[j];
                if (isNeighbor(node.str, candidate)) {
                    node.adjs.add(nodes.get(candidate));
                }   
            }
        }
        
        // for (Node node: nodes.values()) {
        //     System.out.printf("Node(%s) 인접: %s\n", node.str, node.adjs);
        // }
        
        return bfs(begin, target);
    }
    
    int bfs(String start, String end) {
        Queue<Node> q = new ArrayDeque<>();
        Node cur = nodes.get(start);
        q.offer(cur);
        cur.visited = true;
        
        while(!q.isEmpty()) {
            cur = q.poll();
            
            for (Node next: cur.adjs) {
                if (next.visited) continue;
                next.dist = cur.dist + 1;
                if (next.str.equals(end)) return next.dist;
                q.offer(next);
                next.visited = true;
            }
        }
        
        return -1;
    }
    
    boolean isNeighbor(String a, String b) {
        char[] aa = a.toCharArray();
        char[] bb = b.toCharArray();
        int diffCount = 0;
        for (int i = 0; i < aa.length; i++) {
            if (aa[i] != bb[i]) diffCount++;
        }
        return diffCount == 1;
    }
        
}

class Node {
    String str;
    List<Node> adjs;
    boolean visited;
    int dist;
    
    public Node(String str) {
        this.str = str;
        this.adjs = new ArrayList<>();
        this.visited = false;
        this.dist = 0;
    }
}