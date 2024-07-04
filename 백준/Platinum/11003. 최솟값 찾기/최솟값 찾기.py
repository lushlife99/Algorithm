import sys
from collections import deque
input = sys.stdin.readline

L, N = map(int, input().split())
myList = list(map(int, input().split()))
dq = deque()
answerList = []
start = 0

for i in range(L):
    while dq and dq[-1][1] >= myList[i]:
            dq.pop()

    dq.append((i, myList[i]))

    if i - N + 1 > dq[0][0] : dq.popleft()

    print(dq[0][1], end=' ')
