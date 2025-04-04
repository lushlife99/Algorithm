from collections import Counter

def solution(topping):
    answer = 0
    counter = Counter(topping)
    counter2 = {}
    for i in range(len(topping)):
        counter[topping[i]] -= 1
        if counter[topping[i]] == 0:
            del counter[topping[i]]
        counter2[topping[i]] = 1
        if len(counter.keys()) == len(counter2.keys()):
            answer += 1
    
    return answer