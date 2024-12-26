package vn.lochv.demosdkandroid

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.android.FlutterFragment
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugins.GeneratedPluginRegistrant
import vn.lochv.demosdkandroid.ui.theme.DemoSDKAndroidTheme

//class MainActivity : ComponentActivity() {
//
//    private lateinit var flutterEngine: FlutterEngine
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        // Initialize Flutter Engine
//        flutterEngine = FlutterEngine(this)
//        GeneratedPluginRegistrant.registerWith(flutterEngine)
//        FlutterEngineCache.getInstance().put("flutter_engine_id", flutterEngine)
//
//        setContent {
//            NFCReaderApp { result ->
//                // Handle NFC result
//                println("NFC Result: $result")
//            }
//        }
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        // Clean up Flutter engine
//        flutterEngine.destroy()
//    }
//}
//
//@Composable
//fun NFCReaderApp(onNFCResult: (String) -> Unit) {
//    var nfcResult by remember { mutableStateOf("No NFC data yet") }
//
//    MaterialTheme {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(16.dp),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Text(
//                text = "Jetpack Compose NFC Reader",
//                style = MaterialTheme.typography.bodySmall,
//                modifier = Modifier.padding(bottom = 24.dp)
//            )
//            Button(
//                onClick = {
//                    // Call the Flutter function to read NFC
//                    MethodChannel(
//                        FlutterEngineCache.getInstance()
//                            .get("flutter_engine_id")!!.dartExecutor.binaryMessenger,
//                        "CHANNEL_EASY_EKYC"
//                    ).invokeMethod("NFC", null, object : MethodChannel.Result {
//
//                        override fun success(result: Any?) {
//                            nfcResult = result as? String ?: "Unknown result"
//                            onNFCResult(nfcResult)
//                        }
//
//                        override fun error(
//                            errorCode: String,
//                            errorMessage: String?,
//                            errorDetails: Any?
//                        ) {
//                            nfcResult = "Error: $errorMessage"
//                            onNFCResult(nfcResult)
//                        }
//
//                        override fun notImplemented() {
//                            nfcResult = "NFC function not implemented"
//                            onNFCResult(nfcResult)
//                        }
//                    })
//                },
//                modifier = Modifier.padding(vertical = 8.dp)
//            ) {
//                Text(text = "Test NFC")
//            }
//            Spacer(modifier = Modifier.height(16.dp))
//            Text(text = "Result: $nfcResult")
//        }
//    }
//}

class MainActivity : ComponentActivity() {
    lateinit var flutterEngine: FlutterEngine

    lateinit var methodChannel: MethodChannel;

    override fun onCreate(savedInstanceState: Bundle?) {

        flutterEngine = FlutterEngine(this)

        flutterEngine
            .dartExecutor
            .executeDartEntrypoint(
                DartExecutor.DartEntrypoint.createDefault()
            )

        methodChannel = MethodChannel(
            flutterEngine.dartExecutor.binaryMessenger,
            "CHANNEL_EASY_EKYC"
        )

        FlutterEngineCache
            .getInstance()
            .put("fullScreenEngineId", flutterEngine)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DemoSDKAndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )

                    MyButton(onClick = {
//TODO: Đối với NFC đang lỗi
//                        methodChannel.invokeMethod("QR", "null", object : MethodChannel.Result {
//
//                            override fun success(result: Any?) {
//                                Log.d("TAG", "success: ")
////                                nfcResult = result as? String ?: "Unknown result"
////                                onNFCResult(nfcResult)
//
//                            }
//
//                            override fun error(
//                                errorCode: String,
//                                errorMessage: String?,
//                                errorDetails: Any?
//                            ) {
//                                Log.d("TAG", "error: ")
////                                nfcResult = "Error: $errorMessage"
////                                onNFCResult(nfcResult)
//                            }
//
//                            override fun notImplemented() {
//                                Log.d("TAG", "notImplemented: ")
////                                nfcResult = "NFC function not implemented"
////                                onNFCResult(nfcResult)
//                            }
//                        })

                        startActivity(
                            FlutterActivity
                                .withNewEngine()
                                .initialRoute("/my_route")
                                .build(this)
                        )
                    })
                }
            }
        }
    }
}

//private fun MethodChannel.invokeMethod(methodGetDummy: String) {
//
//}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DemoSDKAndroidTheme {
        Greeting("Android")
    }
}


@Composable
fun MyButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(), // Lấp đầy toàn bộ kích thước màn hình
        contentAlignment = Alignment.Center // Canh giữa nội dung trong Box
    ) {
        Button(onClick = onClick) {
            Text("Launch Flutter!")
        }
    }
}

