//> using scala "2.13.16"
//> using dep "org.chipsalliance::chisel:6.7.0"
//> using plugin "org.chipsalliance:::chisel-plugin:6.7.0"
//> using options "-feature" "-deprecation" "-language:reflectiveCalls"

import circt.stage.ChiselStage

import chisel3._
import chisel3.util._
import java.io.{File, PrintWriter}

object Main extends App {
    class D_Flipflop extends Module {
        val io = IO(new Bundle {
            val D = Input(Bool())
            val Q = Output(Bool())
        })
        io.Q := RegNext(io.D, false.B)
    }

    val verilog = ChiselStage.emitSystemVerilog(
        new D_Flipflop,
        firtoolOpts = Array(
            "-disable-all-randomization",
            "--strip-debug-info"
        )
    )

    val writer = new PrintWriter(new File("D_Flipflop.sv"))
    writer.write(verilog)
    writer.close()
}
