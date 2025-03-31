# want 배열의 구간합 구하기
# 처음에 10일 개수 세기.

from collections import deque

def is_joinable(want, number, fruits_dict):
    for i in range(len(want)):
        if want[i] in fruits_dict:
            if fruits_dict[want[i]] < number[i]:
                return False
        else: return False
    
    return True

def solution(want, number, discount):
    answer = 0    
    fruits_deque = deque()
    fruits_dict = {}
    
    for i in range(10):
        fruits_deque.append(discount[i])
        if discount[i] in fruits_dict:
            fruits_dict[discount[i]] += 1
        else: fruits_dict[discount[i]] = 1
                
    if is_joinable(want, number, fruits_dict):
        answer += 1
    
    for i in range(10, len(discount)):
        fruit = fruits_deque.popleft()
        fruits_dict[fruit] -= 1
        fruits_deque.append(discount[i])
        if discount[i] in fruits_dict:
            fruits_dict[discount[i]] += 1
        else: fruits_dict[discount[i]] = 1
        if is_joinable(want, number, fruits_dict):
            answer += 1
            
    return answer