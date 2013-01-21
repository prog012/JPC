package org.jpc.emulator.execution.opcodes.rm;

import org.jpc.emulator.execution.*;
import org.jpc.emulator.execution.decoder.*;
import org.jpc.emulator.processor.*;
import static org.jpc.emulator.processor.Processor.*;

public class popfd extends Executable
{

    public popfd(int blockStart, Instruction parent)
    {
        super(blockStart, parent);
    }

    public Branch execute(Processor cpu)
    {
        cpu.setEFlags((cpu.getEFlags() & 0x20000) | (cpu.pop32() & ~0x1a0000));
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