package org.jpc.emulator.execution.opcodes.pm;

import org.jpc.emulator.execution.*;
import org.jpc.emulator.execution.decoder.*;
import org.jpc.emulator.processor.*;
import org.jpc.emulator.processor.fpu64.*;
import static org.jpc.emulator.processor.Processor.*;

public class xadd_Eb_Gb_mem extends Executable
{
    final Pointer op1;
    final int op2Index;

    public xadd_Eb_Gb_mem(int blockStart, int eip, int prefices, PeekableInputStream input)
    {
        super(blockStart, eip);
        int modrm = input.readU8();
        op1 = Modrm.getPointer(prefices, modrm, input);
        op2Index = Modrm.Gb(modrm);
    }

    public Branch execute(Processor cpu)
    {
        Reg op2 = cpu.regs[op2Index];
            int tmp1 = op1.get8(cpu);
        int tmp2 = op2.get8();
        cpu.flagOp1 = tmp1;
        cpu.flagOp2 = tmp2;
        cpu.flagResult = (byte)(cpu.flagOp1 + cpu.flagOp2);
        op1.set8(cpu, (byte)cpu.flagResult);
        cpu.flagIns = UCodes.ADD8;
        cpu.flagStatus = OSZAPC;
        op2.set8((byte) tmp1);
        op1.set8(cpu, (byte) (tmp1+tmp2));
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