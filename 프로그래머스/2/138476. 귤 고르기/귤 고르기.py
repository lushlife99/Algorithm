def solution(k, tangerine):
    answer = 0
    type_dict = {}
    for i in range(len(tangerine)):
        if tangerine[i] in type_dict:
            type_dict[tangerine[i]] += 1
        else:
            type_dict[tangerine[i]] = 1
            
    values = sorted(type_dict.values(), reverse=True)
    for value in values:
        k -= value
        answer += 1
        if k <= 0:
            break
    return answer