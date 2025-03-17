import sys
sys.setrecursionlimit(100000)

dx = [0, 0, 1, -1]
dy = [1, -1, 0, 0]

def dfs(x, y, direction, board, costs, N): # direction : 1, 2, 3, 4 위 아래 왼 오
    
    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]
        if 0 <= nx < N and 0 <= ny < N and board[nx][ny] == 0:
            cost = 100
            if direction != i:
                cost += 500
            
            if costs[nx][ny] >= costs[x][y] + cost - 100:
                costs[nx][ny] = costs[x][y] + cost
                dfs(nx, ny, i, board, costs, N)
    

def solution(board):
    INF = 1e10
    N = len(board)
    costs = [[INF for _ in range(N)] for _ in range(N)]
    costs[0][0] = -500
    dfs(0, 0, -1, board, costs, N)
    print(costs)
    return (costs[N-1][N-1])