package org.jpc.emulator.execution.opcodes.rm;

import org.jpc.emulator.execution.*;
import org.jpc.emulator.execution.decoder.*;
import org.jpc.emulator.processor.*;
import static org.jpc.emulator.processor.Processor.*;

public class cmp_o32_rAX_Iz extends Executable
{
    final int immz;
    final int size;

    public cmp_o32_rAX_Iz(int blockStart, Instruction parent)
    {
        super(blockStart, parent);
        size = parent.opr_mode;
        immz = (int)parent.operand[1].lval;
    }

    public Branch execute(Processor cpu)
    {
        if (size == 16)
        {
        cpu.flagOp1 = cpu.r_eax.get32();
        cpu.flagOp2 = immz;
        cpu.flagResult = (short)(cpu.flagOp1 - cpu.flagOp2);
        cpu.flagIns = UCodes.SUB16;
        cpu.flagStatus = OSZAPC;
        }
        else if (size == 32)
        {
        cpu.flagOp1 = cpu.r_eax.get32();
        cpu.flagOp2 = immz;
        cpu.flagResult = (cpu.flagOp1 - cpu.flagOp2);
        cpu.flagIns = UCodes.SUB32;
        cpu.flagStatus = OSZAPC;
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