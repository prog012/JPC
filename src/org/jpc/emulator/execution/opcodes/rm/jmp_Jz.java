package org.jpc.emulator.execution.opcodes.rm;

import org.jpc.emulator.execution.*;
import org.jpc.emulator.execution.decoder.*;
import org.jpc.emulator.processor.*;
import static org.jpc.emulator.processor.Processor.*;

public class jmp_Jz extends Executable
{
    final int jmp;
    final int blockLength;
    final int instructionLength;

    public jmp_Jz(int blockStart, Instruction parent)
    {
        super(blockStart, parent);
        blockLength = parent.x86Length+(int)parent.eip-blockStart;
        instructionLength = parent.x86Length;
        jmp = (int)parent.operand[0].lval;
    }

    public Branch execute(Processor cpu)
    {
        cpu.eip += jmp+blockLength;
        cpu.eip &= 0xFFFF;
        return Branch.T1;
    }

    public boolean isBranch()
    {
        return true;
    }

    public String toString()
    {
        return this.getClass().getName();
    }
}