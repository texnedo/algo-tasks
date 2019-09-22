#include <iostream>
#include "FindTheDuplicateNumber.hpp"
#include "ShortestUnsortedContinuousSubarray.hpp"

int main(int argc, const char * argv[]) {
//    FindTheDuplicateNumber number;
//    vector<int> data = {1, 3, 4, 2, 2};
//    std::cout << number.findDuplicate(data) << std::endl;
    ShortestUnsortedContinuousSubarray cont;
//    vector<int> data = {2, 6, 4, 8, 10, 9, 15};
//    std::cout << cont.findUnsortedSubarray(data) << std::endl;
//    vector<int> data2 = {2, 4, 6, 8, 10};
//    std::cout << cont.findUnsortedSubarray(data2) << std::endl;
    vector<int> data3 = {4, 2, 6, 8, 10};
    std::cout << cont.findUnsortedSubarray(data3) << std::endl;
    vector<int> data4 = {2,3,3,2,4};
    std::cout << cont.findUnsortedSubarray(data4) << std::endl;
    vector<int> data5 = {2,6,4,8,10,9,15};
    std::cout << cont.findUnsortedSubarray(data5) << std::endl;
    
    /**
     s: 2 - 6
     s: 2 - 4 left = 1
     s: 2 - 4 - 8 - 10 left = 1
     s: 2 - 4 - 8 - 9 left = 1
     s: 2 - 4 - 8 - 9 - 15 left = 1
     
     
     s: 15 - 9
     s: 15 - 10 right = 5
     */
    
    
    return 0;
}
