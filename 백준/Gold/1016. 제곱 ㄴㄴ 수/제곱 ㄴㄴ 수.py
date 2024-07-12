import sys
import math
from queue import PriorityQueue

input = sys.stdin.readline
start, end = map(int, input().strip().split())
limit = 1000002
numbers = [False] * (end - start + 1)

def get_numbers(min, max, array):

    for i in range(2, int(math.sqrt(max)) + 1):
        startNum = i * i
        start_index = int(min/startNum)
        if min % startNum != 0:
            start_index += 1

        for j in range(start_index, int(max/startNum) + 1):
            array[j * startNum - min] = True

get_numbers(start, end, numbers)
count = 0
for i in range(0, end - start + 1):
    if not numbers[i]:
        count += 1

print(count)