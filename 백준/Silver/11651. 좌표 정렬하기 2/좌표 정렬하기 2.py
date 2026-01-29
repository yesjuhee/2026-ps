# 좌표 정렬하기 2

# 좌표 정렬하기 1에서 y와 x의 위치만 바꿔서 정렬
import sys
input = sys.stdin.readline

# 입력
n = int(input())
array = []
for i in range(n):
    x, y = map(int, input().split())
    array.append([y, x])

array.sort()

# 출력
for i in range(n):
    print(f'{array[i][1]} {array[i][0]}')
