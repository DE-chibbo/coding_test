'''
boj test : runtime error(name error)
self basic test : passed

time complexity : O(n)
'''


def csh_boj_1013(N, signals):
    # input = sys.stdin.read().splitlines()
    # N = int(input[0])
    # words = input[1:N+1]
    # K = int(input[N+1])

    # (100+1+ | 01)+

    # 반복의 시작은 100, 01로 시작해야함
    # 반복 판단 기준
        # 100 시작 -> 1나오면 끝
        # 01 시작 -> 바로 끝

    result = ''

    for signal in signals:
        pointer = 0
        while pointer < len(signal):
            if signal[pointer:pointer+2] == '01':
                pointer += 2
            elif signal[pointer:pointer+3] == '100':
                pointer += 3
                while pointer < len(signal):
                    if signal[pointer] == '1':
                        pointer += 1
                        break
                    pointer += 1
            else:
                result += 'NO\n'
                break
            
            if pointer == len(signal):
                result += 'YES\n'
                break

   
    print(result)
    return result