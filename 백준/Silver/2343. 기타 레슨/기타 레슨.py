import sys

sys.setrecursionlimit(100000)
from collections import deque

input = sys.stdin.readline

n, m = map(int, input().strip().split())
array = list(map(int, input().strip().split()))
start = max(array)
end = sum(array)
sum = 0

while start <= end:
    mid = int((start + end) / 2)  # 블루레이 중앙값
    idx = 0
    count = 0
    sum = 0
    # m개의 블루레이에 담길 수 있는지 체크

    while idx < len(array):
        if sum + array[idx] <= mid:
            sum += array[idx]
            idx += 1
        

        else:
            count += 1
            sum = 0
    if sum != 0 : count += 1

    if count <= m :
        end = mid - 1
    else : start = mid + 1

print(start)