#include "VEncode42.h"
#include "verilated.h"
#include "verilated_vcd_c.h"
#include <iostream>

int main(int argc, char** argv)
{
    Verilated::commandArgs(argc, argv);
    Verilated::traceEverOn(true);

    VEncode42* top = new VEncode42;
    VerilatedVcdC* vcd = new VerilatedVcdC;

    top->trace(vcd, 99);
    vcd->open("Encode42.vcd");

    top->io_en = 0;
    top->io_in = 0;
    top->eval();
    vcd->dump(0);

    struct TestStage {
        uint8_t en;
        uint8_t in;
        uint64_t duration;
    } stages[] = {
        { 0, 0b0000, 10 },
        { 1, 0b0001, 10 },
        { 1, 0b0010, 10 },
        { 1, 0b0100, 10 },
        { 1, 0b1000, 10 },
        { 1, 0b1111, 10 },
        { 0, 0b0000, 10 },
    };

    uint64_t time = 10; 
    for (const auto& stage : stages) {
        top->io_en = stage.en;
        top->io_in = stage.in;

        for (uint64_t t = 0; t < stage.duration; t++) {
            top->eval();
            vcd->dump(time++);
        }
    }

    vcd->close();
    delete top;
    delete vcd;
    return 0;
}