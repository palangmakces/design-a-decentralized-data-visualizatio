import kotlinx.coroutines.*
import kotlinx.serialization.*
import kotlinx.serialization.json.*

@Serializable
data class VisualizationData(val id: String, val data: List<Double>)

class DecentralizedDataVisualizationController(private val nodeAddress: String) {

    private val visualizationDataMap: MutableMap<String, VisualizationData> = mutableMapOf()
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    fun start() {
        // Start listening for incoming data
        coroutineScope.launch {
            while (true) {
                val data = receiveDataFromNode()
                if (data != null) {
                    visualizationDataMap[data.id] = data
                    notifyVisualizationUpdate(data.id)
                }
            }
        }
    }

    private fun receiveDataFromNode(): VisualizationData? {
        // Implement logic to receive data from node (e.g. using WebSockets, WebRTC, etc.)
        // For demonstration purposes, return a sample data
        return VisualizationData("sample_id", listOf(1.0, 2.0, 3.0, 4.0, 5.0))
    }

    private fun notifyVisualizationUpdate(id: String) {
        // Implement logic to notify visualization components of the update
        // For demonstration purposes, print to console
        println("Visualization update for $id")
    }

    fun getVisualizationData(id: String): VisualizationData? {
        return visualizationDataMap[id]
    }

    fun addVisualizationEventListener(id: String, listener: (VisualizationData) -> Unit) {
        // Implement logic to add event listener for visualization updates
        // For demonstration purposes, print to console
        println("Added event listener for $id")
    }
}