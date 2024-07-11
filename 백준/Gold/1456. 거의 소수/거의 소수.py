import sys
import math
from queue import PriorityQueue

input = sys.stdin.readline
start, end = map(int, input().strip().split())

numbers = [0] * (10000001)
for i in range(2, len(numbers)):
    numbers[i] = i

count = 0

for i in range(2, int(math.sqrt(len(numbers))) + 1):
    if numbers[i] == 0:
        continue

    for j in range(i * i, len(numbers), i):
        numbers[j] = 0

count = 0
for i in range(2, 10000001):
    if numbers[i] != 0:
        temp = numbers[i]
        while numbers[i] <= end / temp:
            if numbers[i] >= start / temp:
                count += 1
            temp = temp * numbers[i]

print(count)