//> using scala "2.13.16"
//> using dep "org.chipsalliance::chisel:6.7.0"
//> using plugin "org.chipsalliance:::chisel-plugin:6.7.0"
//> using options "-feature" "-deprecation" "-language:reflectiveCalls"

import circt.stage.ChiselStage

import chisel3._
import chisel3.util._
import java.io.{File, PrintWriter}

object Main extends App {
    class Mux41 extends RawModule {
        val io = IO(new Bundle {
            val data = Input(UInt(4.W))
            val key = Input(UInt(2.W))
            val out = Output(UInt(1.W))
        })

        io.out := MuxLookup(io.key, 0.U)(
            Seq(
                0.U -> io.data(0),
                1.U -> io.data(1),
                2.U -> io.data(2),
                3.U -> io.data(3)
            )
        )
    }

    val verilog = ChiselStage.emitSystemVerilog(
        new Mux41,
        firtoolOpts = Array(
            "-disable-all-randomization",
            "--strip-debug-info"
        )
    )

    val writer = new PrintWriter(new File("Mux41.sv"))
    writer.write(verilog)
    writer.close()
}
