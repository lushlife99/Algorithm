answer = 0

def dfs(sum_, current, target):
    global answer
    sum_ = sum_ + current
    
    if sum_ == target:
        answer += 1
        return
    
    if sum_ > target:
        return
    
    dfs(sum_, current+1, target)

def solution(n):
    global answer
    for i in range(1, n//2 + 1):
        dfs(0, i, n)
    
    return answer + 1