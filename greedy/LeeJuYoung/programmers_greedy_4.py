def solution(people, limit):
    answer = 0
    left = 0
    right = len(people)-1
    people.sort()
    bucket = limit
    while left <= right:
        bucket -= people[right]
        right -= 1
        if bucket >= people[left]:
            bucket -= people[left]
            left += 1
        answer += 1
        bucket = limit
    return answer