package org.jpc.emulator.execution.opcodes.pm;

import org.jpc.emulator.execution.*;
import org.jpc.emulator.execution.decoder.*;
import org.jpc.emulator.processor.*;
import static org.jpc.emulator.processor.Processor.*;

public class div_Eb_mem extends Executable
{
    final Pointer op1;

    public div_Eb_mem(int blockStart, Instruction parent)
    {
        super(blockStart, parent);
        op1 = new Pointer(parent.operand[0], parent.adr_mode);
    }

    public Branch execute(Processor cpu)
    {
            int ldiv = cpu.r_ax.get16();
            cpu.r_al.set8((byte) (ldiv/(0xFF& op1.get8(cpu))));
            cpu.r_ah.set8((byte) (ldiv % (0xFF& op1.get8(cpu))));
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