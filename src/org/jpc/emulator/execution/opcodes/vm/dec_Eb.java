package org.jpc.emulator.execution.opcodes.vm;

import org.jpc.emulator.execution.*;
import org.jpc.emulator.execution.decoder.*;
import org.jpc.emulator.processor.*;
import org.jpc.emulator.processor.fpu64.*;
import static org.jpc.emulator.processor.Processor.*;

public class dec_Eb extends Executable
{
    final int op1Index;

    public dec_Eb(int blockStart, int eip, int prefices, PeekableInputStream input)
    {
        super(blockStart, eip);
        int modrm = input.readU8();
        op1Index = Modrm.Eb(modrm);
    }

    public Branch execute(Processor cpu)
    {
        Reg op1 = cpu.regs[op1Index];
        cpu.cf = cpu.cf();
        cpu.flagOp1 = op1.get8();
        cpu.flagOp2 = 1;
        cpu.flagResult = (byte)(cpu.flagOp1 - 1);
        op1.set8((byte)cpu.flagResult);
        cpu.flagIns = UCodes.SUB8;
        cpu.flagStatus = NCF;
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