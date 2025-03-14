import sys
sys.setrecursionlimit(100000)

def transible(w1, w2):
    """한 글자만 다른지 확인하는 함수"""
    return sum(1 for i in range(len(w1)) if w1[i] != w2[i]) == 1

def dfs(w, words, target, cnt, visited):
    """DFS 탐색 수행 (최소 변환 단계 찾기)"""
    if w == target:
        return cnt  # 목표 단어에 도달하면 변환 횟수 반환

    min_steps = float('inf')  # 최솟값을 찾기 위해 초기화

    for i, word in enumerate(words):
        if not visited[i] and transible(w, word):
            visited[i] = True
            steps = dfs(word, words, target, cnt + 1, visited)
            visited[i] = False  # 백트래킹
            if steps:
                min_steps = min(min_steps, steps)

    return min_steps if min_steps != float('inf') else 0  # 최솟값 반환

def solution(begin, target, words):
    if target not in words:
        return 0  # 변환할 수 없는 경우

    visited = [False] * len(words)
    return dfs(begin, words, target, 0, visited)
