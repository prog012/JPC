package org.jpc.emulator.execution.opcodes.vm;

import org.jpc.emulator.execution.*;
import org.jpc.emulator.execution.decoder.*;
import org.jpc.emulator.processor.*;
import org.jpc.emulator.processor.fpu64.*;
import static org.jpc.emulator.processor.Processor.*;

public class adc_Eb_Ib_mem extends Executable
{
    final Pointer op1;
    final int immb;

    public adc_Eb_Ib_mem(int blockStart, int eip, int prefices, PeekableInputStream input)
    {
        super(blockStart, eip);
        int modrm = input.readU8();
        op1 = Modrm.getPointer(prefices, modrm, input);
        immb = Modrm.Ib(input);
    }

    public Branch execute(Processor cpu)
    {
        boolean incf = cpu.cf();
        cpu.flagOp1 = op1.get8(cpu);
        cpu.flagOp2 = immb;
        cpu.flagResult = (byte)(cpu.flagOp1 + cpu.flagOp2 + (incf ? 1 : 0));
        op1.set8(cpu, (byte)cpu.flagResult);
        cpu.flagIns = UCodes.ADC8;
        cpu.flagStatus = OSZAPC;
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