'''
boj test : 테스트 미진행
self basic test : failed

time complexity : O(n + K log n)
- n : 모든 문자 개수(!= N)
'''

def csh_boj_1036(N, words, K):
    import heapq

    # input = sys.stdin.read().splitlines()
    # N = int(input[0])
    # words = input[1:N+1]
    # K = int(input[N+1])   

    mapped_char_location = {}
    minheap = []

    for i, word in enumerate(words):
        for j, ch in enumerate(word):
            mapped_char_location[ch] = mapped_char_location.get(ch, [])
            mapped_char_location[ch].append((i, j))

    minheap = list(mapped_char_location.keys())
    heapq.heapify(minheap)

    while minheap:
        min_char = heapq.heappop(minheap)
        for location in mapped_char_location[min_char]:
            words_location = location[0]
            char_location = location[1]
            words[words_location] = (
                words[words_location][:char_location] + 'Z' + words[words_location][char_location + 1:]
            )

            K -= 1
            if K == 0:
                break
            
        if K == 0:
            break

    result = '0'
    for base36 in words:
        result = sum_base36(result, base36)
        
    print(result)
    return result

def base10_to_base36(base10: int) -> str:
    base36 = ''
    if base10 == 0:
        base36 = '0'
    
    while base10 > 0:
        base10, r = divmod(base10, 36)
        if r < 10:
            base36 = str(r) + base36
        else:
            base36 = chr(55 + r) + base36
    
    return base36

def base36_to_base10(base36: str) -> int:
    return int(base36, 36)

def sum_base36(n: str, m: str) -> str:
    sum_base10 = base36_to_base10(n) + base36_to_base10(m)
    return base10_to_base36(sum_base10)