import math


class ExamRoom:
    def __init__(self, n: int):
        self.occupied = 0
        self.seats = [0] * n

    def seat(self) -> int:
        # TODO: find a way to cache intervals to allow fast free interval retrieval
        allocated_seat = -1
        if self.occupied == 0:
            self.seats[0] = 1
            allocated_seat = 0
        elif self.occupied == 1:
            self.seats[len(self.seats) - 1] = 1
            allocated_seat = len(self.seats) - 1
        else:
            ranges = list()
            current_range = [0, 0]
            for i in range(0, len(self.seats)):
                if self.seats[i] == 1:
                    current_range[1] = i - 1
                    if current_range[1] - current_range[0] >= 0:
                        ranges.append(current_range)
                    current_range = [0, 0]
                    current_range[0] = i + 1
                    current_range[1] = i + 1
            if current_range[0] < len(self.seats):
                current_range[1] = len(self.seats) - 1
                ranges.append(current_range)
            max_range = None
            max_range_size = -1
            for item in ranges:
                size = item[1] - item[0]
                if max_range_size < size:
                    max_range_size = size
                    max_range = item
            if max_range[0] == 0:
                self.seats[0] = 1
                allocated_seat = 0
            elif max_range[1] == len(self.seats) - 1:
                self.seats[len(self.seats) - 1] = 1
                allocated_seat = len(self.seats) - 1
            else:
                middle = math.floor((max_range[0] + max_range[1]) / 2)
                self.seats[middle] = 1
                allocated_seat = middle
        self.occupied += 1
        print(self.seats)
        return allocated_seat

    def leave(self, p: int) -> None:
        self.seats[p] = 0
        self.occupied -= 1


class ExamRoomV2:
    def __init__(self, n: int):
        self.max_seats = n
        self.seats = list()

    def seat(self) -> int:
        if len(self.seats) == 0:
            self.seats.append(0)
            return 0
        max_dist = self.seats[0]
        max_dist_seat = 0
        prev_seat = None
        for cur_seat in self.seats:
            if prev_seat is not None:
                dist = math.floor((cur_seat - prev_seat) / 2)
                if dist > max_dist:
                    max_dist_seat = prev_seat + dist
                    max_dist = dist
            prev_seat = cur_seat
        if prev_seat is not None:
            dist = self.max_seats - 1 - prev_seat
            if dist > max_dist:
                max_dist_seat = self.max_seats - 1
        self.seats.append(max_dist_seat)
        self.seats.sort()
        print(self.seats)
        return max_dist_seat

    def leave(self, p: int) -> None:
        self.seats.remove(p)


if __name__ == '__main__':
    obj = ExamRoomV2(10)
    print(obj.seat())
    print(obj.seat())
    print(obj.seat())
    print(obj.seat())
    print(obj.leave(4))
    print(obj.seat())
    # print(obj.seat())
    # print(obj.seat())
    # print(obj.seat())
    # print(obj.seat())
    # print(obj.leave(9))
    # print(obj.seat())
    # print(obj.leave(0))
    # print(obj.seat())