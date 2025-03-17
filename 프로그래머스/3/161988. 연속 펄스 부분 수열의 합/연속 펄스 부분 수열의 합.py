def solution(sequence):
    answer = -1e10
    size = len(sequence)
    s1_min = s2_min =0
    s1 = s2 = 0
    pulse = 1
    
    for num in sequence:
        s1 += num * pulse
        s2 += num * -pulse
        pulse *= -1
        
        answer = max(answer, s1-s1_min, s2-s2_min)
        
        s1_min = min(s1, s1_min)
        s2_min = min(s2, s2_min)
        
        
    return answer