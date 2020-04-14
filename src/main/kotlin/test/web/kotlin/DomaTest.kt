package test.web.kotlin

import org.seasar.doma.*
import org.seasar.doma.experimental.Sql
import org.seasar.doma.jdbc.BatchResult
import org.seasar.doma.jdbc.Config
import org.seasar.doma.jdbc.ConfigSupport
import org.seasar.doma.jdbc.Result
import org.seasar.doma.jdbc.dialect.Dialect
import org.seasar.doma.jdbc.dialect.SqliteDialect
import org.seasar.doma.jdbc.tx.LocalTransactionDataSource
import org.seasar.doma.jdbc.tx.LocalTransactionManager
import org.seasar.doma.jdbc.tx.TransactionManager
import javax.sql.DataSource

@SingletonConfig
class AppConfig private constructor(val _dialect: SqliteDialect, val _dataSource: LocalTransactionDataSource, val txm: TransactionManager) : Config {

    companion object {

        val dataSource = LocalTransactionDataSource("jdbc:sqlite:test.db", null, null)

        val config = AppConfig(
                SqliteDialect(),
                dataSource,
                LocalTransactionManager(dataSource.getLocalTransaction(ConfigSupport.defaultJdbcLogger))
        )

        @JvmStatic
        fun singleton(): AppConfig {
            return config
        }
    }

    override fun getDataSource(): DataSource {
        return _dataSource
    }

    override fun getDialect(): Dialect {
        return _dialect
    }

    override fun getTransactionManager(): TransactionManager {
        return txm
    }
}

@Entity(immutable = true)
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int? = null,
        val name: String
)

@Dao(config = AppConfig::class)
interface UserDao {
    @Sql("""
        select * from user where id = /*id*/0
    """)
    @Select
    fun findById(id: Int): User?

    @Sql("""
       select * from user 
    """)
    @Select
    fun findAll(): List<User>

    @Insert
    fun insert(use: User): Result<User>
}


fun main(args: Array<String>) {
    println("hello, doma")
    val dao = UserDaoImpl()
    val user = User(name = "fuga")
    println(user)

    AppConfig.singleton().transactionManager.required {
        println(dao.findAll())
        val result = dao.insert(user)
        val inserted = result.entity
        println(inserted)
    }
}