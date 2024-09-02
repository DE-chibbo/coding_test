import unittest
from ..csh_boj_1036 import *

class TestSolution(unittest.TestCase):
    def setUp(self):
        # 전체 테스트 케이스 작성
        self.test_cases = [
            (
                "5\nGOOD\nLUCK\nAND\nHAVE\nFUN\n7\n",
                "31YUB"
            ),
            (
                "1\nHELLO\n2\n",
                "ZZLLO"
            ),
            (
                "5\n500\nPOINTS\nFOR\nTHIS\nPROBLEM\n5\n",
                "1100TC85"
            ),
            (
                "6\nTO\nBE\nOR\nNOT\nTO\nBE\n0\n",
                "QNO"
            ),
            (
                "1\nKEQUALS36\n36\n",
                "ZZZZZZZZZ"
            ),
        ]
    

    # csh가 작성한 함수를 테스트
    def test_csh(self):
        for i, (input_data, expected_output) in enumerate(self.test_cases):
            input_lines = input_data.strip().split('\n')
            N = int(input_lines[0])
            numbers = input_lines[1:N + 1]
            K = int(input_lines[N + 1])
            
            result = csh_boj_1036(N, numbers, K)
            assert result == expected_output, f"Test case {i + 1} failed: expected {expected_output}, got {result}"
            break
    
    print("All test cases passed!")


if __name__ == "__main__":
    unittest.main(verbosity=2)
