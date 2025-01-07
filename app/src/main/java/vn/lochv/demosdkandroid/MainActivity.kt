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
import androidx.compose.ui.tooling.preview.Preview
import org.json.JSONObject
import vn.lochv.demosdkandroid.ui.theme.DemoSDKAndroidTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DemoSDKAndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android", modifier = Modifier.padding(innerPadding)
                    )

                    MyButton(onClick = {
                        // Khai báo dữ liệu dưới dạng Map
                        val jsonPayload = mapOf(
                            "key" to "89f797ab-ec41-446a-8dc1-1dfda5e7e93d",
                            "secretKey" to "63f81c69722acaa42f622ec16d702fdb",
                            "CCCD" to "020098007724"
                        )
                        // Chuyển đổi Map thành chuỗi JSON
                        val jsonString = JSONObject(jsonPayload).toString()

                        // Mã hóa JSON để đảm bảo truyền an toàn qua URL
                        val encodedPayload = Uri.encode(jsonString)


                        // Mã hóa tham số vào initialRoute
                        val initialRoute = "/init_app?payload=$encodedPayload"
                        val intent = Intent(this, EkycFlutterActivity::class.java).apply {
                            putExtra("initialRoute", initialRoute)
                        }
                        startActivity(intent)
                    })
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
fun MyButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Button(onClick = onClick) {
            Text("Launch Flutter!")
        }
    }
}
