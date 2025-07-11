#include "bin/VAnd.h"
#include "verilated.h"
#include "verilated_vcd_c.h"
#include <iostream>

int main(int argc, char** argv)
{
    Verilated::commandArgs(argc, argv);
    Verilated::traceEverOn(true);

    VAnd* top = new VAnd;
    VerilatedVcdC* vcd = new VerilatedVcdC;

    top->trace(vcd, 99);
    vcd->open("And.vcd");

    int time = 0;
    top->io_a = 0;
    top->io_b = 0;
    top->eval();
    vcd->dump(time++);

    top->io_a = 0;
    top->io_b = 1;
    top->eval();
    vcd->dump(time++);
    
    top->io_a = 1;
    top->io_b = 0;
    top->eval();
    vcd->dump(time++);

    top->io_a = 1;
    top->io_b = 1;
    top->eval();
    vcd->dump(time++);

    vcd->dump(time++);
    vcd->close();
}