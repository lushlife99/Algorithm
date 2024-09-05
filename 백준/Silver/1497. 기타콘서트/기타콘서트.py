import sys
sys.setrecursionlimit(100000)

N, M = map(int, input().split())
able = [[] for _ in range(N)]
max_song_cnt = 0
min_guitar_cnt = 100000
visited = [False] * M


def solve(song_cnt, guitar_cnt, start_idx):
    global max_song_cnt, min_guitar_cnt

    if max_song_cnt < song_cnt:
        max_song_cnt = song_cnt
        min_guitar_cnt = guitar_cnt

    elif max_song_cnt == song_cnt and min_guitar_cnt > guitar_cnt:
        min_guitar_cnt = guitar_cnt

    for i in range(start_idx, N):
        cnt = 0
        arr = []
        for j in range(len(able[i])):
            if able[i][j] == "Y" and not visited[j]:
                arr.append(j)
                cnt += 1

        if cnt != 0:
            for idx in arr:
                visited[idx] = True
            solve(song_cnt + cnt, guitar_cnt + 1, i + 1)

            for idx in arr:
                visited[idx] = False


for i in range(N):
    name, arr = map(str, input().split())
    able[i] = arr


solve(0, 0, 0)

if max_song_cnt == 0:
    print(-1)
else : print(min_guitar_cnt)