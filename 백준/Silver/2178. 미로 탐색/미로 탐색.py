import sys
from collections import deque
input = sys.stdin.readline

n,m = map(int, input().strip().split())
array = [[0 for _ in range(m)] for _ in range(n)]
for i in range(n):
    s = input().strip()
    for j in range(len(s)):
        array[i][j] = int(s[j])

dq = deque()
visited = [[False for _ in range(m)] for _ in range(n)]
arrived = [[0 for _ in range(m)] for _ in range(n)]
arrived[0][0] = 1
dq.append((0, 0))
count = 0
visited[0][0] = True
while dq:
    count += 1
    x,y = dq.popleft()
    if x < n - 1 and array[x+1][y] == 1:
        if not visited[x+1][y]:
            arrived[x+1][y] = arrived[x][y] + 1
            visited[x+1][y] = True
            dq.append((x+1, y))
        elif arrived[x+1][y] > arrived[x][y] + 1:
            arrived[x+1][y] = arrived[x][y] + 1
            dq.appendleft((x+1, y))

    if y < m - 1 and array[x][y+1] == 1:
        if not visited[x][y+1]:
            arrived[x][y+1] = arrived[x][y] + 1
            visited[x][y+1] = True
            dq.append((x, y+1))
        elif arrived[x][y+1] > arrived[x][y] + 1:
            arrived[x][y+1] = arrived[x][y] + 1
            dq.appendleft((x, y+1))
    if x > 0 and array[x-1][y] == 1:
        if not visited[x-1][y]:
            arrived[x-1][y] = arrived[x][y] + 1
            visited[x-1][y] = True
            dq.append((x-1, y))
        elif arrived[x-1][y] > arrived[x][y] + 1:
            arrived[x-1][y] = arrived[x][y] + 1
            dq.appendleft((x-1, y))
    if y > 0 and array[x][y-1] == 1:
        if not visited[x][y-1]:
            arrived[x][y-1] = arrived[x][y] + 1
            visited[x][y-1] = True
            dq.append((x, y-1))
        elif arrived[x][y-1] > arrived[x][y] + 1:
            arrived[x][y-1] = arrived[x][y] + 1
            dq.appendleft((x, y-1))

print(arrived[n-1][m-1])