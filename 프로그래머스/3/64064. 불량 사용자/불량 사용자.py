answer = set()

def dfs(user_id, banned_id, visited, bI):
    
    global answer
    
    if bI == len(banned_id):
        arr = set()
        for i in range(len(visited)):
            if visited[i]:
                arr.add(user_id[i])
        answer.add(frozenset(arr))
        return
    
    for i in range(len(user_id)):
        id = banned_id[bI]
        if len(id) == len(user_id[i]) and not visited[i]:
            cnt = 0
            for j in range(len(user_id[i])):
                if id[j] == user_id[i][j] or id[j] == '*':
                    cnt += 1
            
            if cnt == len(user_id[i]):
                visited[i] = True
                dfs(user_id, banned_id, visited, bI+1)
                visited[i] = False

def solution(user_id, banned_id):
    visited = [False for _ in range(len(user_id))]
    dfs(user_id, banned_id, visited, 0)
    return len(answer)