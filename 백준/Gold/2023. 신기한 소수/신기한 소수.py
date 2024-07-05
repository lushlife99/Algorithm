import sys
import math
sys.setrecursionlimit(1000000)
input = sys.stdin.readline

def is_prime(k):
    if k <= 1:
        return False
    for i in range(2, int(k**0.5) + 1):
        if k % i == 0:
            return False
    return True

n = int(input())

def DFS(s):
    if s / int(math.pow(10, n-1)) >= 1 :
        print(s)
        return

    for i in range(10):
        if i in (1,3,7,9) and is_prime(s * 10 + i):
            DFS(s * 10 + i)

DFS(2)
DFS(3)
DFS(5)
DFS(7)
