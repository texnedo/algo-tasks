//
//  FindTheDuplicateNumber.hpp
//  algo-cpp
//
//  Created by v.nedashkovskiy on 9/1/19.
//  Copyright © 2019 v.nedashkovskiy. All rights reserved.
//

#ifndef FindTheDuplicateNumber_hpp
#define FindTheDuplicateNumber_hpp

#include <stdio.h>
#include <vector>
#include <iostream>

using namespace std;

class FindTheDuplicateNumber {
public:
    int findDuplicate(vector<int>& nums) {
        std::sort(nums.begin(), nums.end());
        int previous = INT_MIN;
        for(auto it = nums.begin(); it != nums.end(); it++) {
            int value = *it;
            if (it != nums.begin()) {
                if (value == previous) {
                    return value;
                }
            }
            previous = value;
        }
        return INT_MIN;
    }
};
#endif /* FindTheDuplicateNumber_hpp */
