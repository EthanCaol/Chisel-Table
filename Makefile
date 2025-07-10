.PHONY: all run view clean

all: run view

run:
	sbt run
	verilator -Wall --cc Demo.sv --exe Demo.cpp --trace -Wno-UNUSEDSIGNAL
	cd obj_dir && make -j -f VDemo.mk VDemo && ./VDemo

view:
	gtkwave obj_dir/Demo.vcd

clean:
	rm -rf obj_dir Demo.sv