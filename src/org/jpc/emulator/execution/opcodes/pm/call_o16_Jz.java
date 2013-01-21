package org.jpc.emulator.execution.opcodes.pm;

import org.jpc.emulator.execution.*;
import org.jpc.emulator.execution.decoder.*;
import org.jpc.emulator.processor.*;
import static org.jpc.emulator.processor.Processor.*;

public class call_o16_Jz extends Executable
{
    final int jmp;
    final int blockLength;
    final int instructionLength;

    public call_o16_Jz(int blockStart, Instruction parent)
    {
        super(blockStart, parent);
        blockLength = parent.x86Length+(int)parent.eip-blockStart;
        instructionLength = parent.x86Length;
        jmp = (short)parent.operand[0].lval;
    }

    public Branch execute(Processor cpu)
    {
                if ((cpu.r_sp.get16() < 2) && (cpu.r_sp.get16() > 0))
	    throw ProcessorException.STACK_SEGMENT_0;
        cpu.eip += blockLength;
        cpu.push16((short)cpu.eip);
        cpu.eip += jmp;
        cpu.eip &= 0xffff;
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