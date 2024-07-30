import sys
from collections import deque
sys.setrecursionlimit(100000)
input = sys.stdin.readline

n, m = map(int, input().strip().split())
inDegree = [0 for _ in range(n+1)]
A = [[] for _ in range(n+1)]

for i in range(m):
    a, b = map(int, input().strip().split())
    A[a].append(b)
    inDegree[b] += 1

dq = deque()

for i in range(1, n+1):
    if inDegree[i] == 0:
        dq.append(i)

while dq:
    now = dq.popleft()
    print(now, end=' ')
    for next in A[now]:
        inDegree[next] -= 1
        if inDegree[next] == 0:
            dq.append(next)

