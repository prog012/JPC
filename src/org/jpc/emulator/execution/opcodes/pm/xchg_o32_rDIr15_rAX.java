package org.jpc.emulator.execution.opcodes.pm;

import org.jpc.emulator.execution.*;
import org.jpc.emulator.execution.decoder.*;
import org.jpc.emulator.processor.*;
import static org.jpc.emulator.processor.Processor.*;

public class xchg_o32_rDIr15_rAX extends Executable
{

    public xchg_o32_rDIr15_rAX(int blockStart, Instruction parent)
    {
        super(blockStart, parent);
    }

    public Branch execute(Processor cpu)
    {
            int tmp = cpu.r_eax.get32();
        cpu.r_eax.set32(cpu.r_edi.get32());
        cpu.r_edi.set32(tmp);
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