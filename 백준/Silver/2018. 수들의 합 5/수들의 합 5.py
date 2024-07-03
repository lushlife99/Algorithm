import sys
import math
input = sys.stdin.readline

num = int(input())
start_idx = 1
end_idx = 1
sum = 1
count = 1

while end_idx != num:
    if sum == num:
        count += 1
        end_idx += 1
        sum += end_idx
    elif sum > num:
        sum -= start_idx
        start_idx += 1        
    else:
        end_idx += 1
        sum += end_idx

print(count)