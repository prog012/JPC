package org.jpc.emulator.execution.opcodes.rm;

import org.jpc.emulator.execution.*;
import org.jpc.emulator.execution.decoder.*;
import org.jpc.emulator.processor.*;
import static org.jpc.emulator.processor.Processor.*;

public class mov_Eb_Ib_mem extends Executable
{
    final Pointer op1;
    final int imm;

    public mov_Eb_Ib_mem(int blockStart, Instruction parent)
    {
        super(blockStart, parent);
        op1 = new Pointer(parent.operand[0]);
        imm = (byte)parent.operand[1].lval;
    }

    public Branch execute(Processor cpu)
    {
        op1.set8(cpu, (byte)imm);
        return Branch.None;
    }

    public boolean isBranch()
    {
        return false;
    }

    public String toString()
    {
        return this.getClass().getName();
    }
}