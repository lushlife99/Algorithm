l, c = map(int, input().split())
alphabets = sorted(list(map(str, input().split())))
vowels = {"a", "e", "i", "o", "u"}
answer = []
def dfs(s, idx, vowel_count, consonant_count):
    if len(s) == l:
        if vowel_count >= 1 and consonant_count >= 2:
            answer.append(s)
        return

    for i in range(idx, c):
        a = alphabets[i]
        if a in vowels:
            dfs(s + a, i + 1, vowel_count + 1, consonant_count)
        else:
            dfs(s + a, i + 1, vowel_count, consonant_count + 1)

dfs("", 0, 0, 0)

answer = sorted(answer)
for i in answer:
    print(i)