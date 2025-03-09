import sys
input = sys.stdin.readline

n = int(input())
ground = []

for _ in range(n):
    row = list(map(int, input().split()))
    ground.append(row)

max_height = max(max(row) for row in ground)

dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]
ans = 0

def bfs(x, y, rain):
    stack = [(x, y)]
    visited[x][y] = 1

    while stack:
        cx, cy = stack.pop()
        for i in range(4):
            nx = cx + dx[i]
            ny = cy + dy[i]

            if 0 <= nx < n and 0 <= ny < n and ground[nx][ny] > rain and visited[nx][ny] == 0:
                visited[nx][ny] = 1
                stack.append((nx, ny))


count_list = [1]
for i in range(1, max_height):
    cnt = 0
    visited = [[0 for _ in range(n)] for _ in range(n)]

    for j in range(n):
        for k in range(n):
            if ground[j][k] > i and visited[j][k] == 0:
                bfs(j, k, i)
                cnt += 1
    count_list.append(cnt)

print(max(count_list))
