import sys
from queue import PriorityQueue

input = sys.stdin.readline
plusPq = PriorityQueue()
minusPq = PriorityQueue()
n = int(input())
sum = 0

# 입력값을 큐에 삽입
for i in range(n):
    num = int(input())
    if (num <= 0):
        minusPq.put(num)
    else :
        plusPq.put(-num)

# 첫 번째 단계: 곱셈 연산을 처리
while minusPq.qsize() > 1:
    num1 = minusPq.get()
    num2 = minusPq.get()

    sum += num1 * num2

while plusPq.qsize() > 1:
    num1 = -plusPq.get()
    num2 = -plusPq.get()

    if num2 == 1:
        sum += num1 + num2
    else : sum += num1 * num2


# 큐에 하나의 요소가 남아있는 경우 처리
if minusPq.qsize() == 1:
    sum += minusPq.get()

if plusPq.qsize() == 1:
    sum += -plusPq.get()

print(sum)
