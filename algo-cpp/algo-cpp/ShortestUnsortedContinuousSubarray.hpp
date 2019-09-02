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
#include <iostream>
#include <stdio.h>

using namespace std;


class ShortestUnsortedContinuousSubarray {
public:
    int findUnsortedSubarray(vector<int>& nums) {
        for (int i = 1; i < nums.size(); i++) {
            if (nums.at(i) < nums.at(i - 1))
        }
        return 0;
    }
};
#endif /* ShortestUnsortedContinuousSubarray_hpp */
