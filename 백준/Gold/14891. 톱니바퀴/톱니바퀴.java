import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    Deque<Integer>[] gears = new Deque[5];
    int k;

    {
        for (int i = 1; i <= 4; i++) {
            gears[i] = new ArrayDeque<>();
        }
    }

	public static void main(String[] args) throws IOException {
        /*
        바퀴 -> int deque 으로 관리        
        0 -> 12시
        2 -> 3시
        4 -> 6시
        6 -> 9시

        톱니바퀴를 회전시키는 함수 -> 방향 받아서 remove/push
        A와 B가 같은 극인지 다른 극인지 비교하는 함수
        */
        new Main().solution();
	}

    public void solution() throws IOException {
        // 기어 초기화
        init();

        k = Integer.parseInt(br.readLine());

        for (int c = 0; c < k; c++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int gearNum = Integer.parseInt(st.nextToken());
            int rotation = Integer.parseInt(st.nextToken());
            rotate(gearNum, rotation);
        }

        int result = calculateScore();

        System.out.println(result);
    }

    void init() throws IOException {
        for (int i = 1; i <= 4; i++) {
            String input = br.readLine();
            for (int j = 0; j < 8; j++) {
                gears[i].add(Integer.parseInt(input.substring(j, j+1)));
            }
        }
    }

    void rotate(int gearNum, int rotation) {
        // 왼쪽 확인
        int left = gearNum - 1;
        int cur = gearNum;
        boolean[] willRotate = new boolean[5];
        while(left >= 1) {
            if (isSamePole(left, cur)) break;
            willRotate[left] = true;
            left--;
            cur--;
        }
        left = gearNum - 1;
        int leftRotation = rotation;
        while(left >= 1) {
            if (!willRotate[left]) break;
            leftRotation *= -1;
            realRotate(left, leftRotation);
            left--;
        }

        // 오른쪽 확인
        int right = gearNum + 1;
        cur = gearNum;
        willRotate = new boolean[5];
        while(right <= 4) {
            if (isSamePole(cur, right)) break;
            willRotate[right] = true;
            right++;
            cur++;
        }
        right = gearNum + 1;
        int rightRotation = rotation;
        while(right <= 4) {
            if (!willRotate[right]) break;
            rightRotation *= -1;
            realRotate(right, rightRotation);
            right++;
        }

        // 본인 회전
        realRotate(gearNum, rotation);
    }

    void realRotate(int gearNum, int rotation) {
        // 회전
        Deque<Integer> gear = gears[gearNum];
        if (rotation == 1) {
            gear.addFirst(gear.removeLast());
        } else {
            gear.addLast(gear.removeFirst());
        }
    }

    boolean isSamePole(int a, int b) {
        // a 의 3시와, b의 9시 검사
        Object[] gearA = gears[a].toArray();
        Object[] gearB = gears[b].toArray();
        return gearA[2] == gearB[6];
    }

    int calculateScore() {
        int score = 0;
        for (int i = 1; i <= 4; i++) {
            Deque<Integer> gear = gears[i];
            
            if (gear.peekFirst() == 1) {
                score += (1 << (i - 1));
            }
        }

        return score;
    }

    void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 4; i++) {
            Object[] gear = gears[i].toArray();
            for (int j = 0; j < 8; j++) {
                sb.append(gear[j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
