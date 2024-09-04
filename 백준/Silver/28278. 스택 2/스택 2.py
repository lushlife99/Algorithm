import sys

class stk:
    my_list = []

    def stk(self):
        self.my_list = []

    def push(self, data):
        self.my_list.append(data)

    def pop(self):

        if len(self.my_list) == 0:
            return -1

        return self.my_list.pop()

    def size(self):
        return len(self.my_list)

    def isEmpty(self):

        if len(self.my_list) == 0:
            return 1

        return 0

    def peek(self):

        if self.isEmpty():
            return -1

        return self.my_list[-1]

N = int(input())
stack = stk()
answer = []
for _ in range(N):
    instruction = list(map(int, sys.stdin.readline().strip().split()))

    if instruction[0] == 1:
        stack.push(instruction[1])

    elif instruction[0] == 2:
        print(stack.pop())

    elif instruction[0] == 3:
        print(stack.size())
    elif instruction[0] == 4:
        print(stack.isEmpty())
    else:
        print(stack.peek())

