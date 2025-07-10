#!/bin/bash
sbt run
verilator -Wall --cc AndGate.sv --exe AndGate_tb.cpp
cd obj_dir
make -j -f VAndGate.mk VAndGate
./VAndGate