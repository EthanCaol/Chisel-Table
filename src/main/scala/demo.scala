import chisel3._
import circt.stage.ChiselStage
import java.io.{File, PrintWriter}

class Demo extends Module {
    val io = IO(new Bundle {
        val enable = Input(Bool())
        val out = Output(Bool())
    })

    val flip = RegInit(false.B)

    when(io.enable) {
        flip := !flip
    }.otherwise {
        flip := false.B
    }

    io.out := flip
}

object Main extends App {
    val verilog = ChiselStage.emitSystemVerilog(
      new Demo,
      firtoolOpts = Array("-disable-all-randomization", "--strip-debug-info")
    )

    val outputFile = new File("demo.sv")
    val writer = new PrintWriter(outputFile)
    writer.write(verilog)
    writer.close()

    println(s"SystemVerilog 已保存到 ${outputFile.getAbsolutePath}")
}
