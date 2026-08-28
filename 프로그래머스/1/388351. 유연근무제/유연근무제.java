class Solution {
    
    /*
    상품 -> 일주일동안 상품 희망 시간에 늦지 않음 
    
    - 반복문으로 timelogs 순회
        - 주말인 날은 패스 
    - 한명 직원의 전체 기록 체크 
    - 늦은 적 없으면 result +1
    
    1 - 월
    2 - 화
    3 - 수
    4 - 목
    5 - 금
    6 - 토
    0 - 일
    */
    
    int n; // 전체 직원 수
    int result = 0; // 통과 직원의 수
    
    
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        n = schedules.length;
        
        startday %= 7;
        
        for (int i = 0; i < n; i++) {
            // i + 1 번째 직원 체크 
            if (isGood(schedules[i], timelogs[i], startday)) result++;
        }

        return result;
    }
    
    // 일주일 통과
    public boolean isGood(int schedule, int[] timelog, int startday) {
        for (int t = 0; t < 7; t++) { // 일주일 체크
            if (startday != 6 && startday != 0 && !isOK(schedule, timelog[t])) return false;
            
            startday = (startday + 1) % 7; // 다음 요일
        }
        return true;
    }
    
    public boolean isOK(int schedule, int timelog) {
        int start = schedule;
        int h = start / 100;
        int m = start % 100;
        
        int end;
        if (m < 50) {
            // 시간이 넘어가지 않음
            end = start + 10;
        } else {
            h++;
            m = (m + 10) % 60;
            end = h * 100 + m;
        }
        
        return timelog <= end;
    }
}