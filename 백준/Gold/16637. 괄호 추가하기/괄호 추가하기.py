import sys
from itertools import combinations

N = int(input())
expression = str(input())
answer = -sys.maxsize

def express(rpn_ex):
    stk = []
    operators = {'+', '-', '*'}

    for i in range(len(rpn_ex)):
        c = rpn_ex[i]
        if c in operators:
            operand2 = int(stk.pop())
            operand1 = int(stk.pop())
            if c == '+':
                stk.append(operand1 + operand2)
            elif c == '-':
                stk.append(operand1 - operand2)
            else: stk.append(operand1 * operand2)

        else: stk.append(c)

    return stk.pop()

def convert_rpn(ex, priority_idx_list):
    stk = []
    rpn_ex = []

    for i in range(N):
        if i % 2 == 1: # Operator일 경우
            op = ex[i]

            if i not in priority_idx_list:
                while stk:
                    rpn_ex.append(stk.pop())

            stk.append(op)

        else: # Operand일 경우
            rpn_ex.append(ex[i])

    while stk:
        rpn_ex.append(stk.pop())

    return rpn_ex

odd_numbers = [num for num in range(N) if num % 2 != 0]
for i in range(0, N//2):

    for combo in combinations(odd_numbers, i):
        if all(combo[i] + 2 < combo[i + 1] for i in range(len(combo) - 1)):
            res = express(convert_rpn(expression, combo))
            answer = max(res, answer)

if N == 1:
    print(expression)

else: print(answer)