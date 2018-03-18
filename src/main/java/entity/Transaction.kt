package entity

/**
 * Holds A simple Transaction data
 *
 * @param id A UUID for identification
 * @param sender Simple String to represent sender
 * @param receiver Simple String to represent receiver
 * @param amount
 */
data class Transaction(
        var id: String,
        var sender: String,
        var receiver: String,
        var amount: Double
)