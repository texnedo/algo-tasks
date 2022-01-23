class Solution:
    def minKnightMoves(self, x: int, y: int) -> int:
        current_layer = [(0, 0)]
        next_layer = []
        visited = set()
        visited.add((0, 0))
        count_moves = 0
        found = False
        while len(current_layer) > 0:
            for item in current_layer:
                if item[0] == x and item[1] == y:
                    return count_moves
                possible_points = self.get_possible_points(item[0], item[1])
                for next_item in possible_points:
                    if next_item not in visited:
                        visited.add(next_item)
                        next_layer.append(next_item)
            count_moves += 1
            current_layer = []
            current_layer.extend(next_layer)
            next_layer = []
        return count_moves

    def get_possible_points(self, x: int, y: int) -> list:
        result = []
        result.append((x + 2, y + 1))
        result.append((x + 2, y - 1))

        result.append((x - 2, y + 1))
        result.append((x - 2, y - 1))

        result.append((x + 1, y + 2))
        result.append((x - 1, y + 2))

        result.append((x + 1, y - 2))
        result.append((x - 1, y - 2))
        return result


if __name__ == '__main__':
    s = Solution()
    print(s.minKnightMoves(3, 4))
    #print(s.minKnightMoves(5, 5))