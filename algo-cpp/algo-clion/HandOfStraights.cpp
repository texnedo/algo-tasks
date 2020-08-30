#include <iostream>
#include <vector>
#include <map>
using namespace std;

void printMultiMap(std::multimap<int, int>& buckets);
void testMultiMap();

class Solution {
public:
    bool isNStraightHand(std::vector<int>& hand, int W) {
        std::sort(hand.begin(), hand.end());
        std::multimap<int, int> buckets;
        if (W <= 0) {
            return false;
        }
        if (hand.size() % W != 0) {
            return false;
        }
        auto bucketCount = hand.size() / W;
        for (int i = 0; i < bucketCount; i++) {
            buckets.insert(std::make_pair(-1, 0));
        }
        for (int i = 0; i < hand.size(); i++) {
            auto value = hand[i];
            auto previous = value - 1;
            auto it = buckets.find(previous);
            if (it == buckets.end()) {
                it = buckets.find(-1);
                if (it == buckets.end()) {
                    return false;
                }
            }
            std::cout << "item: " << value << " found bucket: "
                        << it->first << " count: " << it->second << std::endl;
            auto count = it->second + 1;
            buckets.erase(it);
            if (count < W) {
                buckets.insert(std::make_pair(value, count));
            }
            printMultiMap(buckets);
        }
        return buckets.empty();
    }
};

void printMultiMap(std::multimap<int, int>& buckets) {
    std::cout << "___________" << std::endl;
    for (auto it = buckets.begin(); it != buckets.end(); it++) {
        std::cout << "bucket: " << it->first << " count: " << it->second << std::endl;
    }
    std::cout << "___________" << std::endl;
}

void testMultiMap() {
    std::multimap<int, int> buckets;
    buckets.insert(std::make_pair(0, 1));
    buckets.insert(std::make_pair(1, 1));
    buckets.insert(std::make_pair(2, 2));
    buckets.insert(std::make_pair(2, 1));
    buckets.insert(std::make_pair(3, 1));
    for (auto it = buckets.find(2); it != buckets.end(); it++) {
        if (it->first > 2) {
            break;
        }
        std::cout << "bucket: " << it->first << " count: " << it->second << std::endl;
    }
    std::cout << std::endl;
    auto it = buckets.find(2);
    it->second = 3;
    for (auto it = buckets.find(2); it != buckets.end(); it++) {
        if (it->first > 2) {
            break;
        }
        std::cout << "bucket: " << it->first << " count: " << it->second << std::endl;
    }
    std::cout << std::endl;
    buckets.erase(it);
    for (auto it = buckets.find(2); it != buckets.end(); it++) {
        if (it->first > 2) {
            break;
        }
        std::cout << "bucket: " << it->first << " count: " << it->second << std::endl;
    }
    std::cout << std::endl;
}

int main() {
    Solution s;

    std::vector<int> data2 = {0, 0};
    std::cout << "result: " << s.isNStraightHand(data2, 2) << std::endl;

    std::vector<int> data0 = {1,2,3,4,5,6};
    std::cout << "result: " << s.isNStraightHand(data0, 2) << std::endl;

    std::vector<int> data = {1,2,3,6,2,3,4,7,8};
    std::cout << "result: " << s.isNStraightHand(data, 3) << std::endl;

    std::vector<int> data1 = {1,2,3,4,5};
    std::cout << "result: " << s.isNStraightHand(data1, 4) << std::endl;
    return 0;
}
