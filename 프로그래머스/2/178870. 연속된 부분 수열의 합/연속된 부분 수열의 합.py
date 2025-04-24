# 합배열 만들기
# 

def solution(sequence, k):
    answer = []
    sum_ = [sequence[0]]
    
    for i in range(1, len(sequence)):
        sum_.append(sum_[i-1] + sequence[i]) 
    
    left = 0
    for right in range(len(sequence)):
        left_num = 0
        if left != 0:
            left_num = sum_[left-1]
            
        if sum_[right] - left_num < k:
            continue
        
        if sum_[right] - left_num > k:
            while sum_[right] - left_num > k and left < right:
                left += 1
                left_num = sum_[left-1]
        
        
        if sum_[right] - left_num == k:
            answer.append([left, right])
    
    answer.sort(key = lambda x: x[1] - x[0])
    
    return answer[0]