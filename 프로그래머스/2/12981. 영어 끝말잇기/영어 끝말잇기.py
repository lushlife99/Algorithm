def submit(answer_set, word, first_spelling):
    
    if len(word) == 1:
        return False
    
    if word[0] != first_spelling:
        return False
    
    if word in answer_set:
        return False
    
    answer_set.add(word)
    return True

def solution(n, words):
    answer = [0, 0]
    answer_set = set()
    last_word = words[0][0]
    cnt = 0
    for word in words:
        if not submit(answer_set, word, last_word[-1]):
            print(word)
            break
        last_word = word
        cnt += 1
            
    if cnt != len(words):
        turn = (cnt+1) % n
        if (cnt+1) % n == 0:
            turn = n
            
        answer = [turn, 1 + (cnt) // n]

    return answer