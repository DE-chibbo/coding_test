import heapq

def solution(scoville, K):
    # 최소 힙으로 변환
    heapq.heapify(scoville)
    
    # 섞은 횟수
    mix_count = 0
    
    # 가장 작은 값이 K 이상이 될 때까지 반복
    while scoville[0] < K:
        # 섞을 수 없는 경우 -1 반환 (힙에 2개 미만의 요소가 남았을 때)
        if len(scoville) < 2:
            return -1
        
        # 가장 맵지 않은 두 개의 음식 꺼내기
        first_min = heapq.heappop(scoville)
        second_min = heapq.heappop(scoville)
        
        # 새로운 음식의 스코빌 지수 계산
        new_scoville = first_min + (second_min * 2)
        
        # 새로운 음식 다시 힙에 넣기
        heapq.heappush(scoville, new_scoville)
        
        # 섞은 횟수 증가
        mix_count += 1
    
    # 모든 음식의 스코빌 지수가 K 이상이 되었을 때 섞은 횟수 반환
    return mix_count