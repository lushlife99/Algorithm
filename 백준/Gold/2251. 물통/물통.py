import sys

sys.setrecursionlimit(100000)
input = sys.stdin.readline

# A, B, C 물통 존재.
# A 물통이 비어있을 때 C 물통에 담겨있는 물의 양을 오름차순으로 구해내라
a, b, c = map(int, input().strip().split())
visited = [[[False for _ in range(c + 1)] for _ in range(b + 1)] for _ in range(a + 1)]
A = []


def DFS(x, y, z):
    global a, b, c, visited, A
    visited[x][y][z] = True

    if x == 0:
        A.append(z)

    if x != 0:  # a에서 b, c로 이동
        if y < b: # b가 가득차지 않았을 때 b로 이동
            if x < b - y:  # a에 있는 모든 물의 양을 b로 이동
                if not visited[0][y + x][z]:
                    DFS(0, y + x, z)

            else:  # b가 가득차게 물을 이동
                if not visited[x - (b - y)][b][z]:
                    DFS(x - (b - y), b, z)

        if z < c: # c가 가득차지 않았을 때 c로 이동
            if x < c - z: # a에 있는 모든 물의 양을 c로 이동
                if not visited[0][y][z + x]:
                    DFS(0, y, z + x)

            else:
                if not visited[x - (c - z)][y][c]:
                    DFS(x - (c - z), y, c)

    if y != 0:  # b에서 a, c로 이동
        if x < a: # a가 가득차지 않았을 때 a로 이동
            if y < a - x: # b에 있는 모든 물의 양을 a로 이동
                if not visited[x + y][0][z]:
                    DFS(x+y, 0, z)

            else:
                if not visited[a][y - (a - x)][z]:
                    DFS(a, y - (a - x), z)

        if z < c:
            if y < c - z:
                if not visited[x][0][z + y]:
                    DFS(x, 0, z + y)

    if z != 0:
        if x < a:
            if z < a - x:
                if not visited[x + z][y][0]:
                    DFS(x + z, y, 0)

            else:
                if not visited[a][y][z - (a - x)]:
                    DFS(a, y, z - (a - x))

        if y < b:
            if z < b - y:
                if not visited[x][y + z][0]:
                    DFS(x, y + z, 0)

            else:
                if not visited[x][b][z - (b - y)]:
                    DFS(x, b, z - (b - y))

DFS(0, 0, c)
A = sorted(A)
for i in range(len(A)):
    print(A[i], end=' ')