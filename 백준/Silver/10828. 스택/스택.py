# 스택

import sys
input = sys.stdin.readline


def s_push(stack, x):
    stack.append(x)
    return stack


def s_pop(stack):
    if len(stack) == 0:
        return -1, stack
    return stack.pop(), stack


def s_size(stack):
    return len(stack)


def s_empty(stack):
    if len(stack) == 0:
        return 1
    return 0


def s_top(stack):
    if len(stack) == 0:
        return -1
    return stack[-1]


stack = []

n = int(input())

for _ in range(n):
    input_list = list(input().split())

    if input_list[0] == "push":
        stack = s_push(stack, input_list[1])
    elif input_list[0] == "pop":
        x, stack = s_pop(stack)
        print(x)
    elif input_list[0] == "size":
        print(s_size(stack))
    elif input_list[0] == "empty":
        print(s_empty(stack))
    elif input_list[0] == "top":
        print(s_top(stack))
