package vn.lochv.demosdkandroid

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.gson.Gson
import io.flutter.embedding.android.FlutterActivity
import org.json.JSONObject
import vn.lochv.demosdkandroid.ui.theme.DemoSDKAndroidTheme
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.plugin.common.MethodChannel

class MainActivity : ComponentActivity() {
    lateinit var flutterEngine: FlutterEngine

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        flutterEngine = FlutterEngine(this)

        flutterEngine.dartExecutor.executeDartEntrypoint(
            DartExecutor.DartEntrypoint.createDefault()
        )

        setContent {
            DemoSDKAndroidTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    val context = LocalContext.current

                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center, // Thêm khoảng cách giữa các phần tử
                        horizontalAlignment = Alignment.CenterHorizontally // Căn giữa theo chiều ngang
                    ) {
                        Greeting(name = "Android")

                        MyButton(
                            onClick = {
                                context.startActivity(Intent(context, EkycFlutterActivity::class.java))
                            },
                            title = "Launch EKYC Flutter!"
                        )

                        MyButton(
                            onClick = {
                                context.startActivity(Intent(context, NFCFlutterActivity::class.java))
                            },
                            title = "Launch NFC Flutter!"
                        )
                    }
                }
            }
        }

    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", modifier = modifier
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
fun MyButton(onClick: () -> Unit, title: String) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = onClick) {
            Text(title)
        }
    }
}

