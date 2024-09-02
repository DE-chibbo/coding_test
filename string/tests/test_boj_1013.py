import unittest
from ..csh_boj_1013 import *

class TestSolution(unittest.TestCase):
    def setUp(self):
        # 전체 테스트 케이스 작성
        self.test_cases = [
            (
                "3\n10010111\n011000100110001\n0110001011001\n",
                "NO\nNO\nYES\n",
            ),
        ]
    

    # csh가 작성한 함수를 테스트
    def test_csh(self):
        for i, (input_data, expected_output) in enumerate(self.test_cases):
            input_lines = input_data.strip().split('\n')
            N = int(input_lines[0])
            signals = input_lines[1:N + 1]
            
            result = csh_boj_1013(N, signals)
            assert result == expected_output, f"Test case {i + 1} failed: expected {expected_output}, got {result}"
            break
    
    print("All test cases passed!")


if __name__ == "__main__":
    unittest.main(verbosity=2)
