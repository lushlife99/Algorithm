import sys
import math
from queue import PriorityQueue
input = sys.stdin.readline

start, end = map(int, input().strip().split())
if start == 1:
    start = 2
numbers = [1] * (end + 1)
numbers[0] = 0
numbers[1] = 0

for i in range(2, int(math.sqrt(end)) + 1):
    if numbers[i] == 0:
        continue

    for j in range(i * 2, end+1, + i):
        numbers[j] = 0

for i in range(start, end+1):
    if numbers[i] == 1:
        print(i)