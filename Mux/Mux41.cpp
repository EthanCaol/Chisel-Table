#include "bin/VMux41.h"
#include "verilated.h"
#include "verilated_vcd_c.h"
#include <iostream>

int main(int argc, char** argv)
{
    Verilated::commandArgs(argc, argv);
    Verilated::traceEverOn(true);

    VMux41* top = new VMux41;
    VerilatedVcdC* vcd = new VerilatedVcdC;

    top->trace(vcd, 99);
    vcd->open("Mux41.vcd");

    uint64_t time = 0;
    top->io_data = 0;
    top->io_key = 0;
    top->eval();

    struct TestCase {
        uint8_t data; // 4
        uint8_t key;  // 2
        int delay;
    };

    TestCase test_cases[] = {
        { 0b0000, 0b00, 10 },
        { 0b0001, 0b00, 10 },
        { 0b0010, 0b01, 10 },
        { 0b0100, 0b10, 10 },
        { 0b1000, 0b11, 10 },
        { 0b0111, 0b11, 10 },
    };

    for (const auto& test : test_cases) {
        top->io_data = test.data;
        top->io_key = test.key;

        for (int i = 0; i < test.delay; i++) {
            top->eval();
            vcd->dump(time++);
        }
    }

    vcd->close();
}