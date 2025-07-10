#include "VAndGate.h"
#include "verilated.h"
#include <iostream>

int main(int argc, char** argv) {
    Verilated::commandArgs(argc, argv);
    VAndGate* top = new VAndGate;
    
    // 测试所有输入组合
    for (int a_val = 0; a_val < 2; a_val++) {
        for (int b_val = 0; b_val < 2; b_val++) {
            top->io_a = a_val;  // 注意这里改为 io_a
            top->io_b = b_val;  // 注意这里改为 io_b
            top->eval();
            
            std::cout << "a = " << a_val 
                      << ", b = " << b_val
                      << ", out = " << (top->io_out ? "1" : "0")
                      << std::endl;
        }
    }
    
    delete top;
    return 0;
}