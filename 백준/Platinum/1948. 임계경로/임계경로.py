import sys
from collections import deque
sys.setrecursionlimit(100000)
input = sys.stdin.readline

n = int(input()) # number of cities
m = int(input()) # number of roads

A = [[] for _ in range(n+1)]
time = [0] * (n+1)
inDegree = [0] * (n+1)

for i in range(m):
    a, b, c = map(int, input().strip().split())
    A[a].append((i, b, c))
    inDegree[b] += 1

start, end = map(int, input().strip().split())
dq = deque()
dq.append(start)

while dq:
    now = dq.popleft()

    for next in A[now]:
        inDegree[next[1]] -= 1
        if time[next[1]] < time[now] + next[2]:
            time[next[1]] = time[now] + next[2]

        if inDegree[next[1]] == 0:
            dq.append(next[1])

visited = [False] * (n+1)
list = []
dq.append(end)
cnt = 0
while dq:
    now = dq.popleft()

    for prev in range(1, n + 1):
        for road_id, next_city, travel_time in A[prev]:
            if next_city == now and time[now] == time[prev] + travel_time:
                cnt += 1
                if not visited[prev]:
                    visited[prev] = True
                    dq.append(prev)
for i in range(len(list)):
    print(list[i])

print(time[end])
print(cnt)