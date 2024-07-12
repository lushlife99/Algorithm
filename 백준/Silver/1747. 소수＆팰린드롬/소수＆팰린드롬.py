import sys
import math
from queue import PriorityQueue

input = sys.stdin.readline

n = int(input())
maxNumber = 1100000
prime_numbers = [1] * maxNumber
def get_prime_numbers() :
    global prime_numbers
    prime_numbers[1] = 0
    for i in range(2, int(math.sqrt(maxNumber)) + 1):
        if prime_numbers[i]:
            for j in range(i * i, maxNumber, i) :
                prime_numbers[j] = 0

def is_symmetry_number(number) :
    strNumber = str(number)
    strLength = len(strNumber)
    for i in range(len(strNumber) // 2):
        if strNumber[i] != strNumber[strLength - i - 1]:
            return False

    return True

get_prime_numbers()
for i in range(n, maxNumber):

    if prime_numbers[i] and is_symmetry_number(i):
        print(i)
        break

