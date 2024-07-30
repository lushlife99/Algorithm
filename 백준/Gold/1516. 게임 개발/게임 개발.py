import sys
from collections import deque
sys.setrecursionlimit(100000)
input = sys.stdin.readline

n = int(input())
time = [0] * (n+1)
A = [[] for _ in range(n+1)] # 각 노드의 간선
inDegree = [0 for _ in range(n+1)]
ans = [0] * (n+1)
for i in range(n):
    inputs = list(map(int, input().strip().split()))
    time[i+1] = inputs[0]
    for j in range(1, len(inputs) - 1):
        A[inputs[j]].append(i+1)
        inDegree[i+1] += 1

dq = deque()

for i in range(1, n+1):
    if inDegree[i] == 0:
        dq.append(i)

while dq:
    building = dq.popleft()

    for i in A[building]:
        inDegree[i] -= 1
        ans[i] = max(ans[i], ans[building] + time[building])
        if inDegree[i] == 0:
            dq.append(i)

for i in range(1, n+1):
    print(ans[i] + time[i])