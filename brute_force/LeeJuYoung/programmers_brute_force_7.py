def solution(word):
    # 사용할 모음 리스트와 각 자리에서 나올 수 있는 단어들의 반복 패턴 길이를 정의
    vowels = ['A', 'E', 'I', 'O', 'U']
    # 자리마다의 가중치 (1자리, 2자리, 3자리 ...에서 나올 수 있는 경우의 수)
    weights = [781, 156, 31, 6, 1]  # 781 = 5^4 + 5^3 + 5^2 + 5^1 + 1
    
    result = 0  # 최종적으로 반환할 순번
    
    # 단어의 각 자리마다 해당하는 모음의 순번을 계산
    for i, char in enumerate(word):
        # 각 자리에 있는 문자가 모음 리스트에서 몇 번째인지 찾는다
        index = vowels.index(char)
        
        # 자리별 가중치를 곱해서 현재 문자가 사전에서 차지하는 순번을 더해준다
        result += index * weights[i]
        
        # 해당 문자가 사전에서 출현하는 첫 번째 단어까지의 개수를 더해줌 (자기 자신 포함)
        result += 1
    
    return result