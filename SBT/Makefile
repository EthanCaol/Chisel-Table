.PHONY: all run view clean

all: run view

run:
	sbt run
	verilator -Wall --cc And.sv --exe And.cpp --Mdir bin --trace
	cd bin && make -s -j -f VAnd.mk VAnd && ./VAnd

view:
	gtkwave bin/And.vcd

clean:
	rm -rf bin And.sv