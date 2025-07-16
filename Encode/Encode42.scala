//> using scala "2.13.16"
//> using dep "org.chipsalliance::chisel:6.7.0"
//> using plugin "org.chipsalliance:::chisel-plugin:6.7.0"
//> using options "-feature" "-deprecation" "-language:reflectiveCalls"

import circt.stage.ChiselStage

import chisel3._
import chisel3.util._
import java.io.{File, PrintWriter}

object Main extends App {
    class Encode42 extends RawModule {
        val io = IO(new Bundle {
            val en = Input(Bool())
            val in = Input(UInt(4.W))
            val out = Output(UInt(2.W))
        })

        io.out := Mux(
            io.en,
            MuxLookup(io.in, 0.U)(
                Seq(
                    0x1.U -> 0.U,
                    0x2.U -> 1.U,
                    0x4.U -> 2.U,
                    0x8.U -> 3.U
                )
            ),
            0.U
        )
    }

    val verilog = ChiselStage.emitSystemVerilog(
        new Encode42,
        firtoolOpts = Array(
            "-disable-all-randomization",
            "--strip-debug-info"
        )
    )

    val writer = new PrintWriter(new File("Encode42.sv"))
    writer.write(verilog)
    writer.close()
}
