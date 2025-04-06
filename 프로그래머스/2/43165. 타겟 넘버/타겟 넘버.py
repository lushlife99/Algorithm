import sys
sys.setrecursionlimit(100000)

def dfs(current, target, numbers, cnt):
    if cnt == len(numbers):
        return 1 if current == target else 0
    
    return dfs(current + numbers[cnt], target, numbers, cnt + 1) + dfs(current - numbers[cnt], target, numbers, cnt + 1)
            
    return result

def solution(numbers, target):
    return dfs(0, target, numbers, 0)