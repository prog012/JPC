package org.jpc.emulator.execution.opcodes.vm;

import org.jpc.emulator.execution.*;
import org.jpc.emulator.execution.decoder.*;
import org.jpc.emulator.processor.*;
import org.jpc.emulator.processor.fpu64.*;
import static org.jpc.emulator.processor.Processor.*;

public class fxch_ST0_ST5 extends Executable
{

    public fxch_ST0_ST5(int blockStart, int eip, int prefices, PeekableInputStream input)
    {
        super(blockStart, eip);
        int modrm = input.readU8();
    }

    public Branch execute(Processor cpu)
    {
        double tmp = cpu.fpu.ST(0);
        cpu.fpu.setST(0, cpu.fpu.ST(5));
        cpu.fpu.setST(5, tmp);
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