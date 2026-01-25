# 주사위 세개
import sys
a, b, c = map(int, sys.stdin.readline().split())

max = None
count = 1

if a == b:
    if a == c:
        print(10000+a*1000)  # 같은 눈 3개
    else:
        print(1000+a*100)  # 같은 눈 2개
else:
    if a == c:
        print(1000+a*100)  # 같은 눈 2개
    elif b == c:
        print(1000+b*100)  # 같은 눈 2개
    else:                  # 모두 다른 눈
        max = a
        if b > max:
            max = b
        if c > max:
            max = c
        print(max*100)
