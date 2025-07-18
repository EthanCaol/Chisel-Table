//> using scala "2.13.16"
//> using dep "org.chipsalliance::chisel:6.7.0"
//> using plugin "org.chipsalliance:::chisel-plugin:6.7.0"
//> using options "-feature" "-deprecation" "-language:reflectiveCalls"

import circt.stage.ChiselStage

import chisel3._
import chisel3.util._
import java.io.{File, PrintWriter}

object Main extends App {
    class And extends RawModule {
        val io = IO(new Bundle {
            val a = Input(Bool())
            val b = Input(Bool())
            val out = Output(Bool())
        })
        io.out := io.a & io.b
    }

    val verilog = ChiselStage.emitSystemVerilog(
        new And,
        firtoolOpts = Array(
            "-disable-all-randomization",
            "--strip-debug-info"
        )
    )

    val writer = new PrintWriter(new File("And.sv"))
    writer.write(verilog)
    writer.close()
}
