package org.jpc.emulator.execution.opcodes.vm;

import org.jpc.emulator.execution.*;
import org.jpc.emulator.execution.decoder.*;
import org.jpc.emulator.processor.*;
import org.jpc.emulator.processor.fpu64.*;
import static org.jpc.emulator.processor.Processor.*;

public class and_Ed_Id_mem extends Executable
{
    final Pointer op1;
    final int immd;

    public and_Ed_Id_mem(int blockStart, int eip, int prefices, PeekableInputStream input)
    {
        super(blockStart, eip);
        int modrm = input.readU8();
        op1 = Modrm.getPointer(prefices, modrm, input);
        immd = Modrm.Id(input);
    }

    public Branch execute(Processor cpu)
    {
        cpu.of = cpu.af = cpu.cf = false;
        cpu.flagResult = (op1.get32(cpu) & immd);
        op1.set32(cpu, cpu.flagResult);
        cpu.flagStatus = SZP;
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