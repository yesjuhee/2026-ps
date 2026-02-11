// 2042
import java.io.*;
import java.util.*;

class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n, m, k;
    long[] tree;
    StringBuilder sb = new StringBuilder();
    int leafCount; // 2^h

	public static void main(String[] args) throws IOException {
        new Main().solution();
	}

    public void solution() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        initTree();

        // for (int i = 0; i < leafCount * 2; i++) {
        //     System.out.println(tree[i]);
        // }

        for (int i = 0; i < m+k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            if (a == 1) update(b - 1, c);
            else sb.append(sum(b - 1, (int) c - 1)).append("\n");
        }

        System.out.println(sb);
    }

    void initTree() throws IOException {
        int leafCount = 1; // 2^0
        while(leafCount < n) {
            leafCount <<= 1;
        }
        this.leafCount = leafCount;
        tree = new long[leafCount * 2];
        
        for (int i = 0; i < n; i++) {
            tree[leafCount + i] = Long.parseLong(br.readLine());
        }

        for (int i = leafCount * 2 - 1; i >= 2; i--) {
            tree[i/2] += tree[i];
        }
    }

    void update(int index, long value) {
        index = convertIndex(index); // 트리 인덱스
        long diff = value - tree[index];
        while(index >= 1) {
            tree[index] += diff;
            index /= 2;
        }
    }

    long sum(int start, int end) {
        start = convertIndex(start);
        end = convertIndex(end);
        long sum = 0;
        while(start <= end) {
            // System.out.printf("sum: %d, start: %d, end:%d\n", sum, start, end);
            if (start % 2 == 1) sum += tree[start];
            if (end % 2 == 0) sum += tree[end];
            start = (start + 1) / 2;
            end = (end - 1) / 2; 
        }
        return sum;
    }

    int convertIndex(int idx) {
        return idx + leafCount;
    }   
}
