package plugins.haesleinhuepf.implementations.generated;
import plugins.haesleinhuepf.AbstractCLIJ2Block;
import net.haesleinhuepf.clij2.plugins.NonzeroMinimumDiamond;
// this is generated code. See src/test/java/net/haesleinhuepf/clicy/codegenerator for details
public class CLIJ2_NonzeroMinimumDiamondBlock extends AbstractCLIJ2Block {
   
    /**
     * Apply a minimum filter (diamond shape) to the input image. 
     * 
     * The radius is fixed to 1 and pixels with value 0 are ignored.
     */
    public CLIJ2_NonzeroMinimumDiamondBlock() {
        super(new NonzeroMinimumDiamond());
    }

}
