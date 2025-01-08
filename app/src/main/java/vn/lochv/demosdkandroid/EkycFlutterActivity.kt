package vn.lochv.demosdkandroid

import android.net.Uri
import android.os.Bundle
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import org.json.JSONObject

class EkycFlutterActivity : FlutterActivity() {
    private val CHANNEL = "2id.ekyc"

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        MethodChannel(
            flutterEngine.dartExecutor.binaryMessenger,
            CHANNEL
        ).setMethodCallHandler { call, result ->
            when (call.method) {
                "dataUser" -> {
                    val userInfoModel = call.argument<Map<String, Any>>("userInfoModel")
                    val sendNfcModel = call.argument<Map<String, Any>>("sendNfcModel")
                    // Xử lý dữ liệu nhận được từ Flutter
                    println("Received from Flutter: ${userInfoModel.toString()}")

                    // Đóng Activity (Flutter SDK)
                    finish()

                    // Trả về kết quả cho Flutter
                    result.success("SDK Closed")
                }

                "setInitial" -> {
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

                    // Trả về kết quả cho Flutter
                    result.success(encodedPayload)
                }

                else -> {
                    result.notImplemented()
                }
            }
        }
    }

}
