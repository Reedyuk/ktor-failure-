import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*

interface EngineInterface : HttpClientEngine

class CustomHttpClient(engine: EngineInterface? = null) {

    private val httpClientConfig: HttpClientConfig<*>.() -> Unit = {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
    }

    /* we don't want to expose any implementation detail to the consumer */
    private val client = if (engine != null)
        HttpClient(engine, httpClientConfig)
    else
        HttpClient(httpClientConfig)

    /* this allows us to access private client from inline functions, without exposing client to
    *  consumer modules */
    @PublishedApi
    internal val clientBridge: HttpClient
        get() = client
}