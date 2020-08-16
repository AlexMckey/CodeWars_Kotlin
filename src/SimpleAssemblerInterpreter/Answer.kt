package SimpleAssemblerInterpreter

fun interpret(program: Array<String>): Map<String, Int> {
    val cpu = SimpleCPU()
    cpu.loadProgram(program)
    if (cpu.runProgram() != Status.Stop) throw Error("cpu not work properly")
    return cpu.regs
}