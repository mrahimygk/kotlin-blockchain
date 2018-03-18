import block.Block
import block.BlockChain
import com.github.javafaker.Faker
import org.junit.Before
import org.junit.Test
import java.util.*
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class BlockValidityTest(var faker: Faker = Faker(Locale("fa"))) {

    init {
        /**
         * Generating Random Names with Faker
         */

    }

    @Before
    fun bef() {
        println("before")
    }

    @Test
    fun testValidity() {
        var meamChain = BlockChain(mutableListOf())

        /**
         * First element need to be initialized
         */
        meamChain.addData(Block("", "", makeDataList(faker), 0L))

        /**
         * Adding more elements to List with previous hash set to it.prevHash
         */
        for (i in 1..10) {
            meamChain.addData(
                    /**
                     * The Last Element is chained with this element
                     */
                    Block("", meamChain[meamChain.blockList.size - 1].hash, makeDataList(faker), 0L)
            )
        }

        assertTrue(meamChain.isBlockChainValid(),
                "meamChain.isBlockChainValid() asserted True but failed")
    }

    @Test
    fun testInvalidity() {
        var meamChain = BlockChain(mutableListOf())

        /**
         * First element need to be initialized
         */
        meamChain.addData(Block("", "", makeDataList(faker), 0L))

        /**
         * Adding more elements to List with previous hash set to it.prevHash
         */
        for (i in 1..10) {
            meamChain.addData(
                    /**
                     * The Last Element is chained with this element
                     */
                    Block("", meamChain[meamChain.blockList.size - 1].hash, makeDataList(faker), 0L)
            )
        }


        var i = Random().nextInt(meamChain.blockList.size)
        meamChain[i].data[Random().nextInt(meamChain[i].data.size)].amount = Random().nextDouble() * 10


        assertFalse(meamChain.isBlockChainValid(),
                "meamChain.isBlockChainValid() asserted False But Failed")
    }

}