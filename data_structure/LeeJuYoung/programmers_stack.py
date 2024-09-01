def solution(prices):
    # 결과를 저장할 리스트
    answer = []

    # prices 리스트를 순회할 때의 기준 인덱스
    pivot = 0

    # prices 리스트의 각 요소에 대해 처리
    for i in range(len(prices)):
        price = prices[i]  # 현재 가격
        length = len(prices) - 1 - i  # 기본적으로 남은 기간 동안 유지된다고 가정
        
        # 현재 가격 이후의 가격들과 비교
        for k in range(i+1, len(prices)):
            if price > prices[k]:  # 현재 가격보다 낮은 가격이 나타나면
                length = k - i  # 그 시점까지의 기간을 length로 설정
                break  # 더 이상 비교할 필요 없으므로 루프 종료
            else:
                continue  # 낮은 가격이 나오지 않았다면 계속해서 다음 가격 비교
        
        # 계산된 기간을 결과 리스트에 추가
        answer.append(length)
    
    return answer  # 결과 반환
