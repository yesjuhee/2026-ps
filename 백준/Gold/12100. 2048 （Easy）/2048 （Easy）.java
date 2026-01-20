import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n;
    int[][] map1; // 입력용
    int[][] map2; // 이동용
    int max = 0;
    
	public static void main(String[] args) throws IOException {
        /*
        구: 최대 5번 이동해서 만들 수 있는 가장 큰 블록의 값
        이동할 수 있는 방향 4개
        1. 4^5 -> 1024개의 경우의 수 돌리기
        2. 최대값 구하기

        4진법 사용
        00000 -> 0 ~ 1023 경우의 수

        1. 입력 받기
        2. 1023 경우의 수 대로 이동
        3. 매번 최대값 갱신하기

        O(1023 * N^2)
        */
        new Main().solution();
	}

	public void solution() throws IOException {
        // 입력
        n = Integer.parseInt(br.readLine());        
        map1 = new int[n][n];
        map2 = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map1[i][j] = Integer.parseInt(st.nextToken());
                // map2[i][j] = map1[i][j];
            }
        }

        // 1023 가지 경우의 수
        for (int tmp = 0; tmp < 1024; tmp++) {
            // map2 초기화
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    map2[i][j] = map1[i][j];
                }
            }
            
            // 각 자리 4진수 뽑아내기
            int dir = tmp;
            for (int turn = 0; turn < 5; turn++) {
                int d = dir % 4;
                dir /= 4;
                move(d); // d 방향으로 합치기
            }
            
            // 최대값 구하기
            updateMax();
        }

        // 출력
        // StringBuilder sb = new StringBuilder();
        // for (int i = 0; i < n; i++) {
        //     for (int j = 0; j < n; j++) {
        //         sb.append(map2[i][j]).append(" ");
        //     }
        //     sb.append("\n");
        // }
        // System.out.println(sb);
        System.out.println(max);
	}

    public void updateMax() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map2[i][j] > max) max = map2[i][j];
            }
        }
    }

    public void move(int dir) {
        // 회전해서 이동
        // 0 왼쪽
        // 1 위쪽 -> 왼쪽으로 90도 회전
        // 2 오른쪽 -> 왼쪽으로 90도 회전 * 2
        // 3 아래쪽
        for (int i = 0; i < dir; i++) {
            rotate();
        }
        moveLeft();
    }

    // 왼쪽으로 90도 이동
    public void rotate() {
        int[][] tmp = new int[n][n];
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                int nx = y;
                int ny = n - 1 - x;
                tmp[nx][ny] = map2[x][y];
            }
        }
        map2 = tmp;
    }
    
    public void moveLeft() {
        // 왼쪽으로 싹 이동
        for (int i = 0; i < n; i++) {
            // i 번째 열에서 map2의 숫자들 압축
            map2[i] = zip(map2[i]);
        }
    }

    public int[] zip(int[] arr1) {
        int[] arr2 = new int[n];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            if (arr1[i] == 0) continue;
            // 옮길 값이 있다
            if (arr2[idx] == 0) { // 새로운 값 추가, idx 이동하지 않음
                arr2[idx] = arr1[i];
                continue;
            } else {
                // idx에 값이 있음
                if (arr1[i] == arr2[idx]) { // 같은 값이면
                    arr2[idx] = arr1[i] * 2;
                    idx++;
                } else {
                    idx++;
                    arr2[idx] = arr1[i];
                }                 
            }
        }
        return arr2;
    }
}
