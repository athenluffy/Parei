package mn.athen.test

import org.junit.Assert.*
import org.junit.Test

class TestingUtilTest
{
    @Test
    fun test_forcorrect()
    {
        val add = TestingUtil.add(2,3)
        assert(add == 5)
    }
}