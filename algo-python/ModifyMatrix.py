from typing import List


class Solution:
    def modifiedMatrix(self, matrix: List[List[int]]) -> List[List[int]]:
        result = list()
        for i in range(0, len(matrix)):
            result.append([0] * len(matrix[0]))
        update_required_j = []
        for i in range(0, len(matrix[0])):
            max_value = 0
            for j in range(0, len(matrix)):
                result[j][i] = matrix[j][i]
                if matrix[j][i] > max_value:
                    max_value = matrix[j][i]
                if matrix[j][i] == -1:
                    update_required_j.append(j)
            for j in update_required_j:
                result[j][i] = max_value
            update_required_j.clear()
        return result
