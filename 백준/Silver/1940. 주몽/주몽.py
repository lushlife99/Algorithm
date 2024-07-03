import sys
input = sys.stdin.readline

n = int(input())
m = int(input())

start_idx = 0
end_idx = n - 1
sum = 1
count = 0

mylist = list(map(int, input().split()))
mylist.sort()
val1 = mylist[0]
val2 = mylist[-1]

while val1 < val2 :
    sum = val1 + val2
    if sum == m:
        count += 1
        if start_idx < end_idx:
            start_idx += 1
            val1 = mylist[start_idx]
            end_idx -= 1
            val2 = mylist[end_idx]
        else: break
    elif sum > m:
        end_idx -= 1
        val2 = mylist[end_idx]
    else:
        start_idx += 1
        val1 = mylist[start_idx]

print(count)