package org.jpc.emulator.execution.opcodes.pm;

import org.jpc.emulator.execution.*;
import org.jpc.emulator.execution.decoder.*;
import org.jpc.emulator.processor.*;
import static org.jpc.emulator.processor.Processor.*;

public class shr_Eb_I1_mem extends Executable
{
    final Pointer op1;

    public shr_Eb_I1_mem(int blockStart, Instruction parent)
    {
        super(blockStart, parent);
        op1 = new Pointer(parent.operand[0], parent.adr_mode);
    }

    public Branch execute(Processor cpu)
    {
        if(1 != 0)
        {
            cpu.flagOp1 = 0xFF&op1.get8(cpu);
            cpu.flagOp2 = 1;
            cpu.flagResult = (byte)(cpu.flagOp1 >>> cpu.flagOp2);
            op1.set8(cpu, (byte)cpu.flagResult);
            cpu.flagIns = UCodes.SHR8;
            cpu.flagStatus = OSZAPC;
        }
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