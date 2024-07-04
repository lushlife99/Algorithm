import sys
from queue import PriorityQueue
input = sys.stdin.readline

N = input()
myQueue = PriorityQueue()

for i in range(int(N)):
    num = int(input())
    if num == 0 :
        if myQueue.empty():
            print('0')

        else:
            temp = myQueue.get()
            print(str((temp[1])))
    else :
        myQueue.put((abs(num), num))

