import unittest
from csh_programmers_best_album import *

class TestSolution(unittest.TestCase):
    def setUp(self):
        # 전체 테스트 케이스 작성
        self.test_cases = [
            {
                'creator': 'csh',
                'genres': [],
                'plays': [],
                'expected_output': []
            },
            {
                'creator': 'csh',
                'genres': ["classic", "pop", "classic", "classic", "pop"],
                'plays': [500, 600, 150, 800, 2500],
                'expected_output': [4, 1, 3, 0]
            },
        ]
    

    # csh가 작성한 함수를 테스트
    def test_csh(self):
        test_cases = [case for case in self.test_cases if case['creator'] == 'csh']
        for i, case in enumerate(test_cases):
            genres = case['genres']
            plays = case['plays']
            expected_output = case['expected_output']
            
            with self.subTest(f"test_case_{i+1}"):
                self.assertEqual(csh_best_album_solution(genres, plays), expected_output)


if __name__ == "__main__":
    unittest.main(verbosity=2)
