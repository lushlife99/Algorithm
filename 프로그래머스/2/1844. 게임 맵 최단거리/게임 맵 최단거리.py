import sys
sys.setrecursionlimit(100000)

dx = [0, 0, -1, 1]
dy = [1, -1, 0, 0]
        

def solution(maps):
    answer = 1e10
    visited = [[False for _ in range(len(maps[0]))] for _ in range(len(maps))]
    visited[0][0] = True
    queue = [(0, 0, 1)]
    while queue:
        x, y, cnt = queue.pop(0)
        
        if x == len(maps) - 1 and y == len(maps[0]) - 1:
            if answer > cnt:
                answer = cnt
            continue
        
        for i in range(4):
            cx = x + dx[i]
            cy = y + dy[i]
            
            if 0 <= cx < len(maps) and 0 <= cy < len(maps[0]) and maps[cx][cy] == 1 and not visited[cx][cy]:
                visited[cx][cy] = True
                queue.append((cx, cy, cnt+1))
                
    if visited[-1][-1] == False:
        return -1
    
    return answer