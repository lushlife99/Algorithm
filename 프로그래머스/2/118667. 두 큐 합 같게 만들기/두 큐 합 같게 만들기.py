from collections import deque
    
def solution(queue1, queue2):
    q1_sum = sum(queue1)
    q2_sum = sum(queue2)
    queue = deque(queue1 + queue2)
    mid = len(queue1)-1
    answer = 0
    
    while answer < 1000000 and q1_sum != q2_sum:
        answer += 1
        if q1_sum < q2_sum:
            mid += 1
            q1_sum += queue[mid]
            q2_sum -= queue[mid]
        else:
            queue.append(queue.popleft())
            mid -= 1
            q2_sum += queue[-1]
            q1_sum -= queue[-1]
    
    if answer == 1000000:
        answer = -1
    return answer