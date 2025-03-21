import math
import sys
sys.setrecursionlimit(100000)

answer = set()
def is_prime(num): # 6k +-1 형태면 소수이다
    if num < 2:
        return False
    
    if num in (2,3):
        return True
    
    if num % 2 == 0 or num % 3 == 0:
        return False
    
    for i in range(5, math.isqrt(num) + 1, 6):
        if num % i == 0 or num % (i+2) == 0:
            return False
    
    return True

def dfs(numbers, visited, num):
    
    if is_prime(int(num)):
        answer.add(int(num))
    
    for i in range(len(numbers)):
        if not visited[i]:
            visited[i] = True
            dfs(numbers, visited, num + numbers[i])
            visited[i] = False
            

def solution(numbers):

    visited = [False for _ in range(len(numbers))]
    dfs(numbers, visited, '0')
    
    return len(answer)
