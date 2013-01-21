package org.jpc.emulator.execution.opcodes.pm;

import org.jpc.emulator.execution.*;
import org.jpc.emulator.execution.decoder.*;
import org.jpc.emulator.processor.*;
import static org.jpc.emulator.processor.Processor.*;

public class sbb_Ev_Iz extends Executable
{
    final int op1Index;
    final int immz;
    final int size;

    public sbb_Ev_Iz(int blockStart, Instruction parent)
    {
        super(blockStart, parent);
        size = parent.opr_mode;
        op1Index = Processor.getRegIndex(parent.operand[0].toString());
        immz = (int)parent.operand[1].lval;
    }

    public Branch execute(Processor cpu)
    {
        Reg op1 = cpu.regs[op1Index];
        if (size == 16)
        {
        int add = (cpu.cf()? 1: 0);
        cpu.flagOp1 = op1.get16();
        cpu.flagOp2 = immz;
        cpu.flagResult = (short)(cpu.flagOp1 - (cpu.flagOp2 + add));
        op1.set16((short)cpu.flagResult);
        cpu.flagIns = UCodes.SBB16;
        cpu.flagStatus = OSZAPC;
        }
        else if (size == 32)
        {
        int add = (cpu.cf()? 1: 0);
        cpu.flagOp1 = op1.get32();
        cpu.flagOp2 = immz;
        cpu.flagResult = (cpu.flagOp1 - (cpu.flagOp2 + add));
        op1.set32(cpu.flagResult);
        cpu.flagIns = UCodes.SBB32;
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