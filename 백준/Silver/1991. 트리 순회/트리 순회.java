import java.io.*;
import java.util.*;

class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static char[] nodes;
    static int n;
    static int[] leftChild; // i번 노드의 왼쪽 자식
    static int[] rightChild;
    
    static StringBuilder preorder = new StringBuilder();
    static StringBuilder inorder = new StringBuilder();
    static StringBuilder postorder = new StringBuilder();
    
	public static void main(String[] args) throws IOException {
        new Main().solution();
	}

    public void solution() throws IOException {
        // 입력 -> 2^N 배열로 관리
        initTree();
        
        preorder(1);
        inorder(1);
        postorder(1);

        System.out.println(preorder);
        System.out.println(inorder);
        System.out.println(postorder);
    }

    void initTree() throws IOException {
        // n 입력
        n = Integer.parseInt(br.readLine());
        leftChild = new int[1 + n];
        rightChild = new int[1 + n];

        // 노드 하나씩 입력
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p = charToInt(st.nextToken().charAt(0));
            int lc = charToInt(st.nextToken().charAt(0));
            int rc = charToInt(st.nextToken().charAt(0));
            
            if (lc != charToInt('.')) leftChild[p] = lc;
            if (rc != charToInt('.')) rightChild[p] = rc;
        }
    }

    int charToInt(char c) {
        return c - 'A' + 1;
    }

    char intToChar(int n) {
        return (char) ('A' - 1 + n);
    }

    void preorder(int root) {
        int lc = leftChild[root];
        int rc = rightChild[root];
        
        preorder.append(intToChar(root));
        if (lc != 0) preorder(lc);
        if (rc != 0) preorder(rc);
    }

    void inorder(int root) {
        int lc = leftChild[root];
        int rc = rightChild[root];
        
        if (lc != 0) inorder(lc);
        inorder.append(intToChar(root));
        if (rc != 0) inorder(rc);   
    }

    void postorder(int root) {
        int lc = leftChild[root];
        int rc = rightChild[root];
        
        if (lc != 0) postorder(lc);
        if (rc != 0) postorder(rc);
        postorder.append(intToChar(root));        
    }
}
