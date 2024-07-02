import sys

def input():
    return sys.stdin.readline().rstrip()

length, total_test_case = map(int, input().split())
values = list(map(int, input().split()))
sum_values = []
for idx in range(len(values)) :
    if(idx == 0):
        sum_values.append(values[idx])
    else:
        sum_values.append(sum_values[idx-1] + values[idx])

for test_case in range(total_test_case):
    start, end = map(int, input().split())
    start -= 1
    end -= 1
    if(start == 0):
        print(sum_values[end])
    else:
        print(sum_values[end] - sum_values[start-1])
