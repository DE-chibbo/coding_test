from collections import defaultdict

class Solution:

    # 주어진 빈도 딕셔너리에서 가장 높은 빈도와 가장 낮은 빈도의 차이를 계산하는 함수
    def beauty(self, freq):
        # 초기값 설정: 가장 높은 빈도(mf)는 -1로, 가장 낮은 빈도(lf)는 무한대로 설정
        mf = -1
        lf = float('inf')

        # 빈도 딕셔너리의 값들을 순회하며, mf와 lf를 갱신
        for val in freq.values():
            mf = max(mf, val)  # 현재 값과 비교하여 가장 큰 빈도값으로 갱신
            lf = min(lf, val)  # 현재 값과 비교하여 가장 작은 빈도값으로 갱신

        # 가장 높은 빈도와 가장 낮은 빈도의 차이를 반환
        return mf - lf

    # 문자열 s의 모든 부분 문자열들의 아름다움(beauty)의 총합을 계산하는 함수
    def beautySum(self, s: str) -> int:
        
        # 문자열의 길이 n을 계산
        n = len(s)
        
        # 결과값(res)을 초기화
        res = 0

        # 문자열의 모든 시작 위치 i에 대해
        for i in range(n):

            # 빈도수를 저장할 defaultdict를 초기화
            freq = defaultdict(int)

            # i부터 시작하는 모든 부분 문자열의 끝 위치 j에 대해
            for j in range(i, n):
                # 현재 문자 s[j]의 빈도를 1 증가
                freq[s[j]] += 1
                
                # 현재 부분 문자열의 아름다움을 계산하여 결과값에 더함
                res += self.beauty(freq)

        # 최종적으로 모든 부분 문자열의 아름다움의 총합을 반환
        return res
