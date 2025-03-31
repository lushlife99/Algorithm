# 수학 문제인듯
# 규칙 찾아보자
# n = 3, left 2, right 5 일 때 left // 3 = 0이므로 0번째 행에서 2+1 번째 
# right // 3 = 1이므로 1번째 행에서 2+1번째
# 3 2 2 3

def solution(n, left, right):
    answer = []
    left_row, left_col = left // n, left % n
    right_row, right_col = right // n, right % n
    
    for i in range(left, right+1):
        if i % n > i // n:
            value = i % n + 1
        else:
            value = i // n + 1
        answer.append(value)
    return answer