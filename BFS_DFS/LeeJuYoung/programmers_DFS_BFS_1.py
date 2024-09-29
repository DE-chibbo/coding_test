def solution(numbers, target):
    def dfs(index, current_sum):
        # 모든 숫자를 다 사용했을 때
        if index == len(numbers):
            # 현재 합이 타겟과 같으면 1을 반환
            return 1 if current_sum == target else 0
        # 현재 숫자를 더하거나 빼는 두 가지 선택지를 각각 시도
        return dfs(index + 1, current_sum + numbers[index]) + dfs(index + 1, current_sum - numbers[index])

    # dfs 탐색을 시작 (첫 번째 숫자부터 시작, 현재 합은 0)
    return dfs(0, 0)
