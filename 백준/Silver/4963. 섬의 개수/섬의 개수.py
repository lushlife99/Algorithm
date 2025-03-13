import sys

sys.setrecursionlimit(100000)
input = sys.stdin.readline

dx = [0, 0, 1, -1, 1, 1, -1 ,-1]
dy = [1, -1, 0, 0, 1, -1, 1, -1]
ans = []

def dfs(x, y):

    arr[x][y] = 0

    for i in range(len(dx)):
        nx = x + dx[i]
        ny = y + dy[i]

        if 0 <= nx < h and 0 <= ny < w and arr[nx][ny] == 1:
            dfs(nx, ny)


while(True):
    w, h = map(int, input().split())
    if w == 0 and h == 0:
        break

    arr = []
    cnt = 0
    for i in range(h):
        arr.append(list(map(int, input().split())))

    for j in range(h):
        for i in range(w):
            if arr[j][i] == 1:
                dfs(j, i)
                cnt += 1

    ans.append(cnt)


for i in range(len(ans)):
    print(ans[i])
