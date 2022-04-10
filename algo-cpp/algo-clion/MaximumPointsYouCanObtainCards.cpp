#include <iostream>
#include <vector>
#include <map>
#include <math.h>
using namespace std;

class Solution {
public:
    int maxScoreSlow(vector<int>& cardPoints, int k) {
        return maxScoreInternalSlow(cardPoints, k, 0, cardPoints.size() - 1);
    }

    int maxScore(vector<int>& cardPoints, int k) {
        int windowSum = 0;
        int lastLeftIndex = k - 1;
        for (u_long i = 0; i < k; i++) {
            windowSum += cardPoints[i];
        }
        if (k == cardPoints.size()) {
            return windowSum;
        }
        int maxSum = windowSum;
        for (u_long i = cardPoints.size() - 1; i >= cardPoints.size() - k; i--) {
            windowSum += cardPoints[i];
            windowSum -= cardPoints[lastLeftIndex];
            lastLeftIndex--;
            if (windowSum > maxSum) {
                maxSum = windowSum;
            }
        }
        return maxSum;
    }

private:
    int maxScoreInternalSlow(vector<int>& cardPoints, int k, u_long start, u_long end) {
        cout << k << " " << start << " " << end << endl;
        if (k == 0 || start > end) {
            return 0;
        }
        if (start == end) {
            return cardPoints[start];
        }
        int takeLeft = cardPoints[start] +
                maxScoreInternalSlow(cardPoints, k - 1, start + 1, end);
        int takeRight = cardPoints[end] +
                maxScoreInternalSlow(cardPoints, k - 1, start, end - 1);
        return max(takeLeft, takeRight);
    }
};

//int main() {
//    Solution s;
//    vector<int> cards({1,2,3,4,5,6,1});
//    cout << s.maxScoreSlow(cards, 3) << endl;
//    cout << s.maxScore(cards, 3) << endl;
//    vector<int> cards2({9,7,7,9,7,7,9});
//    cout << s.maxScore(cards2, 7) << endl;
//}