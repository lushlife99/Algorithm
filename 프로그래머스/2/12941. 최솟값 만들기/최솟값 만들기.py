import heapq

def solution(A,B):
    answer = 0
    B_desc_heap = []
    heapq.heapify(A)
    for i in range(len(B)):
        heapq.heappush(B_desc_heap, -B[i])
        
    for i in range(len(A)):
        answer += heapq.heappop(A) * -heapq.heappop(B_desc_heap)
        print(answer)
    return answer