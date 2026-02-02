import java.io.*;
import java.util.*;

class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] heights = new int[9];
    
	public static void main(String[] args) throws IOException {
        /*
        9명 중 합이 100인 경우의 수 찾기
        2^9 -> 512가지 경우의 수가 아니고..
        9C7 -> 두명 선택하는 경우의 수
        */
        new Main().solution();
	}

	public void solution() throws IOException {
        // 입력
        for (int i = 0; i < 9; i++) {
            heights[i] = Integer.parseInt(br.readLine());
        }

        // 정렬
        Arrays.sort(heights);
        int c = findCase();

        // 케이스에 맞게 출력
        print(c);
	}

    public int findCase() {
        // 2^9가지 경우의 수 -> 답 찾기
        for (int c = 0; c < 512; c++) {
            int sum = 0;
            int zeroCount = 0; // 0가 2개만 있어야 함
            // 케이스에 해당하는 값만 추가
            for (int i = 0; i < 9; i++) {
                if (((c >> i) & 1) == 1) sum += heights[i]; 
                else zeroCount++;
            }
            if (sum == 100 && zeroCount == 2) {
                return c;
            }
        }
        return 0;
    }

    public void print(int c) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            if (((c >> i) & 1) == 1) sb.append(heights[i]).append("\n");
        }
        System.out.println(sb);
    }
}
