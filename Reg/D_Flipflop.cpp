#include "VD_Flipflop.h"
#include "verilated.h"
#include "verilated_vcd_c.h"

int main(int argc, char** argv)
{
    Verilated::commandArgs(argc, argv);
    Verilated::traceEverOn(true);

    VD_Flipflop* top = new VD_Flipflop;
    VerilatedVcdC* vcd = new VerilatedVcdC;
    top->trace(vcd, 99);
    vcd->open("D_Flipflop.vcd");

    top->clock = 0;
    top->reset = 1;
    top->io_D = 0;

    for (int cycle = 0; cycle < 10; cycle++) {
        top->clock = !top->clock;
        if (cycle == 1)
            top->reset = 0;
        if (cycle == 3)
            top->io_D = 1;
        if (cycle == 5)
            top->io_D = 0;
        if (cycle == 7) {
            top->reset = 1;
            top->io_D = 1;
        }

        top->eval();
        vcd->dump(cycle * 10);
    }

    vcd->close();
    delete top;
    return 0;
}