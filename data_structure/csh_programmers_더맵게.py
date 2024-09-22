'''
programmers basic test : passed
programmers final test : passed
- all passed
'''

import heapq

def csh_more_spicy(scoville, K):
    heapq.heapify(scoville)
    
    shake_cnt = 0
    
    if scoville[0] >= K:
        return shake_cnt
    
    while len(scoville) > 1:
        shake_cnt += 1
        
        first_min = heapq.heappop(scoville)
        second_min = heapq.heappop(scoville)
        shaked_scoville = first_min + (second_min * 2)
        heapq.heappush(scoville, shaked_scoville) # 정확성 71 효율성 16.1(100)
        
        if scoville[0] >= K:
            return shake_cnt
        
    return -1