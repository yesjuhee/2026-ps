import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n;
    int[] heap = new int[100_005];
    StringBuilder sb = new StringBuilder();
    int lastIndex = 0;

	public static void main(String[] args) throws IOException {
        new Main().solution();
	}

    public void solution() throws IOException {
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            int cmd = Integer.parseInt(br.readLine());
            if (cmd == 0 && isEmpty()) {
                sb.append(0).append("\n");
            } else if (cmd == 0) {
                sb.append(remove()).append("\n");
            } else {
                add(cmd);
            }
        }
        System.out.println(sb);
    }

    boolean isEmpty() {
        return lastIndex == 0;
    }

    void add(int num) {
        // 맨 끝에 추가 
        heap[++lastIndex] = num;
        // 위로 올리기 
        int curIndex = lastIndex;
        while(curIndex > 1) {
            int parentIndex = parentIndex(curIndex);
            if (heap[parentIndex] < heap[curIndex]) break;
            swap(parentIndex, curIndex);
            curIndex = parentIndex;
        }
    }

    int remove() {
        // 맨 끝에 값이랑 바꾸기
        swap(1, lastIndex);
        // 바꾼 루트의 위치 찾기
        int curIndex = 1;
        int num = heap[1];
        while(curIndex < lastIndex) {
            int leftIndex = leftChildIndex(curIndex);
            int rightIndex = rightChildIndex(curIndex);
            // cudIndex 가 lastIndex 의 부모가 아니라면 양쪽 자식을 항상 가짐 
            if (leftIndex >= lastIndex) {
                break;
            }  
            if (rightIndex >= lastIndex) {
                // 왼쪽 자식만 확인
                if (heap[leftIndex] < num) swap(leftIndex, curIndex);
                break;
            } 
            // 양쪽 자식 확인
            if (heap[leftIndex] >= num && heap[rightIndex] >= num) break;
            int swapIndex = heap[leftIndex] < heap[rightIndex] ? leftIndex : rightIndex;
            swap(swapIndex, curIndex);
            curIndex = swapIndex;
        }

        return heap[lastIndex--];
    }

    int parentIndex(int idx) {
        return idx / 2;
    }

    int leftChildIndex(int idx) {
        return idx * 2;
    }

    int rightChildIndex(int idx) {
        return idx * 2 + 1;
    }

    void swap(int i, int j) {
        int tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }
}
