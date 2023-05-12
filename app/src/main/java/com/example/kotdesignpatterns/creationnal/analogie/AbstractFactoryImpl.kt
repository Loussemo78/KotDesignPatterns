import org.junit.Assert
import org.junit.Test


interface Source

class ApiDataSource : Source

class SqlDataSource: Source

abstract class SourceFactory {
    abstract fun makeSource():Source

    companion object {
        inline fun <reified T:Source> createFactory():SourceFactory =
            when(T::class) {
                ApiDataSource::class -> ApiDataSourceFactory()
                SqlDataSource::class -> SqlDataSourceFactory()
                else -> throw java.lang.IllegalArgumentException()
            }
    }
}

class ApiDataSourceFactory:SourceFactory() {
    override fun makeSource(): Source = ApiDataSource()

}

class SqlDataSourceFactory:SourceFactory(){
    override fun makeSource(): Source = SqlDataSource()
}

class AbstractFactoryImplTest {
    @Test
    fun afImplTest() {
        val sourceFactory = SourceFactory.createFactory<ApiDataSource>()
        val source = sourceFactory.makeSource()
        println("Created datasource $source")
        Assert.assertEquals(source.javaClass, ApiDataSource::class.java)


    }
}