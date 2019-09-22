//
//  ShortestUnsortedContinuousSubarray.hpp
//  algo-cpp
//
//  Created by v.nedashkovskiy on 9/2/19.
//  Copyright © 2019 v.nedashkovskiy. All rights reserved.
//

#ifndef ShortestUnsortedContinuousSubarray_hpp
#define ShortestUnsortedContinuousSubarray_hpp

#include <vector>
#include <stack>
#include <iostream>
#include <stdio.h>

using namespace std;


class ShortestUnsortedContinuousSubarray {
public:
    int findUnsortedSubarray(vector<int>& nums) {
        vector<int> sorted(nums);
        sort(sorted.begin(), sorted.end());
        int rightIndex = 0;
        int maxIndex = (int)(nums.size() - 1);
        int leftIndex = maxIndex;
        for (int i = 0; i < sorted.size(); i++) {
            if (sorted.at(i) == nums.at(i)) {
                rightIndex++;
            } else {
                break;
            }
        }
        for (int i = maxIndex; i > rightIndex; i--) {
            if (sorted.at(i) == nums.at(i)) {
                leftIndex--;
            } else {
                break;
            }
        }
        if (leftIndex == rightIndex) {
            return 0;
        }
        return leftIndex - rightIndex + 1;
    }
    
    int findUnsortedSubarray3(vector<int>& nums) {
        stack<int> indexes;
        int rightIndex = 0;
        int leftIndex = (int)(nums.size() - 1);
        for (int i = 0; i < nums.size(); i++) {
            int current = nums.at(i);
            if (indexes.empty()) {
                indexes.push(i);
            } else {
                while (true) {
                    indexes.peek();
                }
            }
        }
    }
    
    int findUnsortedSubarray2(vector<int>& nums) {
        int max = INT_MIN;
        int min = INT_MAX;
        for (int i = 0; i < nums.size(); i++) {
            int current = nums.at(i);
            if (min > current) {
                min = current;
            }
            if (max < current) {
                max = current;
            }
        }
        int i = 0;
        int j = (int)(nums.size() - 1);
        int left = i;
        int right = j;
        while (i <= j) {
            if (nums.at(i) >= min) {
                left = i;
            }
            if (nums.at(j) <= max) {
                right = j;
            }
            i++;
            j--;
        }
        return right - left + 1;
    }
};
#endif /* ShortestUnsortedContinuousSubarray_hpp */
