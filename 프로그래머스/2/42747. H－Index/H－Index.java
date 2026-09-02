import java.util.*;

/*
1. max -> 최대 인용 횟수 구하기 (정렬)
2. int[] count = new int[max + 1] -> count[i]: i번 이상 인용된 논문의 수
3. count[max] = 1;
    for (int i = count.length + 1; i >= 0; i--)
*/

class Solution {
    public int solution(int[] citations) {
        int max;
        int[] count;
        int n = citations.length;
        
        Arrays.sort(citations);
        max = citations[n - 1];
        count = new int[max + 1];
        // count[max] = 1;
        
        // count 배열 채우기 
        int citationIndex = n - 1;
        for (int h = max; h >= 0; h--) {
            int cnt = h == max ? 0 : count[h + 1]; // h번 이상 인용된 횟수 계산
            // System.out.printf("\n초기값 -> h: %d, cnt: %d, ci: %d\n", h, cnt, citationIndex);
            
            while(citationIndex >= 0 && citations[citationIndex] >= h) {
                cnt++;
                citationIndex--;
            }
            
            // System.out.printf("중간값: cnt: %d, ci: %d\n", cnt, citationIndex);
            
            
            if (cnt >= h) {
                System.out.println(Arrays.toString(count));
                return h;   
            }
            count[h] = cnt;
        }
        
//         int cnt = 1;
//         for (int i = n - 1; i >= 0; i--) {
//             count[citations[i]] = cnt++;
//         }
        
//         for (int i = citations.length - 1; i >= 1; i--) {
//             if (count[i] >= i) return i;
//             if (count[i - 1] == 0) count[i-1] = count[i];
//         }
        
        return 0;
    }
}