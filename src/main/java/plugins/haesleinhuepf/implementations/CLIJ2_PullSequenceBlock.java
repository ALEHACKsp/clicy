package plugins.haesleinhuepf.implementations;

import icy.sequence.Sequence;
import net.haesleinhuepf.clicy.CLICY;
import net.haesleinhuepf.clij.macro.modules.Pull;
import plugins.adufour.blocks.util.VarList;
import plugins.adufour.vars.lang.VarSequence;
import plugins.adufour.vars.lang.VarString;
import plugins.haesleinhuepf.AbstractCLIJ2Block;
import plugins.haesleinhuepf.VarClearCLBuffer;

public class CLIJ2_PullSequenceBlock extends AbstractCLIJ2Block {
    VarString clijInstanceName;
    VarSequence output;
    VarClearCLBuffer input;

    public CLIJ2_PullSequenceBlock() {
        super(new Pull());

        clijInstanceName = new VarString("", "");
        output = new VarSequence("input", new Sequence());
        input = new VarClearCLBuffer("input");
    }

    @Override
    public void declareInput(VarList varList) {
        varList.add("input", input);
    }

    @Override
    public void declareOutput(VarList varList) {
        varList.add("output", output);
    }

    @Override
    public void run() {
        CLICY clij2 = CLICY.getInstance(clijInstanceName.getValue());
        Sequence sequence = clij2.pullSequence(input.getValue());
        output.setValue(sequence);
    }
}
