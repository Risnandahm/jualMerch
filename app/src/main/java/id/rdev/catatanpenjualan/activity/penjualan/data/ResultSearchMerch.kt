package id.rdev.catatanpenjualan.activity.penjualan.data

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName
import id.rdev.catatanpenjualan.model.Merch

@Generated("com.robohorse.robopojogenerator")
data class ResultSearchMerch(

	@field:SerializedName("merch")
	val merch: List<Merch?>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
)