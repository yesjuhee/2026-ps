import java.util.*;

/*
조이스틱 조작 횟수의 최소값

돌아가면 무조건 손해
오른쪽으로만 or 왼쪽으로만 -> 둘 다 해서 최소값 반환

// 오른쪽으로
String: 초기값 세팅
for (int i = 0; i < name.length; i++)
    if (초기값 == name) break;
    count += i번째 이동 횟수 구하기
    count++; // 이동
    

func : 위아래 이동 횟수 구하기
    abs(b-a) -> 위로 이동
    26 - abs(b-a) -> 아래로 이동
    둘 중에 작은 값

------

한쪽으로만 이동해서 최소가 아닌 경우?

BBAAAAB

위아래 값은 고정이고, 좌우로 얼마만에 커버할 수 있느냐를 구해야함
좌우로 커버 == 연속 A를 제외 

어차피 지나가긴 해야함

(전체 길이 - 최대 연속 A의 길이) -> 밟아야 하는 길이
시작점 ~ 밟아야하는 구간 시작점 까지의 최단 거리 더하기
흠 근데 최대 연속 A를 지나야지 밟아야하는 구간 시작점으로 최단으로 이동할 수 있지 않나?

---

클로드 힌트
- 상하 고정
- 좌우 최소값 찾기 -> 경우의 수 많지 않음
    - 오른쪽으로 이동했다가 왼쪽으로 이동하는 모든 경우
    - 왼쪽으로 이동했다가 오른쪽으로 이동하는 모든 경우
    
ABBAABA
0123456

i: 2
len: 7
next: 5

*/

class Solution {
    public int solution(String name) {
        int n = name.length();
        int vertical = 0; // 상하이동 -> 누적합
        int horizontal = n - 1; // 좌우 이동 -> 최소값 
        
        int min = Integer.MAX_VALUE;
        
        for (int i = 0; i < n; i++) {
            vertical += moveCount(name.charAt(i));
            
            // i 뒤에서 연속된 A가 끝나는 지점 찾기
            int next = i + 1;
            while(next < n && name.charAt(next) == 'A') {
                next++;
            }
            
            // 좌우 이동 최소 횟수 구하기
            int frontMove = i;
            int backMove = n-next;
            
            horizontal = Math.min(horizontal, 
                                 frontMove * 2 + backMove);
            horizontal = Math.min(horizontal,
                                  backMove*2 + frontMove);
        }
        
        return horizontal + vertical;
    }
    
    // 상하 움직임 
    int moveCount(char target) {
        return Math.min(
            target - 'A',
            'Z' - target + 1
        );
    }
}