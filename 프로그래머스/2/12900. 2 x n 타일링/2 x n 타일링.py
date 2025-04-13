import sys
sys.setrecursionlimit(600000)

def fibo(n, memo):
    if n <= 3:
        return n
    if memo[n] != 0:
        return memo[n]
    memo[n] = (fibo(n-1, memo) + fibo(n-2, memo)) % 1000000007
    return memo[n]

def solution(n):
    if n <= 3:
        return n
    memo = [0] * (n + 1)
    memo[1] = 1
    memo[2] = 2
    memo[3] = 3

    return fibo(n, memo) 
