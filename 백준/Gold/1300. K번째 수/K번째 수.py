import sys

sys.setrecursionlimit(100000)
from collections import deque

input = sys.stdin.readline

def count_less_equal(x, n):
    # x 이하의 수의 개수를 계산하는 함수
    count = 0
    for i in range(1, n+1):
        count += min(x // i, n)
    return count

def kth_number(n, k):
    left, right = 1, n * n
    while left < right:
        mid = (left + right) // 2
        if count_less_equal(mid, n) < k:
            left = mid + 1
        else:
            right = mid
    return left

n = int(input().strip())
m = int(input().strip())
print(kth_number(n, m))