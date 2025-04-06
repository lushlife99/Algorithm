from collections import Counter

def solution(str1, str2):
    str1, str2 = str1.upper(), str2.upper()
    str1_sublist = []
    str2_sublist = []
    for i in range(len(str1)-1):
        if ord("A") <= ord(str1[i]) <= ord("Z") and ord("A") <= ord(str1[i+1]) <= ord("Z"):
            str1_sublist.append(str1[i:i+2])
    
    for i in range(len(str2)-1):
        if ord("A") <= ord(str2[i]) <= ord("Z") and ord("A") <= ord(str2[i+1]) <= ord("Z"):
            str2_sublist.append(str2[i:i+2])
    
    cnt = 0
    total_count = 0
    str1_counter = Counter(str1_sublist)
    str2_counter = Counter(str2_sublist)
    
    if len(str1_counter) == 0 and len(str2_counter) == 0:
        return 65536
    
    for key in str1_counter.keys():
        if key in str2_counter:
            cnt += min(str1_counter[key], str2_counter[key])
            total_count += max(str1_counter[key], str2_counter[key])
        else:
            total_count += str1_counter[key]
    
    for key in str2_counter.keys():
        if key not in str1_counter:
            total_count += str2_counter[key]
    
    
    return int(cnt / total_count * 65536)