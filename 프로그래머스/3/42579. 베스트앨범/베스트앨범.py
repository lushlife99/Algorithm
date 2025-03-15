def solution(genres, plays):
    answer = []
    genre_map = dict()
    
    for i in range(len(genres)):
        if genres[i] not in genre_map:
            genre_map[genres[i]] = (plays[i], [i]) # totalcounts, songs
        else:
            total_counts, songs = genre_map[genres[i]]
            total_counts += plays[i]
            
            if len(songs) == 0:
                songs.append(i)
            elif len(songs) == 1:
                songs.append(i)
                
                if plays[songs[0]] < plays[songs[1]]:
                    temp = songs[0]
                    songs[0] = songs[1]
                    songs[1] = temp
            elif plays[songs[1]] < plays[i]:
                songs[1] = i
                
                if plays[songs[0]] < plays[songs[1]]:
                    temp = songs[0]
                    songs[0] = songs[1]
                    songs[1] = temp
            
            genre_map[genres[i]] = (total_counts, songs)
    genre_key = sorted(genre_map.keys(), key=lambda g: genre_map[g][0], reverse=True)
    for genre in genre_key:
        for song in genre_map[genre][1]:
            print(genre_map[genre])
            answer.append(song)
        
        
    return answer