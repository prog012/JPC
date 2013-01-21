package org.jpc.emulator.execution.opcodes.pm;

import org.jpc.emulator.execution.*;
import org.jpc.emulator.execution.decoder.*;
import org.jpc.emulator.processor.*;
import static org.jpc.emulator.processor.Processor.*;

public class rol_Ev_I1 extends Executable
{
    final int op1Index;
    final int size;

    public rol_Ev_I1(int blockStart, Instruction parent)
    {
        super(blockStart, parent);
        size = parent.opr_mode;
        op1Index = Processor.getRegIndex(parent.operand[0].toString());
    }

    public Branch execute(Processor cpu)
    {
        Reg op1 = cpu.regs[op1Index];
        if (size == 16)
        {
            int shift = 1 & (16-1);
            int reg0 = 0xFFFF&op1.get16();
            int res = (reg0 << shift) | (reg0 >>> (16 - shift));
            op1.set16((short)res);
            boolean bit0  = (res & 1 ) != 0;
            boolean bit31 = (res & (1 << (16-1))) != 0;
            if (1 > 0)
            {
                cpu.cf = bit0;
                if (1 == 1)
                {
                    cpu.of = bit0 ^ bit31;
                    cpu.flagStatus &= NOFCF;
                }
                else
                    cpu.flagStatus &= NCF;
            }
        }
        else if (size == 32)
        {
            int shift = 1 & (32-1);
            int reg0 = op1.get32();
            int res = (reg0 << shift) | (reg0 >>> (32 - shift));
            op1.set32(res);
            boolean bit0  = (res & 1 ) != 0;
            boolean bit31 = (res & (1 << (32-1))) != 0;
            if (1 > 0)
            {
                cpu.cf = bit0;
                if (1 == 1)
                {
                    cpu.of = bit0 ^ bit31;
                    cpu.flagStatus &= NOFCF;
                }
                else
                    cpu.flagStatus &= NCF;
            }
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