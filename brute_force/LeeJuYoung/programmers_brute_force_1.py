def solution(sizes):
    answer = 0  # 최종적으로 반환할 결과 값
    max_val = 0  # 각 명함의 긴 변들 중에서 가장 큰 값을 저장할 변수
    min_val = 0  # 각 명함의 짧은 변들 중에서 가장 큰 값을 저장할 변수
    
    # 주어진 sizes 리스트에서 각 명함의 크기를 확인
    for i in range(len(sizes)):
        # 현재 명함의 가로 또는 세로 중 더 긴 값을 찾고, 그 값이 기존 max_val보다 크면 갱신
        if max_val < max(sizes[i]):
            max_val = max(sizes[i])
        
        # 명함의 짧은 변들을 고려하여, 그 중 가장 큰 값을 min_val에 갱신
        # 즉, 두 변 중 작은 값이 min_val보다 클 때만 갱신 (모든 명함을 가로 세로로 회전해 가장 작은 변 중 최대값을 찾음)
        if min_val < sizes[i][0] and min_val < sizes[i][1]:
            min_val = min(sizes[i])
    
    # 가장 큰 긴 변(max_val)과 가장 큰 짧은 변(min_val)을 곱하여 지갑 크기 계산
    answer = max_val * min_val
    return answer
