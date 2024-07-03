import sys
input = sys.stdin.readline

n = int(input())

start = 0
end = n - 1
sum = 1
count = 0

mylist = list(map(int, input().split()))
mylist.sort()
val1 = mylist[0]
val2 = mylist[-1]

for i in range(len(mylist)):

    start = 0
    end = n - 1

    while start < end:
        sum = mylist[start] + mylist[end]
        if sum == mylist[i]:
            if start != i and end != i:
                count += 1
                break
            elif start == i:
                start += 1
            else:
                end -= 1
        elif sum > mylist[i]:
            end -= 1
        else :
            start += 1

print(count)