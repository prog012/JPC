package org.jpc.emulator.execution.opcodes.rm;

import org.jpc.emulator.execution.*;
import org.jpc.emulator.execution.decoder.*;
import org.jpc.emulator.processor.*;
import static org.jpc.emulator.processor.Processor.*;

public class adc_AL_Ib extends Executable
{
    final int immb;

    public adc_AL_Ib(int blockStart, Instruction parent)
    {
        super(blockStart, parent);
        immb = (byte)parent.operand[1].lval;
    }

    public Branch execute(Processor cpu)
    {
        boolean incf = cpu.cf();
        cpu.flagOp1 = cpu.r_al.get8();
        cpu.flagOp2 = immb;
        cpu.flagResult = (byte)(cpu.flagOp1 + cpu.flagOp2 + (incf ? 1 : 0));
        cpu.r_al.set8((byte)cpu.flagResult);
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