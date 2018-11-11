package me.xdrop.levenshtein.algorithms

import me.xdrop.fuzzywuzzy.Ratio
import me.xdrop.fuzzywuzzy.functions.ToStringFunction
import me.xdrop.fuzzywuzzy.ratios.PartialRatio
import org.junit.Test

import static org.easymock.EasyMock.anyObject
import static org.easymock.EasyMock.eq
import static org.easymock.EasyMock.expect
import static org.easymock.EasyMock.mock
import static org.easymock.EasyMock.replay

class TokenSetTest extends GroovyTestCase {
    @Test
    void testUsesStringProcessor() {
        def ts = new TokenSet()
        ToStringFunction<String> mock = mock(ToStringFunction)

        expect(mock.apply(eq("notthesame"))).andReturn("thesame")
        expect(mock.apply(eq("thesame"))).andReturn("thesame")
        replay(mock)

        assertEquals 100, ts.apply("notthesame", "thesame", mock)
    }

    @Test
    void testUsesRatio(){
        def ts = new TokenSet()
        def mock = mock(Ratio)

        expect(mock.apply(anyObject(String), anyObject(String))).andReturn(0).anyTimes()
        replay(mock)

        assertEquals 0, ts.apply("one two", "one three", mock)
    }

    @Test
    void testTokenSet() {
        def ts = new TokenSet()

        assertEquals 46, ts.apply("test", "pesticide")
        assertEquals 75, ts.apply("test", "pesticide", new PartialRatio())
    }
}
