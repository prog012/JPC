package org.jpc.emulator.execution.opcodes.rm;

import org.jpc.emulator.execution.*;
import org.jpc.emulator.execution.decoder.*;
import org.jpc.emulator.processor.*;
import static org.jpc.emulator.processor.Processor.*;

public class mov_rSIr14_Iv extends Executable
{
    final int immv;
    final int size;

    public mov_rSIr14_Iv(int blockStart, Instruction parent)
    {
        super(blockStart, parent);
        size = parent.opr_mode;
        immv = (int)parent.operand[1].lval;
    }

    public Branch execute(Processor cpu)
    {
        if (size == 16)
        {
        cpu.r_esi.set16((short)immv);
        }
        else if (size == 32)
        {
        cpu.r_esi.set32(immv);
        }        else throw new IllegalStateException("Unknown size "+size);
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