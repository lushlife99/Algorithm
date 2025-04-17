from functools import cmp_to_key

def cmp_number(a, b):
    if str(a) + str(b) >= str(b) + str(a):
        return -1
    else:
        return 1

def solution(numbers):

    answer = sorted([str(number) for number in numbers], key=cmp_to_key(cmp_number))
    return str(int("".join(answer)))