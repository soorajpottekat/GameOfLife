import com.thoughtworks.game.component.Cell;
import com.thoughtworks.game.component.CellTest;
import com.thoughtworks.game.component.CellsCreatorTest;
import com.thoughtworks.game.display.GameOfLifePrinterTest;
import com.thoughtworks.game.input.SeedFileReaderTest;
import com.thoughtworks.game.map.MapManagerTest;
import com.thoughtworks.game.rule.RuleSetTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


/**
 * Created by soorajpottekat on 30/03/17.
 */
@RunWith(Suite.class)

@SuiteClasses({
        CellTest.class,
        CellsCreatorTest.class,
        GameOfLifePrinterTest.class,
        SeedFileReaderTest.class,
        MapManagerTest.class,
        RuleSetTest.class

})
public class TestSuite
{
}
