import chisel3._
import circt.stage.ChiselStage
import java.io.{File, PrintWriter}

class And extends Module {
    val io = IO(new Bundle {
        val a = Input(Bool())
        val b = Input(Bool())
        val out = Output(Bool())
    })

    io.out := io.a & io.b
}

object And extends App {
    val verilog = ChiselStage.emitSystemVerilog(
        new And,
        firtoolOpts = Array(
            "-disable-all-randomization",
            "--strip-debug-info"
        )
    )

    val outputFile = new File("And.sv")
    val writer = new PrintWriter(outputFile)
    writer.write(verilog)
    writer.close()
}
