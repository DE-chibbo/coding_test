def solution(arr):
    answer = []
    answer.append(arr[0])  # O(1)
    for i in arr[1:]:      # O(n)
        if answer[-1] != i:  # O(1) answer의 마지막 요소와 현재 요소를 비교
            answer.append(i)  # O(1): 중복되지 않는 경우 현재 요소를 answer에 추가
    return answer  # O(1)