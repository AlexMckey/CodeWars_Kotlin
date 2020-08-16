package SimpleAssemblerInterpreter

data class Op(val type: String, val par1: String, val par2: String)

enum class Status{ Ok, Fail, Stop }

class SimpleCPU {
    private var ip: Int = 0
    var regs: MutableMap<String,Int> = mutableMapOf()
        private set
    private var status: Status = Status.Ok

    private var program: Array<Op> = emptyArray()

    fun loadProgram(ap: Array<String>){
        program = ap.map { it.split(" ")}
            .map {if (it.size > 2) Op(it[0],it[1],it[2])
                  else Op(it[0],it[1],"")}.toTypedArray()
    }

    private fun doStep() {
        if (ip >= program.size) { status = Status.Stop; return }
        val op = program[ip]
        var deltaIP = 1
        when (op.type) {
            "mov" -> regs[op.par1] = op.par2.toIntOrNull() ?: regs[op.par2]!!
            "inc" -> regs[op.par1] = regs[op.par1]!! + 1
            "dec" -> regs[op.par1] = regs[op.par1]!! - 1
            "jnz" -> if (op.par1.toIntOrNull() ?: regs[op.par1]!! != 0)
                deltaIP = op.par2.toIntOrNull() ?: 0
            else -> status = Status.Fail
        }
        ip += deltaIP
    }

    fun runProgram(): Status {
        if (program.size == 0) status = Status.Stop
        else {
            ip = 0
            status = Status.Ok
            while (status == Status.Ok) {
                doStep()
            }
        }
        return status
    }
}