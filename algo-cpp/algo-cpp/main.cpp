#include <iostream>
#include "FindTheDuplicateNumber.hpp"
#include "ShortestUnsortedContinuousSubarray.hpp"

int main(int argc, const char * argv[]) {
//    FindTheDuplicateNumber number;
//    vector<int> data = {1, 3, 4, 2, 2};
//    std::cout << number.findDuplicate(data) << std::endl;
    ShortestUnsortedContinuousSubarray cont;
    vector<int> data = {2, 6, 4, 8, 10, 9, 15};
    std::cout << cont.findUnsortedSubarray(data) << std::endl;
    
    return 0;
}
