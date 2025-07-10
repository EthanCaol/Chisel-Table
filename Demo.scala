import chisel3._
import circt.stage.ChiselStage
import java.io.{File, PrintWriter}

class Demo extends Module {
    val io = IO(new Bundle {
        val a = Input(Bool())
        val b = Input(Bool())
        val out = Output(Bool())
    })

    io.out := io.a & io.b
}

object Main extends App {
    val verilog = ChiselStage.emitSystemVerilog(
      new Demo,
      firtoolOpts = Array(
        "-disable-all-randomization",
        "--strip-debug-info",
      )
    )

    val outputFile = new File("Demo.sv")
    val writer = new PrintWriter(outputFile)
    writer.write(verilog)
    writer.close()
}
