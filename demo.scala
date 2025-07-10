import chisel3._
import circt.stage.ChiselStage
import java.io.{File, PrintWriter}

class AndGate extends Module {
    val io = IO(new Bundle {
        val a = Input(Bool())
        val b = Input(Bool())
        val out = Output(Bool())
    })

    // 简单的与门实现
    io.out := io.a & io.b
}

object Main extends App {
    val verilog = ChiselStage.emitSystemVerilog(
      new AndGate,
      firtoolOpts = Array("-disable-all-randomization", "--strip-debug-info")
    )

    val outputFile = new File("AndGate.sv")
    val writer = new PrintWriter(outputFile)
    writer.write(verilog)
    writer.close()

    println(s"SystemVerilog 已保存到 ${outputFile.getAbsolutePath}")
}
