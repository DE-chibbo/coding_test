'''
programmers basic test : passed
programmers final test : passed
- all passed

time complexity : O(nlogn)
'''

def csh_best_album_solution(genres, plays):
    answer = []
    
    allPlaysByGenre = {}
    allIdxByGenre = {}
    for g, p, i in zip(genres, plays, list(range(len(plays)))):
        allPlaysByGenre[g] = allPlaysByGenre.get(g, [])
        allPlaysByGenre[g].append(p)
        allIdxByGenre[g] = allIdxByGenre.get(g, [])
        allIdxByGenre[g].append(i)
    
    allPlaysByGenre = dict(sorted(allPlaysByGenre.items(), key=lambda item: sum(item[1]), reverse=True))
    
    for item in allPlaysByGenre.items():
        idxSortedByPlays = [list(c) for c in sorted(zip(item[1], allIdxByGenre[item[0]]), key=lambda x: (-x[0], x[1]))]
        saveCnt = 2
        for play, idx in idxSortedByPlays:
            if saveCnt > 0:
                answer.append(idx)
            else:
                break
            saveCnt -= 1

    return answer