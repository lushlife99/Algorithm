import sys
sys.setrecursionlimit(100000)
input = sys.stdin.readline

def dfs(x, y):
    global res

    res += 1
    visited[x][y] = 1

    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]
        if 0 <= nx < n and 0 <= ny < m:
            if arr[nx][ny] == 1 and visited[nx][ny] == 0:
                dfs(nx, ny)

res = 0
ans = 0
n,m,k = map(int, input().split())
arr = [[0 for _ in range(m)] for _ in range(n)]
visited = [[0 for _ in range(m)] for _ in range(n)]
dx = [1,-1,0,0]
dy = [0,0,1,-1]


for _ in range(k):
    a, b = map(int, input().split())
    arr[a-1][b-1] = 1

for i in range(n):
    for j in range(m):
        if arr[i][j] == 1 and visited[i][j] == 0:
            visited[i][j] = 1
            dfs(i,j)
            ans = max(ans, res)
            res = 0

print(ans)
