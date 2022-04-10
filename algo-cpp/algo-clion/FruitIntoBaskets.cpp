#include <iostream>
#include <vector>
#include <unordered_map>
#include <map>
using namespace std;

class Solution {
public:
    int totalFruit(vector<int>& fruits) {
        unordered_map<int, int> windowTypes;
        int fruitCount = 0;
        int maxFruitCount = 0;
        for (int i = 0; i < fruits.size(); i++) {
            if (windowTypes.find(fruits[i]) == windowTypes.end()) {
                if (windowTypes.size() == 2) {
                    if (fruitCount > maxFruitCount) {
                        maxFruitCount = fruitCount;
                    }
                    int minIndex = fruits.size();
                    auto minIndexIt = windowTypes.end();
                    for (auto it = windowTypes.begin(); it != windowTypes.end(); it++) {
                        if (it->second < minIndex) {
                            minIndex = it->second;
                            minIndexIt = it;
                        }
                    }
                    windowTypes.erase(minIndexIt);
                    minIndex = fruits.size();
                    for (auto it = windowTypes.begin(); it != windowTypes.end(); it++) {
                        if (it->second < minIndex) {
                            minIndex = it->second;
                        }
                    }
                    fruitCount = i - minIndex + 1;
                    windowTypes[fruits[i]] = i;
                } else {
                    windowTypes[fruits[i]] = i;
                    fruitCount++;
                }
            } else {
                if (fruits[i - 1] != fruits[i]) {
                    windowTypes[fruits[i]] = i;
                }
                fruitCount++;
            }
        }
        if (fruitCount > maxFruitCount) {
            maxFruitCount = fruitCount;
        }
        return maxFruitCount;
    }
};

int main() {
    Solution s;
    vector<int> data({1,1,1,2,2,3,3,3,3,2,2});
    cout << s.totalFruit(data);
}