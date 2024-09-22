'''
programmers basic test : passed
programmers final test : passed
- all passed
'''

def csh_fatigue(k, dungeons):
    answer = []
    
    for i, dungeon in enumerate(dungeons):
        if dungeon[0] > k:
            answer.append(0)
        else:
            answer.append(1 + rec(k - dungeon[1], dungeons[:i] + dungeons[i+1:]))
                             
    return max(answer)

def rec(k, dungeons):
    if not dungeons:
        return 0
    
    max_explored = 0
    for i, dungeon in enumerate(dungeons):
        if dungeon[0] > k:
            continue
        max_explored = max(max_explored, 1 + rec(k - dungeon[1], dungeons[:i] + dungeons[i+1:]))
        
    return max_explored