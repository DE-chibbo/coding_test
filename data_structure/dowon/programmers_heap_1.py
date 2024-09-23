# 프로그래머스 고득점 Kit - 힙 - 더 맵게

import heapq
    
def solution(scoville, K):
    answer = 0
    # 리스트를 최소 힙으로 변환
    heapq.heapify(scoville)
    while(1):
        #모든 음식의 스코빌 지수를 K 이상으로 만들 수 없는 경우
        if len(scoville) == 1 and scoville[0] < K:
            answer = -1
            break
        if scoville[0] < K:
            # 가장 작은 수 추출
            a = heapq.heappop(scoville)
            # 두 번째로 작은 수 추출
            b = heapq.heappop(scoville)
            # 새로운 음식 추가
            heapq.heappush(scoville, a + (b * 2))
            answer+= 1
        else : 
            break
    return answer