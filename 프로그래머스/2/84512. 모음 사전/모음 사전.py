# 알파뱃 넣기
# 

def solution(word):
    alpha = ["A", "E", "I", "O", "U"]
    dictionary = ["A", "E", "I", "O", "U"]
    prefix = 0
    for i in range(4):
        length = len(dictionary)
        for j in range(prefix, length):
            for k in range(5):
                dictionary.append(dictionary[j] + alpha[k])
        prefix = length
    dictionary.sort()
    return dictionary.index(word)+1