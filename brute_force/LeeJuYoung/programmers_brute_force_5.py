from itertools import permutations

def solution(k, dungeons):
    # 최대 탐험할 수 있는 던전 수를 기록할 변수 초기화
    max_dungeons = 0
    
    # 모든 던전 순서에 대해 탐색 (던전 순서의 모든 경우의 수를 구함)
    # permutations(dungeons)는 던전의 순서를 전부 섞어서 나올 수 있는 모든 순열을 생성
    for perm in permutations(dungeons):
        current_k = k  # 현재 피로도를 주어진 k로 초기화 (던전 탐험 중 남은 피로도)
        count = 0  # 탐험한 던전의 개수를 기록하는 변수
        
        # 던전 순서(perm)에 대해 하나씩 탐험을 시도
        for min_fatigue, consume_fatigue in perm:
            # 현재 피로도가 최소 필요 피로도 이상일 때만 던전을 탐험 가능
            if current_k >= min_fatigue:
                current_k -= consume_fatigue  # 탐험 후 피로도를 소모
                count += 1  # 탐험한 던전의 수 증가
            else:
                # 피로도가 부족하여 탐험할 수 없으면 더 이상 진행하지 않고 중단
                break
        
        # 최대 탐험한 던전 수를 갱신 (현재 탐험한 count가 더 크면 업데이트)
        max_dungeons = max(max_dungeons, count)
    
    # 최종적으로 탐험할 수 있는 던전의 최대 개수를 반환
    return max_dungeons
