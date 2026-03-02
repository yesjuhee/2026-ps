# 숫자의 개수

import sys
input = sys.stdin.readline

digit_list = [0] * 10

# 입력
a = int(input())
b = int(input())
c = int(input())
products = a * b * c

for char in str(products):
    digit_list[int(char)] += 1

for digit in digit_list:
    print(digit) 
