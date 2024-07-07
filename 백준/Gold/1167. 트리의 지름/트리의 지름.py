import sys
sys.setrecursionlimit(100000)
from collections import deque
input = sys.stdin.readline

v = int(input())
array = [[] for _ in range(v+1)]
visited = [False] * (v+1)
distance = [0] * (v+1)
for _ in range(v):
    inputs = list(map(int, input().split()))
    for i in range(1, len(inputs) - 1, 2):
        array[inputs[0]].append((inputs[i], inputs[i + 1]))

def BFS(s) :
    global array
    dq = deque()
    dq.append(s)
    visited[s] = True
    while dq:
        v = dq.popleft()
        for i in array[v]:
            if visited[i[0]] == False:
                visited[i[0]] = True
                distance[i[0]] = distance[v] + i[1]
                dq.append(i[0])

BFS(1)
max = 0
max_idx = 0
for i in range(len(distance)):
    if distance[i] > max:
        max = distance[i]
        max_idx = i

visited = [False] * (v+1)
distance = [0] * (v+1)
BFS(max_idx)

for i in range(len(distance)):
    if distance[i] > max:
        max = distance[i]
        max_idx = i

print(max)