import org.junit.Test;

/**
 * Created by USER on 2017/6/6.
 */
public class PathTest {

    @Test
    public void testOutputPath(){

        System.out.println(this.getClass().getResource("/").getPath());

    }

}
