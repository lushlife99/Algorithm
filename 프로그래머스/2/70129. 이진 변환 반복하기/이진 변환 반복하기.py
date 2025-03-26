def solution(s):
    total_cnt = 0
    iter_cnt = 0
    
    while True:
        iter_cnt += 1
        zero_cnt = 0
        for i in range(len(s)):
            if s[i] == '0':
                zero_cnt += 1
        total_cnt += zero_cnt
        if len(s) - zero_cnt == 1:
            break
        s = bin(len(s) - zero_cnt)[2:]
            
            
    
    return [iter_cnt, total_cnt]