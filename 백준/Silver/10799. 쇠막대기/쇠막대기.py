import sys
from collections import deque

def input():
    return sys.stdin.readline().rstrip()

stack = deque()
input = input()
cnt = 0
ans = 0
flag = 0

for char in input:
    if char == '(' :
        stack.appendleft(char)
        cnt = cnt + 1
        flag = 0
    if char == ')' :
        if flag == 0 :
            stack.pop()
            cnt = cnt - 1
            ans = ans + cnt
            flag = 1
        else :
            cnt = cnt - 1
            ans = ans + 1

print(ans)