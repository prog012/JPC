package org.jpc.emulator.execution.opcodes.vm;

import org.jpc.emulator.execution.*;
import org.jpc.emulator.execution.decoder.*;
import org.jpc.emulator.processor.*;
import org.jpc.emulator.processor.fpu64.*;
import static org.jpc.emulator.processor.Processor.*;

public class shl_Eb_I1 extends Executable
{
    final int op1Index;

    public shl_Eb_I1(int blockStart, int eip, int prefices, PeekableInputStream input)
    {
        super(blockStart, eip);
        int modrm = input.readU8();
        op1Index = Modrm.Eb(modrm);
    }

    public Branch execute(Processor cpu)
    {
        Reg op1 = cpu.regs[op1Index];
        int shift = 1 & 0x1f;
        if(shift != 0)
        {
            if (shift != 1)
            {
                cpu.of(cpu.of());
                cpu.flagStatus = SZAPC;
            }
            else
                cpu.flagStatus = OSZAPC;
            cpu.flagOp1 = op1.get8();
            cpu.flagOp2 = shift;
            cpu.flagResult = (byte)(cpu.flagOp1 << cpu.flagOp2);
            op1.set8((byte)cpu.flagResult);
            cpu.flagIns = UCodes.SHL8;
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