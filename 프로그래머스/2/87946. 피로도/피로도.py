answer = 0

def dfs(dungeons, visited, tired, cnt):
    global answer
    
    if cnt > answer:
        answer = cnt
    
    for i in range(len(dungeons)):
        if not visited[i] and dungeons[i][0] <= tired:
            visited[i] = True
            dfs(dungeons, visited, tired - dungeons[i][1], cnt + 1)
            visited[i] = False

def solution(k, dungeons):
    global answer
    dfs(dungeons, [False] * len(dungeons), k, 0)
    return answer