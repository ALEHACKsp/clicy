package plugins.haesleinhuepf.buttons;
import icy.sequence.Sequence;
import net.haesleinhuepf.clicy.CLICY;
import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import plugins.adufour.ezplug.*;
import plugins.adufour.vars.lang.Var;
import plugins.adufour.vars.lang.VarBoolean;
import plugins.adufour.vars.lang.VarDouble;
import plugins.adufour.vars.lang.VarString;
import plugins.haesleinhuepf.AbstractCLIJ2Block;
import plugins.haesleinhuepf.Recorder;
import plugins.haesleinhuepf.VarClearCLBuffer;
import plugins.haesleinhuepf.implementations.generated.CLIJ2_ThresholdMeanBlock;

import java.util.ArrayList;

// this is generated code. See src/test/java/net/haesleinhuepf/clicy/codegenerator for details
public class ThresholdMean extends EzPlug {

    AbstractCLIJ2Block plugin = new CLIJ2_ThresholdMeanBlock();

    @Override
    public void clean() {
        // dialog closed
        ezVar = null;
    }

    @Override
    public void execute() {
        CLICY clij2 = CLICY.getInstance((String) plugin.inputParameters.get("cl_device").getValue());
        Recorder.initRecorder();
        Recorder.record("\n// " + plugin.getName() + "\n");

        ArrayList<ClearCLBuffer> created = new ArrayList<>();
        int count = 0;
        for (Var var : plugin.inputParameters) {
            if (var instanceof VarClearCLBuffer) {

                Sequence sequence = (Sequence) ezVar.get(count).getValue();
                ClearCLBuffer buffer = clij2.pushSequence(sequence);

                String bufferName = Recorder.getBufferNameFromSequenceName(sequence);
                if (bufferName == null) {
                    Recorder.recordPush(sequence, buffer);
                } else {
                    String newBufferName = Recorder.niceName("buffer", buffer);
                    Recorder.record(newBufferName + " = " + bufferName +";\n");
                }
                var.setValue(buffer);
                created.add(((VarClearCLBuffer) var).getValue());
            } else {
                var.setValue(ezVar.get(count).getValue());
            }
            count++;
        }
        plugin.run();
        for (Var var : plugin.outputParameters) {
            if (var instanceof VarClearCLBuffer) {
                ClearCLBuffer buffer = ((VarClearCLBuffer) var).getValue();
                created.add(buffer);
                Sequence sequence = clij2.pullSequence(buffer);

                Recorder.recordPull(sequence, buffer);
                addSequence(sequence);
            }
        }
        for (ClearCLBuffer buffer : created) {
            clij2.release(buffer);
        }

    }

    ArrayList<EzVar> ezVar = null;

    @Override
    public void initialize() {
        ezVar = new ArrayList<>();
        for (Var var : plugin.inputParameters) {
            handleVar(var);
        }
        for (Var var : plugin.outputParameters) {
            handleVar(var);
        }
        if (plugin != null && plugin.getDescriptor() != null && plugin.getDescriptor().getDescription() != null) {
            addEzComponent(new EzLabel(plugin.getDescriptor().getDescription()));
        }
    }

    private void handleVar(Var var) {
        if (var instanceof VarClearCLBuffer) {
            EzVarSequence evs = new EzVarSequence(var.getName());
            addEzComponent(evs);
            ezVar.add(evs);
        } else if (var instanceof VarDouble) {
            EzVarDouble evs = new EzVarDouble(var.getName());
            addEzComponent(evs);
            ezVar.add(evs);
        } else if (var instanceof VarBoolean) {
            EzVarBoolean evs = new EzVarBoolean(var.getName(), ((VarBoolean) var).getDefaultValue());
            addEzComponent(evs);
            ezVar.add(evs);
        } else if (var instanceof VarString) {
            EzVarText evs = new EzVarText(var.getName());
            addEzComponent(evs);
            ezVar.add(evs);
        }
    }
}
