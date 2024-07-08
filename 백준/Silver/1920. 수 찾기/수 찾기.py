import sys
sys.setrecursionlimit(100000)
from collections import deque
input = sys.stdin.readline

n = int(input())
numbers = list(map(int, input().strip().split()))
numbers = sorted(numbers)
total_find = int(input())
finds = list(map(int, input().split()))
def binary_search(arr, low, high, search):
    mid = (low + high) // 2

    if mid == low or mid == high:
        if search != arr[mid]:
            return 0

    if arr[mid] < search:
        return binary_search(arr, mid, high, search)
    elif arr[mid] > search:
        return binary_search(arr, low, mid, search)
    else:
        return 1


for num in finds:
    print(binary_search(numbers, 0, len(numbers), num))


