import heapq

M, N = map(int, input().split())
tomato = []
visited = [[False for _ in range(M)] for _ in range(N)]
scope = [-1,1]
cnt = 0
queue = []
total = 0

for i in range(N):
    tomato.append(list(map(int, input().split())))
    for j in range(M):
        if tomato[i][j] == 1:
            heapq.heappush(queue, (i, j))
        elif tomato[i][j] == 0:
            total += 1

def bfs(arr):

    global cnt
    nextTomato = []

    while len(arr) != 0:
        x, y = heapq.heappop(arr)
        for d in scope:
            if 0 <= x + d < N:
                if tomato[x+d][y] == 0:
                    tomato[x+d][y] = 1
                    heapq.heappush(nextTomato, (x + d, y))
                    cnt += 1

            if 0 <= y + d < M:
                if tomato[x][y+d] == 0:
                    tomato[x][y+d] = 1
                    heapq.heappush(nextTomato, (x, y+d))
                    cnt += 1

    return nextTomato

day = 0
while len(queue) != 0:
    queue = bfs(queue)

    if len(queue) != 0:
        day += 1

if total == cnt:
    print(day)
else: print(-1)