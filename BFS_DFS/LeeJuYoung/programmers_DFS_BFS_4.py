from collections import deque

# 두 단어가 한 글자만 다른지 확인하는 함수
def one_letter_diff(word1, word2):
    diff_count = 0
    for c1, c2 in zip(word1, word2):
        if c1 != c2:
            diff_count += 1
        if diff_count > 1:
            return False
    return diff_count == 1

def solution(begin, target, words):
    # target이 words에 없다면 변환할 수 없음
    if target not in words:
        return 0
    
    # BFS 탐색을 위한 큐와 방문 기록
    queue = deque([(begin, 0)])  # (단어, 변환 횟수)
    visited = set()  # 방문한 단어를 기록하는 집합
    
    while queue:
        current_word, steps = queue.popleft()
        
        # target에 도달했으면 변환 횟수 반환
        if current_word == target:
            return steps
        
        # 방문 처리
        visited.add(current_word)
        
        # 현재 단어에서 한 글자만 다른 단어들을 찾아 큐에 추가
        for word in words:
            if word not in visited and one_letter_diff(current_word, word):
                queue.append((word, steps + 1))
    
    # target에 도달할 수 없으면 0 반환
    return 0
