import java.io.*;
import java.util.*;

class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static char[] nodes;
    static int n;
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
        nodes = new char[2 << n];

        // 루트 초기화
        nodes[1] = 'A';

        // 노드 하나씩 입력 -> 현재 위치 찾기 -> 자식 추가
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char p = st.nextToken().charAt(0);
            char leftChild = st.nextToken().charAt(0);
            char rightChild = st.nextToken().charAt(0);
            
            int pIndex = getIndexOf(p);
            if (leftChild != '.') nodes[leftChild(pIndex)] = leftChild;
            if (rightChild != '.') nodes[rightChild(pIndex)] = rightChild;
        }
    }

    int getIndexOf(char c) {
        for (int i = 1; i < 2 << n; i++) {
            if (nodes[i] == c) return i;
        }
        return -1;
    }

    int leftChild(int idx) {
        return 2 * idx;
    }

    int rightChild(int idx) {
        return 2 * idx + 1;
    }

    void preorder(int idx) {
        int leftChildIdx = leftChild(idx);
        int rightChildIdx = rightChild(idx);
        
        preorder.append(nodes[idx]);
        if (nodes[leftChildIdx] != '\0') preorder(leftChildIdx);
        if (nodes[rightChildIdx] != '\0') preorder(rightChildIdx);
    }

    void inorder(int idx) {
        int leftChildIdx = leftChild(idx);
        int rightChildIdx = rightChild(idx);
        
        if (nodes[leftChildIdx] != '\0') inorder(leftChildIdx);
        inorder.append(nodes[idx]);        
        if (nodes[rightChildIdx] != '\0') inorder(rightChildIdx);    
    }

    void postorder(int idx) {
        int leftChildIdx = leftChild(idx);
        int rightChildIdx = rightChild(idx);
        
        if (nodes[leftChildIdx] != '\0') postorder(leftChildIdx);
        if (nodes[rightChildIdx] != '\0') postorder(rightChildIdx);
        postorder.append(nodes[idx]);
    }
}
