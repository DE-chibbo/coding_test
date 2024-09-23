# 프로그래머스 고득점 Kit - 힙 - 디스크 컨트롤러

import heapq

def solution(jobs):
    answer, now, i = 0, 0, 0
    start = -1
    heap = []
    
    while i < len(jobs):
        # 현재 시간(now) 이전에 도착한 작업을 힙에 추가
        for j in jobs:
            if start < j[0] <= now:
                heapq.heappush(heap, (j[1], j[0]))  # (소요 시간, 요청 시간) 추가
        
        if len(heap) > 0:
            # 최소 소요 시간 작업 꺼내기
            current = heapq.heappop(heap)
            start = now
            now += current[0]  # 작업 소요 시간을 더함
            answer += now - current[1]  # 대기 시간을 더함
            i += 1
        else:
            now += 1  # 대기 시간 증가

    return int(answer / len(jobs))  # 평균 대기 시간 반환