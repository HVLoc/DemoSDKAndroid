package vn.lochv.demosdkandroid

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import org.json.JSONObject
import com.google.gson.Gson

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
                    val userInfoModel = call.argument<Map<String, Any>>("value")
                    // Chuyển đổi JSON sang NfcModel
                    val gson = Gson()
                    val json = gson.toJson(userInfoModel)
                    val nfcModel = gson.fromJson(json, NfcModel::class.java)

                    // Tạo Intent để chuyển sang màn hình CCCDVerificationScreen
                    val intent = Intent(this, CCCDVerificationActivity::class.java)
                    intent.putExtra("userData", gson.toJson(nfcModel)) // Chuyển NfcModel thành JSON và truyền qua Intent
                    startActivity(intent)

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
                        "isProd" to "false", // Biến xác định hệ thống demo/prod
                        "CCCD" to "027998007724" // Truyền vào giá trị Căn cước vào để thực hiện xác thực luôn, nếu không truyền thì sử dụng quét QR
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
