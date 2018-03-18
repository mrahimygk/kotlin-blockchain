package block

data class BlockChain(
        var blockList: MutableList<Block>
) {


    /**
     * Returns Extended list of Pair<Boolean, String> to see the reason of false elements
     */
    fun checkBlockChainValidityEx() =
            blockList.zipWithNext { a: Block, b: Block ->

                var hashVsCalcHash = false
                var hashVsPrevHash = false
                var reason = ""

                if (a.hash == a.calculateHash()) {
                    hashVsCalcHash = true
                } else {
                    reason += "| reason : a.hash != a.calculateHash() " +
                            "( ${a.hash} != ${a.calculateHash()} )"
                }

                if (a.hash == b.prevHash) {
                    hashVsPrevHash = true
                } else {
                    reason += "| reason : a.hash != b.prevHash " +
                            "( ${a.hash} != ${b.prevHash} )"
                }

                /**
                 * returning Pair<Boolean, String>
                 */
                (hashVsCalcHash and hashVsPrevHash) to reason
            }


    /**
     * if a List<Boolean> from checkBlockChainValidity contains false : not valid
     */
    fun isBlockChainValid() =
            !this.checkBlockChainValidity().contains(false)


    /**
     * Simply checks BlockChain's Validity by Comparing hashes
     */
    fun checkBlockChainValidity() =
            /**
             * Returns a list of booleans. containing true if hashes are valid
             */
            blockList.zipWithNext { a: Block, b: Block ->
                (a.hash == a.calculateHash()) and (a.hash == b.prevHash)
            }

    fun addData(vararg block: Block) {
        block.forEach {
            blockList.add(it)
        }
    }

    operator fun get(i: Int) = blockList[i]
}