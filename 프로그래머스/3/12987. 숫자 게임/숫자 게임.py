import sys

def solution(A, B):

    answer = 0
    vB = [False for _ in range(len(B))]
    A.sort()
    B.sort()
    iB = 0
    
    for iA in range(len(A)):
        for j in range(iB, len(B)):
            if B[j] > A[iA]:
                iB = j+1
                answer += 1
                break
        
        if j == len(B)-1:
            break
            
    return answer
