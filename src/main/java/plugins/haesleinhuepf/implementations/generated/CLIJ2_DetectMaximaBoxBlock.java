package plugins.haesleinhuepf.implementations.generated;
import plugins.haesleinhuepf.AbstractCLIJ2Block;
import net.haesleinhuepf.clij2.plugins.DetectMaximaBox;
// this is generated code. See src/test/java/net/haesleinhuepf/clicy/codegenerator for details
public class CLIJ2_DetectMaximaBoxBlock extends AbstractCLIJ2Block {
   
    /**
     * Detects local maxima in a given square/cubic neighborhood. 
     * 
     * Pixels in the resulting image are set to 1 if there is no other pixel in a given radius which has a 
     * higher intensity, and to 0 otherwise.
     */
    public CLIJ2_DetectMaximaBoxBlock() {
        super(new DetectMaximaBox());
    }

}
