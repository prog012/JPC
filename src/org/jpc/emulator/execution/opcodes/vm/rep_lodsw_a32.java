package org.jpc.emulator.execution.opcodes.vm;

import org.jpc.emulator.execution.*;
import org.jpc.emulator.execution.decoder.*;
import org.jpc.emulator.processor.*;
import org.jpc.emulator.processor.fpu64.*;
import static org.jpc.emulator.processor.Processor.*;

public class rep_lodsw_a32 extends Executable
{
    final int segIndex;

    public rep_lodsw_a32(int blockStart, int eip, int prefices, PeekableInputStream input)
    {
        super(blockStart, eip);
        segIndex = Prefices.getSegment(prefices, Processor.DS_INDEX);
    }

    public Branch execute(Processor cpu)
    {
        Segment seg = cpu.segs[segIndex];
        StaticOpcodes.rep_lodsw_a32(cpu, seg);
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