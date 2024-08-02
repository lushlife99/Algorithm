import sys
from collections import deque
input = sys.stdin.readline

fees = []
n,start,end, d = map(int, input().split())
for i in range(d):
    s,e,w = map(int, input().split())
    fees.append((s,e,w))
costs = list(map(int, input().split()))
edges = []
result = [-1 * sys.maxsize] * n
result[start] = costs[start]

for i in fees:
    s,e,w = i
    w = costs[e] - w
    edges.append((s,e,w))

for _ in range(n-1):
    for i in edges:
        s,e,w = i
        if result[s] != -1*sys.maxsize and result[e] < result[s] + w:
            result[e]=result[s] + w


def bfs(start, end):
    q = deque()
    q.append(start)
    visited = [False]*(n)
    visited[start] = True
    while q:
        now = q.popleft()
        if now == end:
            return True
        for s,e,w in edges:
            if s==now:
                if not visited[e]:
                    visited[e] = True
                    q.append(e)
    
    return False


isCycle = False
for i in edges:
        s,e,w = i
        if result[s] != -1 * sys.maxsize and result[e] < result[s] + w:
            if bfs(s,end):
                isCycle = True
                break

if result[end] == -1 * sys.maxsize:
    print("gg")
else:
    if isCycle:
        print("Gee")
    else:
        print(result[end])
