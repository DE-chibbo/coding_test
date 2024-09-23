# 프로그래머스 고득점 Kit - 완전탐색 - 피로도

from itertools import permutations

def solution(k, dungeons):
    answer = 0  # 최대 탐험할 던전 수 초기화
    
    dun_len = len(dungeons)  # 던전의 개수
    
    # 모든 던전 조합을 생성하여 순회 (순열)
    for permute in permutations(dungeons, dun_len):
        hp = k  # 초기 체력
        count = 0  # 탐험한 던전 수 초기화
        
        # 현재 던전 순서대로 탐험
        for pm in permute:
            # 현재 체력이 던전의 최소 필요 체력보다 크거나 같으면 탐험
            if hp >= pm[0]:
                hp -= pm[1]  # 체력을 소모
                count += 1  # 탐험한 던전 수 증가
            
            # 탐험한 던전 수가 현재 최대 탐험 수보다 많으면 갱신
            if count > answer:
                answer = count
    
    return answer