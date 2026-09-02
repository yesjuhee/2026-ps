import java.util.*;

class Solution {
    public int solution(int[][] sizes) {
        // 전체에서 제일 큰 값 구하기
        int n = sizes.length;
        int max = -1;
        int indexOfMax = -1;
        
        for (int i = 0; i < n; i++) {
            if (sizes[i][0] > max || sizes[i][1] > max) {
                max = Math.max(sizes[i][0], sizes[i][1]);
                indexOfMax = i;
            }
        }
        
        int width = max;
        int height = -1;
        for (int i = 0; i < n; i++) {
            height = Math.max(height, Math.min(sizes[i][0], sizes[i][1])); 
        }
        
        return width * height;
    }
}