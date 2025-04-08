def solution(numbers):
    answer = [-1] * len(numbers)
    stk = []
    for i in range(len(numbers)):
        while stk and numbers[stk[-1]] < numbers[i]:
            answer[stk.pop()] = numbers[i]
        stk.append(i)
    return answer