import com.github.javafaker.Faker
import entity.Transaction
import java.security.MessageDigest
import java.util.*
import block.Block
import block.BlockChain
import util.makeUUID

/**
 * Main Class Of MeamChain Program
 */
object MeamBlockChainMain {

    @JvmStatic
    fun main(args: Array<String>) {

        /**
         * Generating Random Names with Faker
         */
        val faker = Faker(Locale("fa"))

        /**
         * First element need to be initialized
         */
        val meamChain = mutableListOf(Block("", "", makeDataList(faker), 0L))

        /**
         * Adding more elements to List with previous hash set to it.prevHash
         */
        for (i in 1..10) {
            meamChain.add(
                    /**
                     * The Last Element is chained with this element
                     */
                    Block("", meamChain[meamChain.size - 1].hash, makeDataList(faker), 0L)
            )
        }

        val blockChain = BlockChain(meamChain)

        /**
         * The Chain is printed with each element every line
         */
        println(blockChain.blockList.joinToString("\n"))

        /**
         * Simply Printing BlockChain Validity
         */
        println(blockChain.checkBlockChainValidity())
        println("isBlockChainValid : " + blockChain.isBlockChainValid())

        /**
         * Changing Data So The Chain Breaks
         */
        blockChain[5].data[5].amount = 50.0

        println(blockChain.checkBlockChainValidity())
        println(blockChain.checkBlockChainValidityEx())
        println("isBlockChainValid : " + blockChain.isBlockChainValid())

    }
}

/**
 * Makes a list of Transactions by using Faker
 */
fun makeDataList(faker: Faker) = listOf(
        Transaction(String.makeUUID(), faker.gameOfThrones().character(), faker.name().fullName(), Random().nextDouble()),
        Transaction(String.makeUUID(), faker.gameOfThrones().character(), faker.name().fullName(), Random().nextDouble()),
        Transaction(String.makeUUID(), faker.gameOfThrones().character(), faker.name().fullName(), Random().nextDouble()),
        Transaction(String.makeUUID(), faker.gameOfThrones().character(), faker.name().fullName(), Random().nextDouble()),
        Transaction(String.makeUUID(), faker.gameOfThrones().character(), faker.name().fullName(), Random().nextDouble()),
        Transaction(String.makeUUID(), faker.gameOfThrones().character(), faker.name().fullName(), Random().nextDouble())
)