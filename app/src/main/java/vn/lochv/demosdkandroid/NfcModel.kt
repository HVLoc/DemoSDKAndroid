package vn.lochv.demosdkandroid

data class NfcModel(
    var sessionId: String? = null,
    var type: String? = null,
    var number: String? = null,
    var name: String? = null,
    var firstName: String? = null,
    var lastName: String? = null,
    var dob: String? = null,
    var sex: String? = null,
    var nationality: String? = null,
    var doe: String? = null,
    var mrz: String? = null,
    var file: String? = null,
    var aaSignature: String? = null,
    var aaPublicKey: String? = null,
    var keyAlg: String? = null,
    var imgLiveNess: String? = null,
    var faceMatching: String? = null,
    var isFaceMatching: Boolean? = null,
    var sodData: String? = null,
    var dg1DataB64: String? = null,
    var dg2DataB64: String? = null,
    var dg13DataB64: String? = null,
    var dg14DataB64: String? = null,
    var fileId: String? = null,
    var bodyFileId: String? = null,
    var numberVMN: String? = null,
    var nameVNM: String? = null,
    var dobVMN: String? = null,
    var sexVMN: String? = null,
    var nationalityVMN: String? = null,
    var nationVNM: String? = null,
    var religionVMN: String? = null,
    var homeTownVMN: String? = null,
    var residentVMN: String? = null,
    var identificationSignsVNM: String? = null,
    var registrationDateVMN: String? = null,
    var doeVMN: String? = null,
    var nameDadVMN: String? = null,
    var nameMomVMN: String? = null,
    var otherPaper: String? = null,
    var nameCouple: String? = null,
    var raw: String? = null,
    var phone: String? = null,
    var isView: Boolean = false,
    var statusSuccess: Boolean = false,
    var visibleButtonDetail: Boolean = true,
    var kind: String? = null
) {
    companion object {
        fun fromJson(json: Map<String, Any?>): NfcModel {
            return NfcModel(
                sessionId = json["session_id"] as? String,
                type = json["type"] as? String,
                number = json["number"] as? String,
                name = json["name"] as? String,
                dob = json["dob"] as? String,
                sex = json["sex"] as? String,
                nationality = json["nationality"] as? String,
                doe = json["doe"] as? String,
                mrz = json["MRZ"] as? String,
                file = json["file"] as? String,
                aaSignature = json["aaSignature"] as? String,
                aaPublicKey = json["aaPublicKey"] as? String,
                keyAlg = json["keyAlg"] as? String
            )
        }
    }

    fun toJsonBase64(): Map<String, Any?> {
        return mapOf(
            "sodData" to sodData,
            "dg1DataB64" to dg1DataB64,
            "dg2DataB64" to dg2DataB64,
            "dg13DataB64" to dg13DataB64,
            "dg14DataB64" to dg14DataB64,
            "fileId" to fileId,
            "bodyFileId" to bodyFileId,
            "phone" to phone
        )
    }

    fun toJson(): Map<String, Any?> {
        return mapOf(
            "sessionId" to sessionId,
            "type" to type,
            "number" to number,
            "name" to nameVNM,
            "dob" to dobVMN,
            "sex" to sex,
            "nationality" to nationality,
            "doe" to doeVMN,
            "MRZ" to mrz,
            "poo" to homeTownVMN,
            "por" to residentVMN,
            "religion" to religionVMN,
            "nation" to nationVNM,
            "registerDate" to registrationDateVMN,
            "symbol" to identificationSignsVNM,
            "father" to nameDadVMN,
            "mother" to nameMomVMN,
            "aaSignature" to aaSignature,
            "aaPublicKey" to aaPublicKey,
            "keyAlg" to keyAlg?.split(".")?.last(),
            "file" to file,
            "identity" to otherPaper,
            "raw" to raw,
            "couple" to nameCouple
        )
    }
}
