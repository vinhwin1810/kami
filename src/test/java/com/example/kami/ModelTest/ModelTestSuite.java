package com.example.kami.ModelTest;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
@RunWith(Suite.class)
@Suite.SuiteClasses(
        {RectangleCellTest.class, AbstractBoardAndCellTest.class,TriangleCellTest.class
        }
)

public class ModelTestSuite {
    // no implementation needed; above annotations do the work.
}
